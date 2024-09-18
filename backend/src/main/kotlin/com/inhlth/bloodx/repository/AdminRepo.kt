package com.inhlth.bloodx.repository

import com.inhlth.bloodx.model.Admin
import org.springframework.data.jpa.repository.JpaRepository;

interface AdminRepository : JpaRepository<Admin,Long>