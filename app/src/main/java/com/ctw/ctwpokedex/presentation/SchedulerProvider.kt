package com.ctw.ctwpokedex.presentation

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface SchedulerProvider {
    fun io() = Schedulers.io()
    fun main() = AndroidSchedulers.mainThread()
}

class DefaultSchedulerProvider : SchedulerProvider