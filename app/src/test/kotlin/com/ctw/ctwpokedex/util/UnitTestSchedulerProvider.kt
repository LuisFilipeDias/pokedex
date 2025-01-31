package com.ctw.ctwpokedex.util

import com.ctw.ctwpokedex.presentation.SchedulerProvider
import io.reactivex.schedulers.Schedulers

class UnitTestSchedulerProvider : SchedulerProvider {
    override fun main() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}