package com.poc.bloodx.service

import com.poc.bloodx.model.BloodRequest
import com.poc.bloodx.repository.BloodRequestRepository
import com.poc.bloodx.workflow.BloodRequestWorkFlow
import com.poc.bloodx.activity.BloodRequestActivity
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class BloodRequestService @Autowired constructor(
    private val workflowServiceStubs: WorkflowServiceStubs,
    private val workflowClient: WorkflowClient,
    private val bloodRequestRepository: BloodRequestRepository,
    private val bloodRequestActivity: BloodRequestActivity
) {

    fun placeBloodRequest(workflowId: String) {
        // Implementation here
    }

    fun makeOrderAccepted(id: Long) {
        val workflowId = id.toString()
        val workflow = workflowClient.newWorkflowStub(BloodRequestWorkFlow::class.java, "BloodRequest_$workflowId")
        workflow.signalOrderAccepted(id)
    }

    fun makeOrderPickedUp(id: Long) {
        val workflowId = id.toString()
        val workflow = workflowClient.newWorkflowStub(BloodRequestWorkFlow::class.java, "BloodRequest_$workflowId")
        workflow.signalOrderPickedUp(id)
    }

    fun makeOrderDelivered(id: Long) {
        val workflowId = id.toString()
        val workflow = workflowClient.newWorkflowStub(BloodRequestWorkFlow::class.java, "BloodRequest_$workflowId")
        workflow.signalOrderDelivered(id)
    }

    private fun createWorkFlowConnection(id: String): BloodRequestWorkFlow {
        val options = WorkflowOptions.newBuilder()
            .setTaskQueue(BloodRequestWorkFlow.QUEUE_NAME)
            .setWorkflowId("BloodRequest_$id")
            .build()
        return workflowClient.newWorkflowStub(BloodRequestWorkFlow::class.java, options)
    }

    fun addBloodRequest(bloodRequest: BloodRequest): BloodRequest {
        bloodRequest.status = "Started"
        val savedBloodRequest = bloodRequestActivity.placeBloodRequest(bloodRequest) // Database store
        val workflowId = savedBloodRequest.id.toString() // Generate workflow ID using bloodRequest ID (primary key)
        val workflow = createWorkFlowConnection(workflowId)
        WorkflowClient.start { workflow.bloodRequestStartedWorkflow(bloodRequest) }
        val id = savedBloodRequest.id.toString()
        // Query the workflow status
        val status = workflow.getStatus() // Query method
        return savedBloodRequest
    }

    fun getAllBloodRequest(): List<BloodRequest> {
        return bloodRequestRepository.findAll()
    }

    fun getBloodRequestsByStatus(status: String): List<BloodRequest> {
        return bloodRequestRepository.findByStatus(status)
    }
}
