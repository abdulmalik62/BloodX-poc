package com.poc.bloodx.service

import com.poc.bloodx.model.Admin
import com.poc.bloodx.repository.AdminRepository
import org.springframework.stereotype.Service

@Service
class AdminService(private  val adminRepository: AdminRepository) {

    fun saveAdmin(admin: Admin) : Admin {
        return adminRepository.save(admin)
    }
}

