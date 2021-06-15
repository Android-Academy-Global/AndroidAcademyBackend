package com.android_academy.backend.api.controllers

import com.android_academy.backend.services.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/user")
class UsersController(
        @Autowired val loginService: LoginService
) {
    @PostMapping("update-fcm-token")
    fun updateFcmToken(
            @RequestHeader(CoursesController.TOKEN_HEADER, required = false) token: String?,
            @RequestBody fcmToken: String
    ) {
        if (token == null) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
        val authInfo = loginService.getAuthInfo(token)
        if (authInfo != null) {
            loginService.updateAuthInfo(token = token, fcmToken = fcmToken, userId = authInfo.userId)
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }
}