package com.android_academy.backend.api.controllers

import com.android_academy.backend.api.models.LoginRequestDTO
import com.android_academy.backend.api.models.LoginResponseDTO
import com.android_academy.backend.db.models.toUserProfileDTO
import com.android_academy.backend.services.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/login")
class LoginController(
        @Autowired val loginService: LoginService
) {
    @PostMapping
    fun login(
            @RequestBody loginRequest: LoginRequestDTO
    ): LoginResponseDTO {
        val loginResult = loginService.login(username = loginRequest.username, pwd = loginRequest.pwd)
        if (loginResult.success) {
            return LoginResponseDTO(
                    userProfile = loginResult.user!!.toUserProfileDTO(),
                    token = loginResult.token
            )
        } else {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
    }
}