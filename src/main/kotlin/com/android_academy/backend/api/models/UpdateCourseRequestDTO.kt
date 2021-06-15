package com.android_academy.backend.api.models

import com.android_academy.backend.db.models.Course

data class UpdateCourseRequestDTO(
        val id: Long? = null,
        val title: String,
        val shortDescription: String? = null,
        val fullDescription: String? = null,
        val imgUrl: String? = null,
        val tags: List<String>,
        val subscribed: Boolean = false
)

fun UpdateCourseRequestDTO.toCourse(): Course =
        Course(
                id = id,
                title = title,
                shortDescription = shortDescription,
                fullDescription = fullDescription,
                imgUrl = imgUrl,
                tags = tags.joinToString(),
                subscribed = subscribed
        )