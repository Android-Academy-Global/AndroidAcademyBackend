package com.android_academy.backend.api.controllers

import com.android_academy.backend.api.models.RegisterRequestDTO
import com.android_academy.backend.api.models.RegisterResponseDTO
import com.android_academy.backend.db.exceptions.ExistingEntityException
import com.android_academy.backend.db.models.toUserProfileDTO
import com.android_academy.backend.services.RegisterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/register")
class RegisterController(
        @Autowired val registerService: RegisterService
) {

    @PostMapping
    fun register(
            @RequestBody registerRequestDTO: RegisterRequestDTO
    ): RegisterResponseDTO =
            try {
                val registerResult = registerService.register(
                        username = registerRequestDTO.username,
                        pwd = registerRequestDTO.pwd,
                        name = registerRequestDTO.name,
                        mentor = registerRequestDTO.mentor
                )
                RegisterResponseDTO(
                        token = registerResult.token,
                        registerResult.user!!.toUserProfileDTO()
                )
            } catch (e: ExistingEntityException) {
                throw ResponseStatusException(HttpStatus.CONFLICT)
            }
}