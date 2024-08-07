package com.poc.bloodx.workflow

import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.workflow.Workflow
// import io.temporal.activity.ActivityStub // Import ActivityStub
import java.time.Duration
import com.poc.bloodx.activity.BloodRequestActivity

class BloodRequestWorkflowImpl : BloodRequestWorkFlow {

    private val retryOptions = RetryOptions.newBuilder()
        .setInitialInterval(Duration.ofSeconds(1))
        .setMaximumInterval(Duration.ofSeconds(100))
        .setBackoffCoefficient(2.0)
        .setMaximumAttempts(50000)
        .build()

    private val options = ActivityOptions.newBuilder()
        .setStartToCloseTimeout(Duration.ofSeconds(30))
        .setRetryOptions(retryOptions)
        .build()

    private val activity: BloodRequestActivity = Workflow.newActivityStub(BloodRequestActivity::class.java, options)

    var isOrderConfirmed = false

    var isOrderPickedUp = false

    var isOrderDelivered = false

    override fun bloodRequestStartedWorkflow() {
        activity.placeBloodRequest()

        println("***** Waiting for BLOOD BANK to confirm blood availability")
        Workflow.await { isOrderConfirmed }

        println("***** Please wait till we assign a Phlebotomist")
        Workflow.await { isOrderPickedUp }

        Workflow.await { isOrderDelivered }
    }

    override fun signalOrderAccepted() {
        activity.setOrderAccepted()
        isOrderConfirmed = true
    }

    override fun signalOrderPickedUp() {
        activity.setOrderPickedUp()
        isOrderPickedUp = true
    }

    override fun signalOrderDelivered() {
        activity.setOrderDelivered()
        isOrderDelivered = true
    }
}
