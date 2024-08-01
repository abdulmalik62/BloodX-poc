package com.poc.bloodx.workflow

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface BloodRequestWorkflow {
    @WorkflowMethod
    fun handleBloodRequest(requestId: String): String
}