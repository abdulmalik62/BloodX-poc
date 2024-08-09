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
    fun getAllBloodRequests(@RequestParam status: String): ResponseEntity<List<BloodRequest>> {
        val bloodRequests = bloodRequestService.getBloodRequestsByStatus(status)
        return ResponseEntity(bloodRequests, HttpStatus.OK)
    }
}