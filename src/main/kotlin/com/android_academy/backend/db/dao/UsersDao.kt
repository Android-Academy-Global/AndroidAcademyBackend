package com.android_academy.backend.db.dao

import com.android_academy.backend.db.models.User
import com.j256.ormlite.dao.Dao

class UsersDao(
        private val delegateDAO: Dao<User, Long>
) {
    fun findBy(username: String): User? =
            delegateDAO.queryForEq(
                    "username", username
            ).let { result ->
                if (result.isNotEmpty()) {
                    result.first()
                } else {
                    null
                }
            }

    fun save(user: User) {
        delegateDAO.create(user)
    }
}