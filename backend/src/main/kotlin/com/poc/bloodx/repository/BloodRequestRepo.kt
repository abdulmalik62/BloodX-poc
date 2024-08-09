package com.poc.bloodx.repository

import com.poc.bloodx.model.BloodRequest
import org.springframework.data.jpa.repository.JpaRepository;

interface BloodRequestRepository : JpaRepository<BloodRequest,Long>{
    fun findByStatus(status: String): List<BloodRequest>
}
