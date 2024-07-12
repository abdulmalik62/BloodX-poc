package com.poc.bloodx.repository

import com.poc.bloodx.model.Admin
import org.springframework.data.jpa.repository.JpaRepository;

interface AdminRepository : JpaRepository<Admin,Long>