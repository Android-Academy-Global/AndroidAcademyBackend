package com.android_academy.backend.api.models

data class UserProfileDTO(
        val username: String,
        val name: String,
        val mentor: Boolean
)