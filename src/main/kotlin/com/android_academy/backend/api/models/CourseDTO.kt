package com.android_academy.backend.api.models

import com.android_academy.backend.db.models.Course

data class CourseDTO(
        val id: Long,
        val title: String,
        val shortDescription: String? = null,
        val fullDescription: String? = null,
        val imgUrl: String? = null,
        val tags: List<String>,
        val subscribed: Boolean
)

fun fromCourse(course: Course): CourseDTO =
        CourseDTO(
                id = course.id!!,
                title = course.title,
                shortDescription = course.shortDescription,
                fullDescription = course.fullDescription,
                imgUrl = course.imgUrl,
                tags = course.tags?.split(", ") ?: emptyList(),
                subscribed = course.subscribed
        )