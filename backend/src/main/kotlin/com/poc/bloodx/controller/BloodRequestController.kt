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

@RestController
@RequestMapping("/BloodX/Hospital")
@CrossOrigin("\${CrossOrigin}")
class BloodRequestController(private val bloodRequestService: BloodRequestService) {

    @PostMapping("/AddBloodRequest")
    fun createAdmin(@RequestBody bloodRequest: BloodRequest) : String{
        val id = bloodRequestService.addBloodRequest(bloodRequest)
        return "Request placed. Your id is: $id";
    }

    @GetMapping("/GetAll")
    fun getAllBloodRequest(): ResponseEntity<List<BloodRequest>> {
        val bloodRequest = bloodRequestService.getAllBloodRequest()
        return ResponseEntity(bloodRequest, HttpStatus.OK)
    }

    
}