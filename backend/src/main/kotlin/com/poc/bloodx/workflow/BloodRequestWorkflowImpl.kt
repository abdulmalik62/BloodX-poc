package com.poc.bloodx.workflow

import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.workflow.Workflow
import com.poc.bloodx.model.BloodRequest
import java.time.Duration
import com.poc.bloodx.activity.BloodRequestActivity

class BloodRequestWorkflowImpl : BloodRequestWorkFlow {

    private val retryOptions = RetryOptions.newBuilder()
        .setInitialInterval(Duration.ofSeconds(1))
        .setMaximumInterval(Duration.ofSeconds(100))
        .setBackoffCoefficient(2.0)
        .setMaximumAttempts(2)  // Retry 2 times before failing
        .build()

    private val options = ActivityOptions.newBuilder()
        .setStartToCloseTimeout(Duration.ofSeconds(30))
        .setRetryOptions(retryOptions)
        .build()

    private val activity: BloodRequestActivity = Workflow.newActivityStub(BloodRequestActivity::class.java, options)

    var isOrderConfirmed = false
    var isOrderPickedUp = false
    var isOrderDelivered = false   
    
    private var status: String = "Started"
    private var savedBloodRequest: BloodRequest? = null

    override fun bloodRequestStartedWorkflow(bloodRequest: BloodRequest): String {
        try {
            savedBloodRequest = activity.placeBloodRequest(bloodRequest)
            status = "Blood Request Placed"

            println("***** Waiting for BLOOD BANK to confirm blood availability")
            Workflow.await { isOrderConfirmed }
            status = "Confirmed by blood bank"

            println("***** Please wait till we assign a Phlebotomist")
            Workflow.await { isOrderPickedUp }
            status = "Phlebotomist Picked Up"

            // Retry logic for delivery
            try {
                Workflow.await { isOrderDelivered }
                status = "Blood Delivered"
            } catch (e: Exception) {
                status = "Failed after retrying Blood Delivery"
                throw e  // Re-throw to mark the workflow as failed
            }
        } catch (e: Exception) {
            status = "Failed: ${e.message}"
            throw e  // Re-throw to mark the workflow as failed
        }

        return "Blood request completed"
    }

    override fun signalOrderAccepted(id: Long) {
        val savedBloodRequest = activity.setOrderAccepted(id)
        isOrderConfirmed = true
    }

    override fun signalOrderPickedUp(id: Long) {
        activity.setOrderPickedUp(id)
        isOrderPickedUp = true
    }

    override fun signalOrderDelivered(id: Long) {
        try {
            activity.setOrderDelivered(id)
            isOrderDelivered = true
        } catch (e: Exception) {
            println("***** Failed to deliver blood, retrying...")
            throw e  // This will trigger the retry mechanism
        }
    }

    override fun getSavedBloodRequest(): BloodRequest? {
        return savedBloodRequest
    }

    override fun getStatus(): String {
        return status
    }
}
