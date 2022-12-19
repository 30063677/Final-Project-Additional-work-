package com.umdaa.nurseasst.enums

enum class ServiceUrlEnvironment( val environmentType: String, val apiUrl: String) {

    DEV("DEV ENVIRONMENT", "http://13.234.116.111/dev/" /*"https://umdaa.co/uat/"*/),

    TEST("TEST ENVIRONMENT", /*"http://3.19.54.31/dev/");*/ "https://umdaa.co/clinic/")


}


