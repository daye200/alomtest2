package com.example.alomtest

data class UserModel(
    var id : String ?= null,
    var pw : String ?= null
)
data class LoginBackendResponse(
    val code: Int,
    val error: String,
    val message: String,
    val status: Int,
    val timestamp: String
)