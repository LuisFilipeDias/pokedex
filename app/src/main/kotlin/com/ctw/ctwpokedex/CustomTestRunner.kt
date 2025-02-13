/**
 * Copyright (c) BMW Critical TechWorks. All rights reserved.
 */
package com.ctw.ctwpokedex

import android.app.Application
import android.content.Context
import androidx.multidex.BuildConfig
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.CustomTestApplication

@CustomTestApplication(Application::class)
interface PokedexTester

class CustomTestRunner : AndroidJUnitRunner() {
    //RsuTester_Application will be generated, so may appear as unknown before the first build
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, PokedexTester_Application::class.java.name, context)
    }

}
