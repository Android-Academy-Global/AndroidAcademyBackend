package com.android_academy.backend.api.models

data class RegisterRequestDTO(
        val username: String,
        val pwd: String,
        val name: String,
        val mentor: Boolean
)