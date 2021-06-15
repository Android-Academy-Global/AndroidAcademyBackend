package com.android_academy.backend.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

// fixme: configure another messaging mechanism
@Component
class ScheduledNotificationsService(
//        @Autowired val messageService: MessageService,
        @Autowired val lecturesService: LecturesService
) {
//    @Scheduled(cron = "0 */15 * * * *") // hourly
    fun sendNotificationsIfNeeded() {
        for ((lecture, tokens) in lecturesService.findFcmTokensToBeNotified()) {
//            messageService.sendNotifications(lecture = lecture, fcmTokens = tokens)
        }
    }
}