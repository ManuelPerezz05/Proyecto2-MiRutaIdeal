package com.example.mirutaideal

data class RegisterRequest(
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class UserResponse(
    val id: Int,
    val email: String,
    val nombre: String?
)

data class LoginResponse(
    val token: String,
    val user: UserResponse
)

