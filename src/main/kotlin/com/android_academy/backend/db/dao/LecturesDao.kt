package com.android_academy.backend.db.dao

import com.android_academy.backend.db.models.Lecture
import com.j256.ormlite.dao.Dao

class LecturesDao(
        private val delegateDao: Dao<Lecture, Long>
) {
    fun save(lecture: Lecture): Lecture {
        if (lecture.id != null) {
            delegateDao.update(lecture)
        } else {
            delegateDao.create(lecture)
        }
        return findById(id = lecture.id!!)!!
    }

    fun findById(id: Long): Lecture? =
            delegateDao.queryForId(id)

    fun getAll(): List<Lecture> =
            delegateDao.queryForAll()
}