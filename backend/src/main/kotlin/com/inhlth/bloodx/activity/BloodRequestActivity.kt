package com.inhlth.bloodx.activity

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import com.inhlth.bloodx.model.BloodRequest

@ActivityInterface
interface BloodRequestActivity {

    @ActivityMethod
    fun placeBloodRequest(bloodRequest :BloodRequest):BloodRequest

    @ActivityMethod
    fun setOrderAccepted(id: Long)

    @ActivityMethod
    fun setOrderPickedUp(id: Long)

    @ActivityMethod
    fun setOrderDelivered(id: Long)
}