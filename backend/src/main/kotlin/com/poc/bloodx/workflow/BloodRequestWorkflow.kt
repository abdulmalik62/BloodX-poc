package com.poc.bloodx.workflow

import io.temporal.workflow.SignalMethod
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface BloodRequestWorkFlow {

    companion object {
        const val QUEUE_NAME = "BloodX"
    }

    @WorkflowMethod
    fun bloodRequestStartedWorkflow()

    @SignalMethod
    fun signalOrderAccepted()

    @SignalMethod
    fun signalOrderPickedUp()

    @SignalMethod
    fun signalOrderDelivered()
}