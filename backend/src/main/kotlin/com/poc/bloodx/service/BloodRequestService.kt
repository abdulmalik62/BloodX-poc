package com.poc.bloodx.service

import com.poc.bloodx.repository.BloodRequestRepository
import com.poc.bloodx.model.BloodRequest
import org.springframework.stereotype.Service

@Service
class BloodRequestService(private  val bloodRequestRepository: BloodRequestRepository) {

    fun saveAdmin(bloodRequest: BloodRequest) : BloodRequest {
        return bloodRequestRepository.save(bloodRequest)
    }

    fun getAllBloodRequest (): List<BloodRequest> {
        return bloodRequestRepository.findAll()
    }

}