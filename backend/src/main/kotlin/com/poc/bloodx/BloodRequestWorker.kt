package com.poc.bloodx

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.Worker
import io.temporal.worker.WorkerFactory
import com.poc.bloodx.workflow.BloodRequestWorkflowImpl
import com.poc.bloodx.workflow.BloodRequestActivitiesImpl
import com.poc.bloodx.workflow.Shared

fun main() {
    // Get a Workflow service stub.
    val service = WorkflowServiceStubs.newLocalServiceStubs()

    // Get a Workflow service client which can be used to start, Signal, and Query Workflow Executions.
    val client = WorkflowClient.newInstance(service)

    // Define the workflow factory. It is used to create workflow workers that poll specific Task Queues.
    val factory = WorkerFactory.newInstance(client)

    // Define the workflow worker. Workflow workers listen to a defined task queue and process
    // workflows and activities.
    val worker = factory.newWorker(Shared.BLOOD_REQUEST_TASK_QUEUE)

    // Register our workflow implementation with the worker.
    // Workflow implementations must be known to the worker at runtime in
    // order to dispatch workflow tasks.
    worker.registerWorkflowImplementationTypes(BloodRequestWorkflowImpl::class.java)

    // Register our Activity Types with the Worker. Since Activities are stateless and thread-safe,
    // the Activity Type is a shared instance.
    worker.registerActivitiesImplementations(BloodRequestActivitiesImpl())

    // Start all the workers registered for a specific task queue.
    // The started workers then start polling for workflows and activities.
    factory.start()
}
