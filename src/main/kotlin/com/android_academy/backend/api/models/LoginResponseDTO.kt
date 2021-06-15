package com.android_academy.backend.api.models

data class LoginResponseDTO(
        val userProfile: UserProfileDTO,
        val token: String
)