package com.poc.bloodx.model

import jakarta.persistence.*

@Entity
class BloodRequest(var patientname: String,
            var bloodgroup: String,
            var age: Int,
            var units: Int,
            var reason: String,
            @Id
            @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
            @SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 1)
            @Column(name = "id", updatable = false, nullable = false)
            val id: Long)