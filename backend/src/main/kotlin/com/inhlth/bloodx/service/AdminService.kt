package com.inhlth.bloodx.service

import com.inhlth.bloodx.model.Admin
import com.inhlth.bloodx.repository.AdminRepository
import org.springframework.stereotype.Service

@Service
class AdminService(private val adminRepository: AdminRepository) {

    fun saveAdmin(admin: Admin) : Admin {
        return adminRepository.save(admin)
    }

    fun getAllAdmins(): List<Admin> {
        return adminRepository.findAll()
    }
}

