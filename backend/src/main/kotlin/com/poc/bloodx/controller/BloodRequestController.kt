package com.poc.bloodx.controller

import com.poc.bloodx.model.BloodRequest
import com.poc.bloodx.service.BloodRequestService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestHeader
// import org.springframework.security.access.prepost.PreAuthorize
import okhttp3.Response

@RestController
@RequestMapping("/BloodX/Hospital")
@CrossOrigin("\${CrossOrigin}")
class BloodRequestController(private val bloodRequestService: BloodRequestService) {

    @PostMapping("/AddBloodRequest")
    fun createAdmin(@RequestBody bloodRequest: BloodRequest) : BloodRequest{
        val savedBloodRequest = bloodRequestService.addBloodRequest(bloodRequest)
        return savedBloodRequest;
    }

    @GetMapping("/GetAll")
    fun getAllBloodRequest(@RequestHeader("Authorization") authorizationHeader: String): ResponseEntity<List<BloodRequest>> {
        // Extract the token from the Authorization header
        val token = authorizationHeader.removePrefix("Bearer ").trim()

        val bloodRequest = bloodRequestService.getAllBloodRequest(token)

        return ResponseEntity(bloodRequest, HttpStatus.OK)
    }

    // @GetMapping("/GetAll")
    // fun getAllBloodRequest(): ResponseEntity<String> {
    //     val response: Response = bloodRequestService.performApiCall()

    //     // Extract the response body as a String
    //     val responseBody = response.body?.string()

    //     return ResponseEntity(responseBody ?: "No content", HttpStatus.OK)
    // }

    @GetMapping("/GetAllByStatus")
    fun getAllBloodRequests(@RequestHeader("Authorization") authorizationHeader: String,@RequestParam status: String): ResponseEntity<List<BloodRequest>> {
        val token = authorizationHeader.removePrefix("Bearer ").trim()
        val bloodRequests = bloodRequestService.getBloodRequestsByStatus(status,token)
        return ResponseEntity(bloodRequests, HttpStatus.OK)
    }

    
}