package com.android_academy.backend.api.models

import com.android_academy.backend.AppConfig
import com.android_academy.backend.db.models.Lecture
import com.fasterxml.jackson.module.kotlin.readValue

data class LectureDTO(
        val id: Long,
        val title: String,
        val youtubeUrl: String = "",
        val githubRepoUrl: String = "",
        val telegramChannel: String = "",
        val additionalMaterials: List<AdditionalMaterialDTO>,
        val imgUrl: String? = null,
        val tags: List<String>,
        val courseId: Long,
        val startTimestamp: Long
)

fun fromLecture(lecture: Lecture): LectureDTO {
    val additionalMaterials: List<AdditionalMaterialDTO> = AppConfig.objectMapper.readValue(lecture.additionalMaterials)
    return LectureDTO(
            id = lecture.id!!,
            title = lecture.title,
            youtubeUrl = lecture.youtubeUrl,
            githubRepoUrl = lecture.githubRepoUrl,
            telegramChannel = lecture.telegramChannel,
            additionalMaterials = additionalMaterials,
            imgUrl = lecture.imgUrl,
            tags = lecture.tags.split(", "),
            courseId = lecture.courseId,
            startTimestamp = lecture.startTimestamp
    )
}
