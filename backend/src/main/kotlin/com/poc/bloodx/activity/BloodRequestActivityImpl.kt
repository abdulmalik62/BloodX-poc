package com.poc.bloodx.activity

import com.poc.bloodx.model.BloodRequest
import com.poc.bloodx.repository.BloodRequestRepository
import java.util.Optional

class BloodRequestActivityImpl(private val bloodRequestRepository: BloodRequestRepository) : BloodRequestActivity {

    override fun placeBloodRequest(bloodRequest :BloodRequest):BloodRequest {
        println("***** BloodRequest has been placed")
        val savedBloodRequest = bloodRequestRepository.save(bloodRequest)
        // Update the status
        savedBloodRequest.status = "Blood Request Placed"
        return bloodRequestRepository.save(savedBloodRequest)
    }

    override fun setOrderAccepted(id: Long) {
        val optionalBloodRequest: Optional<BloodRequest> = bloodRequestRepository.findById(id)
        if (optionalBloodRequest.isPresent) {
            val bloodRequest = optionalBloodRequest.get()
            bloodRequest.status = "Confirmed by blood bank" // Update the status
            bloodRequestRepository.save(bloodRequest)
            println("***** Blood bank has accepted your order")
        } else {
            println("BloodRequest with ID $id not found")
        }
        
    }

    override fun setOrderPickedUp(id: Long) {
        val optionalBloodRequest: Optional<BloodRequest> = bloodRequestRepository.findById(id)
        if (optionalBloodRequest.isPresent) {
            val bloodRequest = optionalBloodRequest.get()
            bloodRequest.status = "Phlebotomist Picked Up" // Update the status
            bloodRequestRepository.save(bloodRequest)
            println("***** Order has been picked up")
        } else {
            println("BloodRequest with ID $id not found")
        }
    }

    override fun setOrderDelivered(id: Long) {
        val optionalBloodRequest: Optional<BloodRequest> = bloodRequestRepository.findById(id)
        if (optionalBloodRequest.isPresent) {
            val bloodRequest = optionalBloodRequest.get()
            bloodRequest.status = "Blood Delivered" // Update the status
            bloodRequestRepository.save(bloodRequest)
            println("***** Order Delivered")
        } else {
            println("BloodRequest with ID $id not found")
        }
        
    }
}
