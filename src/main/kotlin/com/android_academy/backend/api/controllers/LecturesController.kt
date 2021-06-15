package com.android_academy.backend.api.controllers

import com.android_academy.backend.api.models.LectureDTO
import com.android_academy.backend.api.models.UpdateLectureRequestDTO
import com.android_academy.backend.api.models.fromLecture
import com.android_academy.backend.api.models.toLecture
import com.android_academy.backend.services.LecturesService
import com.android_academy.backend.services.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/lectures")
class LecturesController(
        @Autowired val lecturesService: LecturesService,
        @Autowired val loginService: LoginService
) {
    @PostMapping("update")
    fun updateLecture(
            @RequestHeader(CoursesController.TOKEN_HEADER, required = false) token: String?,
            @RequestBody updateLectureRequestDTO: UpdateLectureRequestDTO
    ): LectureDTO {
        if (token == null) {
            throw throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
        val authInfo = loginService.getAuthInfo(token)
        if (authInfo != null) {
            return fromLecture(lecturesService.save(lecture = updateLectureRequestDTO.toLecture()))
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("all")
    fun getAll(
            @RequestHeader(CoursesController.TOKEN_HEADER, required = false) token: String?,
            @RequestParam courseId: Long
    ): List<LectureDTO> {
        if (token != null && loginService.getAuthInfo(token) != null) {
            return lecturesService.getAll()
                    .map { lecture -> fromLecture(lecture = lecture) }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("by-id")
    fun getLectureById(
            @RequestHeader(CoursesController.TOKEN_HEADER, required = false) token: String?,
            @RequestParam lectureId: Long
    ): LectureDTO {
        if (token != null && loginService.getAuthInfo(token) != null) {
            val lecture = lecturesService.getById(id = lectureId)
            if (lecture != null) {
                return fromLecture(lecture)
            } else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND)
            }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

}
