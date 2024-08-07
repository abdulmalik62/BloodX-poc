package com.poc.bloodx.controller

import com.poc.bloodx.model.Admin
import com.poc.bloodx.service.AdminService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.CrossOrigin
// import org.springframework.security.access.prepost.PreAuthorize

@RestController
@RequestMapping("/BloodX/Admins")
@CrossOrigin("\${CrossOrigin}")
class AdminController(private  val adminService: AdminService) {

    @PostMapping("/Create")
    fun createAdmin(@RequestBody admin: Admin) : ResponseEntity<Admin>{
        val savedAdmin = adminService.saveAdmin(admin)
        return ResponseEntity(savedAdmin, HttpStatus.CREATED)
    }

    @GetMapping("/GetAll")
    // @PreAuthorize("isAuthenticated()")
    fun getAllAdmins(): ResponseEntity<List<Admin>> {
        val admins = adminService.getAllAdmins()
        return ResponseEntity(admins, HttpStatus.OK)
    }

}