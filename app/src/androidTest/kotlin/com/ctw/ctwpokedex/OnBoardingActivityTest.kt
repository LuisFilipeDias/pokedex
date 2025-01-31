package com.ctw.ctwpokedex

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.ctw.ctwpokedex.presentation.activities.OnboardingActivity
import com.ctw.ctwpokedex.presentation.activities.PokedexActivity
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class OnboardingActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(OnboardingActivity::class.java)

    @Test
    fun button_start_appears() {
        onView(withId(R.id.button_start)).check(matches(isDisplayed()))
        //api call
        onView(withId(R.id.button_start)).check(matches(withText("START")))
    }

    @Test
    fun start_goes_to_main_activity() {
        onView(withId(R.id.button_start)).perform(click())

        intended(hasComponent(PokedexActivity::class.java.name))
    }


    companion object {

        @AfterClass
        @JvmStatic
        fun clear_app_data(){
            val mDevice =  UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            mDevice.executeShellCommand("pm clear com.ctw.ctwpokedex")
        }

        @BeforeClass
        @JvmStatic
        fun initIntents(){
            Intents.init()
        }
    }
}