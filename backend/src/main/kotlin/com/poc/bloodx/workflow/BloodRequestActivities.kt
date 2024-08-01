package com.poc.bloodx.workflow

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface BloodRequestActivities {
    @ActivityMethod
    fun processBloodRequest(requestId: String): String
}