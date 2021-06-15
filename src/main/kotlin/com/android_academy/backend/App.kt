package com.android_academy.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(scanBasePackages = ["com.android_academy.backend"])
@EnableScheduling
class WinnerApp

fun main(args: Array<String>) {
	runApplication<WinnerApp>(*args)
}
