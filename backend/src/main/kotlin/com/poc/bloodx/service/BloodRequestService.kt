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
import com.poc.bloodx.config.ApiClient
import okhttp3.Response
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.poc.bloodx.exception.UnauthorizedException


@Service
class BloodRequestService @Autowired constructor(
    private val workflowServiceStubs: WorkflowServiceStubs,
    private val workflowClient: WorkflowClient,
    private val bloodRequestRepository: BloodRequestRepository,
    private val bloodRequestActivity: BloodRequestActivity,
    private val apiClient: ApiClient
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

    // fun performApiCall(): Response {
    //     val url = "https://bloodx-f0vwkp.zitadel.cloud/oidc/v1/userinfo"
    //     val token = "TYvGt-LygifwmhQ7twTrI4ycvsjwnTOlzhFIocHjnduZuM5oBeoVBBMPosfPRKAn9YsqDPNiLIvONnUoOVG7hmtHhQqmB9Y-YSffLKuL" // Retrieve or inject the token as needed

    //     // Call the API
    //     return apiClient.callApi(url, token)
    // }

    @Value("\${bloodx.url}")
    lateinit var url: String

    // fun addBloodRequest(bloodRequest: BloodRequest, token: String): BloodRequest {
    //     // Call the API with the token and get the roles
    //     val response = callApi(url, token)
    //     val roles = extractRoles(response.body)

    //     // Check if the user has the required role
    //     if (!roles.contains("Dev")) {
    //         throw IllegalAccessException("User does not have the required role to perform this action")
    //     }

    //     bloodRequest.status = "Started"
        
    //     // Store in the database
    //     val savedBloodRequest = bloodRequestActivity.placeBloodRequest(bloodRequest)
        
    //     // Generate workflow ID using bloodRequest ID (primary key)
    //     val workflowId = savedBloodRequest.id.toString()
        
    //     // Create a workflow connection
    //     val workflow = createWorkFlowConnection(workflowId)
        
    //     // Start the workflow
    //     WorkflowClient.start { workflow.bloodRequestStartedWorkflow(bloodRequest) }
        
    //     // Query the workflow status
    //     val status = workflow.getStatus()
        
    //     return savedBloodRequest
    // }


    private fun extractRoles(responseBody: String?): List<String> {
        // Parse the JSON response to extract roles
        responseBody?.let {
            val mapper = jacksonObjectMapper()
            val jsonNode = mapper.readTree(it)
            val roles = mutableListOf<String>()

            val zitadelRoles = jsonNode.path("urn:zitadel:iam:org:project:roles")
            zitadelRoles.fields().forEachRemaining { role ->
                roles.add(role.key)
            }

            return roles
        }
        return emptyList()
    }

    // fun getAllBloodRequest(): List<BloodRequest> {
    //     return bloodRequestRepository.findAll()
    // }

    fun getAllBloodRequest(token: String): List<BloodRequest> {

        val requiredRoles = listOf("Hospital")

        // Call the API with the token and get the roles
        val response = apiClient.callApi(url, token)

        // Convert ResponseBody to String
        val responseBodyString = response.body?.string()

        val roles = extractRoles(responseBodyString)

        // Check if the user has the required role
        if (!roles.any { it in requiredRoles }) {
            val rolesString = roles.joinToString(", ")
            throw UnauthorizedException("$rolesString user does not have the required role to access this resource")
        }

        // If the role check passes, return all blood requests
        return bloodRequestRepository.findAll()
    }

    fun getBloodRequestsByStatus(status: String): List<BloodRequest> {
        return bloodRequestRepository.findByStatus(status)
    }
}
