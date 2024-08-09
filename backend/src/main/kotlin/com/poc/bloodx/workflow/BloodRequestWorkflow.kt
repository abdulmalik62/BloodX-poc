package com.poc.bloodx.workflow

import io.temporal.workflow.SignalMethod
import io.temporal.workflow.WorkflowInterface
import com.poc.bloodx.model.BloodRequest
import io.temporal.workflow.WorkflowMethod
import io.temporal.workflow.QueryMethod

@WorkflowInterface
interface BloodRequestWorkFlow {

    companion object {
        const val QUEUE_NAME = "BloodX"
    }

    @WorkflowMethod
    fun bloodRequestStartedWorkflow(bloodRequest :BloodRequest):String

    @QueryMethod
    fun getSavedBloodRequest(): BloodRequest?

    @QueryMethod
    fun getStatus(): String

    @SignalMethod
    fun signalOrderAccepted(id: Long)

    @SignalMethod
    fun signalOrderPickedUp(id: Long)

    @SignalMethod
    fun signalOrderDelivered(id: Long)
}