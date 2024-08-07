package com.poc.bloodx.activity

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface BloodRequestActivity {

    @ActivityMethod
    fun placeBloodRequest()

    @ActivityMethod
    fun setOrderAccepted()

    @ActivityMethod
    fun setOrderPickedUp()

    @ActivityMethod
    fun setOrderDelivered()
}