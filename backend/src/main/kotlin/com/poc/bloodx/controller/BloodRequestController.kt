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

@RestController
@RequestMapping("/BloodX/BloodRequest")
class BloodRequestController(private val bloodRequestService: BloodRequestService) {

    @PostMapping("/Create")
    fun createAdmin(@RequestBody bloodRequest: BloodRequest) : ResponseEntity<BloodRequest>{
        val savedBloodRequest = bloodRequestService.saveAdmin(bloodRequest)
        return ResponseEntity(savedBloodRequest, HttpStatus.CREATED)
    }

    @GetMapping("/GetAll")
    fun getAllBloodRequest(): ResponseEntity<List<BloodRequest>> {
        val bloodRequest = bloodRequestService.getAllBloodRequest()
        return ResponseEntity(bloodRequest, HttpStatus.OK)
    }
}