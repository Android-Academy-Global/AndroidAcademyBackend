package com.android_academy.backend.db.dao

import com.android_academy.backend.db.models.AuthInfo
import com.j256.ormlite.dao.Dao

class AuthInfoDao(
        private val delegateDAO: Dao<AuthInfo, String>
) {
    fun findBy(token: String): AuthInfo? =
            delegateDAO.queryForId(token)


    fun findFcmTokens(userIds: List<Long>): List<String?> =
            delegateDAO.queryBuilder()
                    .where()
                    .`in`("userId", userIds)
                    .query()
                    .map { it.fcmToken }

    fun save(authInfo: AuthInfo) {
        if (delegateDAO.queryForId(authInfo.token) != null) {
            delegateDAO.update(authInfo)
        } else {
            delegateDAO.create(authInfo)
        }
    }
}