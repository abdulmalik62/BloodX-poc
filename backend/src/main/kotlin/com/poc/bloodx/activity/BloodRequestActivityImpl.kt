package com.poc.bloodx.activity

class BloodRequestActivityImpl : BloodRequestActivity {

    override fun placeBloodRequest() {
        println("***** BloodRequest has been placed")
    }

    override fun setOrderAccepted() {
        println("***** Restaurant has accepted your order")
    }

    override fun setOrderPickedUp() {
        println("***** Order has been picked up")
    }

    override fun setOrderDelivered() {
        println("***** Order Delivered")
    }
}
