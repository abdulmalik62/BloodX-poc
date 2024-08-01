package com.poc.bloodx

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.client.WorkflowStub
import io.temporal.serviceclient.WorkflowServiceStubs
import com.poc.bloodx.workflow.Shared
import com.poc.bloodx.workflow.BloodRequestWorkflow

fun main() {
    // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
    val service = WorkflowServiceStubs.newLocalServiceStubs()

    // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
    val client = WorkflowClient.newInstance(service)

    // Define our workflow unique id
    val WORKFLOW_ID = "BloodRequestWorkflowID"

    /*
     * Set Workflow options such as WorkflowId and Task Queue so the worker knows where to list and which workflows to execute.
     */
    val options = WorkflowOptions.newBuilder()
        .setWorkflowId(WORKFLOW_ID)
        .setTaskQueue(Shared.BLOOD_REQUEST_TASK_QUEUE)
        .build()

    // Create the workflow client stub. It is used to start our workflow execution.
    val workflow = client.newWorkflowStub(BloodRequestWorkflow::class.java, options)

    /*
     * Execute our workflow and wait for it to complete. The call to our getGreeting method is
     * synchronous.
     * 
     * Replace the parameter "World" in the call to getGreeting() with your name.
     */
    val greeting = workflow.handleBloodRequest("928292")

    val workflowId = WorkflowStub.fromTyped(workflow).execution.workflowId
    // Display workflow execution results
    println("$workflowId $greeting")
}
