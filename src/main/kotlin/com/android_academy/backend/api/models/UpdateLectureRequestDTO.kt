package com.android_academy.backend.api.models

import com.android_academy.backend.AppConfig
import com.android_academy.backend.db.models.Lecture

data class UpdateLectureRequestDTO(
        val id: Long? = null,
        val title: String,
        val youtubeUrl: String = "",
        val githubRepoUrl: String = "",
        val telegramChannel: String = "",
        val additionalMaterials: List<AdditionalMaterialDTO>,
        val imgUrl: String? = null,
        val tags: List<String>,
        val courseId: Long,
        val startTimeStamp: Long
)

fun UpdateLectureRequestDTO.toLecture(): Lecture =
        Lecture(
                id = id,
                title = title,
                youtubeUrl = youtubeUrl,
                githubRepoUrl = githubRepoUrl,
                telegramChannel = telegramChannel,
                additionalMaterials = AppConfig.objectMapper.writeValueAsString(additionalMaterials),
                imgUrl = imgUrl,
                tags = tags.joinToString(),
                courseId = courseId,
                startTimestamp = startTimeStamp
        )