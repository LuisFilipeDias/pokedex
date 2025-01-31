package com.ctw.ctwpokedex.domain.usecases

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimestampUseCase @Inject constructor() {

    operator fun invoke(): LocalDateTime? {
        return LocalDateTime.ofInstant(Instant.now(), ZoneId.of("Atlantic/Azores"))
    }
}