package com.poc.bloodx.controller

import com.poc.bloodx.model.Admin
import com.poc.bloodx.service.AdminService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/BloodX/Admins")
class AdminController(private  val adminService: AdminService) {

    @PostMapping
    fun createAdmin(@RequestBody admin: Admin) : ResponseEntity<Admin>{
        val savedAdmin = adminService.saveAdmin(admin)
        return ResponseEntity(savedAdmin, HttpStatus.CREATED)

    }
}
