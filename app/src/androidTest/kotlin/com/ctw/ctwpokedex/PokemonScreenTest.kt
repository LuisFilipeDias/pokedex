package com.ctw.ctwpokedex

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.ctw.ctwpokedex.data.models.Pokemon
import com.ctw.ctwpokedex.presentation.UiState
import com.ctw.ctwpokedex.presentation.activities.PokemonActivity
import com.ctw.ctwpokedex.presentation.viewmodel.PokemonViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class PokemonScreenTest {

    @get:Rule
    val rule = createAndroidComposeRule<PokemonActivity>()

    lateinit var viewModel: PokemonViewModel

    fun setUp() {
        viewModel = rule.activity.viewModel

        viewModel.pokemonLiveData.postValue(
            UiState.Display(
                Pokemon(
                    id = 25,
                    name = "Pikachu",
                    sprites = null,
                    sprite = "some-url",
                    weight = 0,
                    height = 0
                )
            )
        )

    }

    @Test
    fun testSomething() {
        rule.onRoot().printToLog("MY TAG")

        rule.onNodeWithText("#1").assertExists()
        rule.onAllNodesWithText("defaultPoke").assertCountEquals(2)

        rule.onNodeWithContentDescription("Pokemon Image").assertExists()
        rule.onNodeWithText("Pokemon is gone!").assertDoesNotExist()
        rule.onNodeWithText("Wanna bring it back?").assertDoesNotExist()
        rule.onNodeWithTag("ButtonA").assertDoesNotExist()
        rule.onNodeWithTag("ButtonB").assertDoesNotExist()

        rule.mainClock.autoAdvance = false
        rule.onNodeWithText("Filled").performClick()

        rule.waitForIdle()
        rule.mainClock.advanceTimeByFrame()
        rule.mainClock.advanceTimeBy(5000)

        rule.onNodeWithText("Pokemon is gone!").assertExists()
        rule.onNodeWithText("Wanna bring it back?").assertExists()
        rule.onNodeWithTag("ButtonA").assertExists()
        rule.onNodeWithTag("ButtonB").assertExists()
        rule.onNodeWithContentDescription("Pokemon Image").assertDoesNotExist()

        rule.mainClock.autoAdvance = true
        rule.onNodeWithText("Dismiss").performClick()

        rule.onNodeWithText("Pokemon is gone!").assertExists()
        rule.onNodeWithText("Wanna bring it back?").assertExists()
        rule.onNodeWithTag("ButtonA").assertExists()
        rule.onNodeWithTag("ButtonB").assertExists()
        rule.onNodeWithContentDescription("Pokemon Image").assertDoesNotExist()

        rule.onNodeWithText("Confirm").performClick()

        rule.onNodeWithText("#1").assertExists()
        rule.onAllNodesWithText("defaultPoke").assertCountEquals(2)

        rule.onNodeWithContentDescription("Pokemon Image").assertExists()
        rule.onNodeWithText("Pokemon is gone!").assertDoesNotExist()
        rule.onNodeWithText("Wanna bring it back?").assertDoesNotExist()
        rule.onNodeWithTag("ButtonA").assertDoesNotExist()
        rule.onNodeWithTag("ButtonB").assertDoesNotExist()
    }
}