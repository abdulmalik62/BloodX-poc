package com.poc.bloodx.model

import jakarta.persistence.*

@Entity
class Admin(var name: String,
            var email: String,
            var phone: Long,
            var hospitalname: String,
            @Id
            @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
            @SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 1)
            @Column(name = "id", updatable = false, nullable = false)
            val id: Long)

