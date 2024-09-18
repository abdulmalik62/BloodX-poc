package com.inhlth.bloodx.controller

import com.inhlth.bloodx.model.BloodRequest
import com.inhlth.bloodx.service.BloodRequestService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/BloodX/BloodBank")
@CrossOrigin("\${CrossOrigin}")
class BloodBankController(private val bloodRequestService: BloodRequestService) {

    @PostMapping("/BloodRequestConfirmation")
    fun orderAccepted(@RequestParam("id") id: Long): String {
        bloodRequestService.makeOrderAccepted(id)
        return "Blood request confirmed"
    }

    @PostMapping("/PhlebotomistConfirmation")
    fun orderPickedUp(@RequestParam("id") id: Long): String {
        bloodRequestService.makeOrderPickedUp(id)
        return "Blood shipped"
    }

    @PostMapping("/BloodDelivered")
    fun orderDelivered(@RequestParam("id") id: Long): String {
        bloodRequestService.makeOrderDelivered(id)
        return "Delivered successfully"
    }

    @GetMapping("/GetAllByStatus")
    fun getAllBloodRequests(@RequestHeader("Authorization") authorizationHeader: String,@RequestParam status: String): ResponseEntity<List<BloodRequest>> {
        val token = authorizationHeader.removePrefix("Bearer ").trim()
        val bloodRequests = bloodRequestService.getBloodRequestsByStatus(status,token)
        return ResponseEntity(bloodRequests, HttpStatus.OK)
    }
}