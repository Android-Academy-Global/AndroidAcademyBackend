package com.android_academy.backend.services

import com.android_academy.backend.db.dao.CoursesDao
import com.android_academy.backend.db.models.Course
import org.springframework.beans.factory.annotation.Autowired

class CoursesService(
        @Autowired val coursesDao: CoursesDao,
) {
    fun save(userId: Long, course: Course): Course =
            coursesDao.save(userId = userId, course = course)

    fun getAllCourses(): List<Course> =
            coursesDao.getAll()

    fun getFavoriteCourses(userId: Long): List<Course> =
            coursesDao.getFavorite(userId = userId)
}