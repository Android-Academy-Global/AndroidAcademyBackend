package com.android_academy.backend

import com.android_academy.backend.db.dao.*
import com.android_academy.backend.db.models.*
import com.android_academy.backend.services.CoursesService
import com.android_academy.backend.services.LecturesService
import com.android_academy.backend.services.LoginService
import com.android_academy.backend.services.RegisterService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.table.TableUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Value(value = "\${sqlite.backend.url}")
    lateinit var url: String

    @Bean
    fun userDao(): UsersDao =
            UsersDao(
                    delegateDAO = createDao(clazz = User::class.java)
            )

    @Bean
    fun authInfoDAO(): AuthInfoDao =
            AuthInfoDao(delegateDAO = createDao(clazz = AuthInfo::class.java))

    @Bean
    fun coursesDao(): CoursesDao =
            CoursesDao(
                    coursesDelegateDAO = createDao(clazz = Course::class.java),
                    userCoursesDelegateDao = createDao(clazz = UsersCourses::class.java)
            )

    @Bean
    fun lecturesDao(): LecturesDao =
            LecturesDao(
                    delegateDao = createDao(clazz = Lecture::class.java)
            )

    @Bean
    fun userCoursesDao(): UsersCoursesDao =
            UsersCoursesDao(
                    userCoursesDelegateDao = createDao(clazz = UsersCourses::class.java)
            )

    @Bean
    fun loginService(
            userDAO: UsersDao,
            authInfoDao: AuthInfoDao
    ): LoginService =
            LoginService(userDAO = userDAO, authInfoDao = authInfoDao)

    @Bean
    fun registerService(
            userDAO: UsersDao,
            authInfoDao: AuthInfoDao
    ): RegisterService =
            RegisterService(userDAO = userDAO, authInfoDao = authInfoDao)

    @Bean
    fun coursesService(): CoursesService =
            CoursesService(
                    coursesDao = CoursesDao(
                            coursesDelegateDAO = createDao(clazz = Course::class.java),
                            userCoursesDelegateDao = createDao(clazz = UsersCourses::class.java)
                    )
            )

//    @Bean
//    fun messaging(): FirebaseMessaging {
//        return ClassPathResource("hackathon-22-c3630-firebase-adminsdk-y8pzv-62a45a5111.json").inputStream.use { serviceAccount ->
//            val options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build()
//
//            val firebaseApp = FirebaseApp.initializeApp(options)
//            FirebaseMessaging.getInstance(firebaseApp)
//        }
//    }


    @Bean
    fun lecturesService(
            lecturesDao: LecturesDao,
            usersCoursesDao: UsersCoursesDao,
            authInfoDao: AuthInfoDao
    ): LecturesService =
            LecturesService(
                    lecturesDao = lecturesDao,
                    usersCoursesDao = usersCoursesDao,
                    authInfoDao = authInfoDao
            )

    // fixme: choose/configure a new messaging service as FireBase for this project was shut down
//    @Bean
//    fun messageService(
//            firebaseMessaging: FirebaseMessaging
//    ): MessageService =
//            MessageService(firebaseMessaging)


    private fun <T, I> createDao(clazz: Class<T>): Dao<T, I> {
        val connectionSource = JdbcConnectionSource(url)
        val orm: Dao<T, I> = DaoManager.createDao(
                connectionSource,
                clazz
        )
        TableUtils.createTableIfNotExists(connectionSource, clazz)
        return orm
    }

    companion object {
        val objectMapper: ObjectMapper = jacksonObjectMapper()
    }
}