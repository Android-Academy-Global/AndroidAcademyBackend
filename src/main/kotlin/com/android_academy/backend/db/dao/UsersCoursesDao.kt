package com.android_academy.backend.db.dao

import com.android_academy.backend.db.models.UsersCourses
import com.j256.ormlite.dao.Dao

class UsersCoursesDao(
        private val userCoursesDelegateDao: Dao<UsersCourses, Long>
) {
    fun save(usersCourses: UsersCourses) {
        userCoursesDelegateDao.create(usersCourses)
    }

    fun findCourseIds(userId: Long): List<Long> =
            userCoursesDelegateDao.queryForEq("userId", userId)
                    .map { usersCourses -> usersCourses.courseId }

    fun findUserIds(courseId: Long): List<Long> =
            userCoursesDelegateDao.queryForEq("courseId", courseId)
                    .map { usersCourses -> usersCourses.userId }
}