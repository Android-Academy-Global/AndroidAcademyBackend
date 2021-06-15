package com.android_academy.backend.services.models

import com.android_academy.backend.db.models.User

data class LoginResult(
        val success: Boolean,
        val token: String,
        val user: User?
)