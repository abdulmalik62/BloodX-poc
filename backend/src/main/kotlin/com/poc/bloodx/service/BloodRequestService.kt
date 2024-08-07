package com.poc.bloodx.service

import com.poc.bloodx.repository.BloodRequestRepository
import com.poc.bloodx.model.BloodRequest

// @Service
// class BloodRequestService(private  val bloodRequestRepository: BloodRequestRepository) {

//     fun saveAdmin(bloodRequest: BloodRequest) : BloodRequest {
//         return bloodRequestRepository.save(bloodRequest)
//     }

//     fun getAllBloodRequest (): List<BloodRequest> {
//         return bloodRequestRepository.findAll()
//     }

// }

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.poc.bloodx.workflow.BloodRequestWorkFlow
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs

@Service
class BloodRequestService @Autowired constructor(
    private val workflowServiceStubs: WorkflowServiceStubs,
    private val workflowClient: WorkflowClient,
    private  val bloodRequestRepository: BloodRequestRepository
) {

    fun placeBloodRequest(workflowId: String) {
        // val workflow = createWorkFlowConnection(workflowId)
        // WorkflowClient.start { workflow.startApprovalWorkflow() }
    }

    fun makeOrderAccepted(workflowId: String) {
        val workflow = workflowClient.newWorkflowStub(BloodRequestWorkFlow::class.java, "BloodRequest_$workflowId")
        workflow.signalOrderAccepted()
    }

    fun makeOrderPickedUp(workflowId: String) {
        val workflow = workflowClient.newWorkflowStub(BloodRequestWorkFlow::class.java, "BloodRequest_$workflowId")
        workflow.signalOrderPickedUp()
    }

    fun makeOrderDelivered(workflowId: String) {
        val workflow = workflowClient.newWorkflowStub(BloodRequestWorkFlow::class.java, "BloodRequest_$workflowId")
        workflow.signalOrderDelivered()
    }

    private fun createWorkFlowConnection(id: String): BloodRequestWorkFlow{
        val options = WorkflowOptions.newBuilder()
            .setTaskQueue(BloodRequestWorkFlow.QUEUE_NAME)
            .setWorkflowId("BloodRequest_$id")
            .build()
        return workflowClient.newWorkflowStub(BloodRequestWorkFlow::class.java, options)
    }

    
    fun addBloodRequest(bloodRequest: BloodRequest): String{
        val savedBloodRequest = bloodRequestRepository.save(bloodRequest)//database store 
        println("*****")
        println(savedBloodRequest.id)
        val workflowid = savedBloodRequest.id.toString() // generate workflow id takes from bloodrequest id(pk)
        val workflow = createWorkFlowConnection(workflowid)
        WorkflowClient.start { workflow.bloodRequestStartedWorkflow() }
        return savedBloodRequest.id.toString()
    }

    fun getAllBloodRequest (): List<BloodRequest> {
        return bloodRequestRepository.findAll()
    }

}
