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
    fun getAllBloodRequest(): ResponseEntity<List<BloodRequest>> {
        val bloodRequest = bloodRequestService.getAllBloodRequest()
        return ResponseEntity(bloodRequest, HttpStatus.OK)
    }

    @GetMapping("/GetAllByStatus")
    fun getAllBloodRequests(@RequestParam status: String): ResponseEntity<List<BloodRequest>> {
        val bloodRequests = bloodRequestService.getBloodRequestsByStatus(status)
        return ResponseEntity(bloodRequests, HttpStatus.OK)
    }

    
}