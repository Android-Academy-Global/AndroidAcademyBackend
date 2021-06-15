package com.android_academy.backend.api.models

data class RegisterResponseDTO(
        val token: String,
        val userProfile: UserProfileDTO
)