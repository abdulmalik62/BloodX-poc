package com.inhlth.bloodx.repository

import com.inhlth.bloodx.model.BloodRequest
import org.springframework.data.jpa.repository.JpaRepository;

interface BloodRequestRepository : JpaRepository<BloodRequest,Long>{
    fun findByStatus(status: String): List<BloodRequest>
}
