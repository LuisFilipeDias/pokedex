package com.ctw.ctwpokedex.presentation.activities

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctw.ctwpokedex.helpers.openPokedexActivity
import com.ctw.ctwpokedex.presentation.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ctw.ctwpokedex.R

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private val onboardingViewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (onboardingViewModel.hasUserLoggedIn()) {
            navigateToPokedex()
        } else {
            setContentView(R.layout.onboarding_activity)
            findViewById<Button>(R.id.button_start).setOnClickListener {
                onboardingViewModel.setUserLoggedIn()
                navigateToPokedex()
            }
        }
    }

    private fun navigateToPokedex() = this.openPokedexActivity()
}

@Composable
fun PokemonComposition() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFCB05), Color(0xFF3C5AA6)),
                    center = Offset(0.5f, 0.5f),
                    radius = 250f
                )
            )
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "PKM",
            color = Color.White,
            fontSize = 100.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .shadow(20.dp, CircleShape)
                .animateContentSize()
        )
    }
}

@Composable
fun SimpleComposeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFCB05), Color(0xFF3C5AA6)),
                    center = Offset(0.5f, 0.5f),
                    radius = 250f
                )
            )
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "PKM",
            color = Color.White,
            fontSize = 100.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .shadow(20.dp, CircleShape)
                .animateContentSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSimpleComposeScreen() {
    SimpleComposeScreen()
}

/*

/**
 * Copyright (c), BMW Critical TechWorks. All rights reserved.
 */
package com.bmwgroup.apinext.updatecenter.ui.compose.flatscreen

import android.text.format.DateFormat
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.bmwgroup.apinext.ui.common.ExperimentalUiLibApi
import com.bmwgroup.apinext.ui.compose.core.id10.ID10TestTheme
import com.bmwgroup.apinext.updatecenter.R
import com.bmwgroup.apinext.updatecenter.common.types.Date
import com.bmwgroup.apinext.updatecenter.common.types.DefaultTimePoint
import com.bmwgroup.apinext.updatecenter.common.types.Setting
import com.bmwgroup.apinext.updatecenter.common.types.SettingValue
import com.bmwgroup.apinext.updatecenter.common.types.SettingsMap
import com.bmwgroup.apinext.updatecenter.common.types.State
import com.bmwgroup.apinext.updatecenter.common.types.StatusInformation
import com.bmwgroup.apinext.updatecenter.common.types.Time
import com.bmwgroup.apinext.updatecenter.common.types.TimePoint
import com.bmwgroup.apinext.updatecenter.common.types.TimerValue
import com.bmwgroup.apinext.updatecenter.common.types.TimerValueType
import com.bmwgroup.apinext.updatecenter.service.ipa.IpaHandler
import com.bmwgroup.apinext.updatecenter.service.utils.AccountUtils
import com.bmwgroup.apinext.updatecenter.common.usecases.GetSettingsUseCase
import com.bmwgroup.apinext.updatecenter.common.usecases.GetStatusInformationUseCase
import com.bmwgroup.apinext.updatecenter.service.myhighlights.MyHighlightsServiceManager
import com.bmwgroup.apinext.updatecenter.receivers.SystemSettingsObserver
import com.bmwgroup.apinext.updatecenter.ui.compose.usecases.updatecenterlightmode.RespondToNotificationUseCase
import com.bmwgroup.apinext.updatecenter.ui.compose.usecases.updatecenterlightmode.UpdateSettingsUseCase
import com.bmwgroup.apinext.updatecenter.ui.compose.flatscreens.model.screen.FlatMenuTemplatePopupActivityViewModel
import com.bmwgroup.apinext.updatecenter.ui.compose.flatscreens.model.mappers.FlatMenuScreenModelMapper
import com.bmwgroup.apinext.updatecenter.ui.compose.flatscreens.data.Settings
import com.bmwgroup.apinext.updatecenter.ui.compose.flatscreens.view.screen.FlatMenuDownloadInfoScreen
import com.bmwgroup.apinext.updatecenter.ui.compose.flatscreens.view.screen.FlatMenuFailedInstallationScreen
import com.bmwgroup.apinext.updatecenter.ui.compose.flatscreens.view.screen.FlatMenuInstalledSoftwareScreen
import com.bmwgroup.apinext.updatecenter.ui.compose.flatscreens.view.screen.FlatMenuSettingsScreen
import com.bmwgroup.apinext.updatecenter.ui.compose.flatscreens.view.component.FlatMenuVersionInformation
import com.bmwgroup.apinext.updatecenter.ui.compose.fullscreen.model.helpers.FullscreenMapperHelpers
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalUiLibApi::class)
@HiltAndroidTest
class FlatMenuTest {
    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var viewModel: FlatMenuTemplatePopupActivityViewModel
    private val getStatusInformationUseCase = mockk<GetStatusInformationUseCase>()
    private val respondToNotificationUseCase = mockk<RespondToNotificationUseCase>()
    private val updateSettingsUseCase = mockk<UpdateSettingsUseCase>()
    private val getSettingsUseCase = mockk<GetSettingsUseCase>()
    private val statusInfo = mockk<StatusInformation>()
    private val flatMenuScreenModelMapper = mockk<FlatMenuScreenModelMapper>()
    private val systemSettingsObserver = mockk<SystemSettingsObserver>()
    private val accountUtils = mockk<AccountUtils>()
    private val ipaHandler = mockk<IpaHandler>()

    private val newMarketingVersionNumber = "newMarketingVersion"
    private val currentMarketingVersionNumber = "currentMarketingVersion"

    private val is24HourFormat = DateFormat.is24HourFormat(context)
    private val currentDate = LocalDate.now().plusDays(1)
    private val day = currentDate.dayOfMonth.toByte()
    private val month = currentDate.month.value.toByte()
    private val year = currentDate.year

    @Before
    fun setup() {
        hiltRule.inject()
        every { statusInfo.state } returns State.PREPARING_UPDATE
        every { statusInfo.downloadTotalBytes } returns 1
        every { statusInfo.currentMarketingVersion } returns currentMarketingVersionNumber
        every { statusInfo.newMarketingVersion } returns newMarketingVersionNumber
        every { systemSettingsObserver.timeLanguageFlow } returns flowOf(Pair(is24HourFormat, Locale.UK))

        val settingsInfo: Array<SettingsMap> = Array(size = 1) {
            SettingsMap(Setting.CONTINUE_PREPARATION_IN_PARKING, SettingValue())
        }

        every { getStatusInformationUseCase() } returns flowOf(statusInfo)
        every { getSettingsUseCase() } returns flowOf(settingsInfo)

        viewModel = FlatMenuTemplatePopupActivityViewModel(
            updateSettingsUseCase = updateSettingsUseCase,
            respondToNotificationUseCase = respondToNotificationUseCase,
            getStatusInformation = getStatusInformationUseCase,
            getSettingsUseCase = getSettingsUseCase,
            flatMenuScreenModelMapper = flatMenuScreenModelMapper,
            accountUtils = accountUtils,
            ipaHandler = ipaHandler,
            systemSettingsObserver = systemSettingsObserver,
        )
    }

    @Test
    fun testSettingsFlatScreen(): Unit = with(composeTestRule) {
        setContent {
            ID10TestTheme {
                FlatMenuSettingsScreen(
                    currentSettings = Settings(
                        isIceVehicle = false,
                        automaticUpdateEnabled = false,
                        downloadWhenParked = true,
                        isKeyInCar = false,
                        installationTime = "20",
                        timePointString = "02:00 am",
                        timePointValue = DefaultTimePoint(1, Time(2, 0)),
                        is24HourFormat = is24HourFormat,
                    ),
                    onAction = viewModel::onAction,
                    openTimer = false,
                    navArgs = null
                )
            }
        }

        onNodeWithText(context.getString(R.string.rsu_hmi_automatic_installation_flat_menu_hdr)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.rsu_hmi_automatic_installation_flat_menu_lb)).assertIsDisplayed()

        onNodeWithText(context.getString(R.string.rsu_hmi_installation_time_flat_menu_lb)).assertIsDisplayed()

        onNodeWithText(context.getString(R.string.rsu_hmi_download_when_parked_flat_menu_hrd)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.rsu_hmi_download_when_parked_flat_menu_lb)).assertIsDisplayed()
    }

    @Test
    fun testDownloadInfoFlatScreen(): Unit = with(composeTestRule) {
        setContent {
            ID10TestTheme {
                FlatMenuDownloadInfoScreen(viewModel::onAction)
            }
        }

        onNodeWithText(context.getString(R.string.rsu_hmi_download_infos_flat_menu_lb)).assertIsDisplayed()

        onNodeWithText(context.getString(R.string.rsu_download_connect_wifi_bt)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.rsu_hmi_download_infos_flat_menu_wifi_lb)).assertIsDisplayed()

        onNodeWithText(context.getString(R.string.rsu_download_mybmwapp_bt)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.rsu_hmi_download_infos_flat_menu_my_bmw_lb)).assertIsDisplayed()
    }

    @Test
    fun testInstalledSoftwareFlatScreen(): Unit = with(composeTestRule) {
        MyHighlightsServiceManager.bind(context)
        setContent {
            ID10TestTheme {
                FlatMenuInstalledSoftwareScreen(viewModel::onAction, currentMarketingVersionNumber)
            }
        }
        onNodeWithText(
            context.getString(R.string.rsu_hmi_installed_software_version_flat_menu_hrd)
                .format(currentMarketingVersionNumber)
        ).assertIsDisplayed()

        onNodeWithText(
            context.getString(R.string.rsu_hmi_installed_software_version_flat_menu_lb).format("X")
        ).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.rsu_hmi_release_notes_bt)).assertIsDisplayed()

        onNodeWithText(context.getString(R.string.rsu_hmi_installed_software_connected_store_flat_menu_hrd)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.rsu_hmi_installed_software_connected_store_flat_menu_lb)).assertIsDisplayed()

        if (MyHighlightsServiceManager.getMyHighlightsNumber() != "0") {
            onNodeWithText(context.getString(R.string.rsu_hmi_installed_software_my_highlights_flat_menu_hdr)).assertIsDisplayed()
            onNodeWithText(context.getString(R.string.rsu_hmi_installed_software_my_highlights_flat_menu_lb)).assertIsDisplayed()
        }
        MyHighlightsServiceManager.unBind(context)
    }

    @Test
    fun testVersionInformationFlatScreen(): Unit = with(composeTestRule) {
        setContent {
            ID10TestTheme {
                FlatMenuVersionInformation(currentMarketingVersionNumber)
            }
        }

        onNodeWithText(
            context.getString(R.string.rsu_hmi_installed_software_version_flat_menu_lb).format("")
        ).assertIsDisplayed()

        onNodeWithText(
            context.getString(R.string.rsu_hmi_version_number_flat_menu_hrd)
        ).assertIsDisplayed()

        onNodeWithText(
            currentMarketingVersionNumber
        ).assertIsDisplayed()
    }

    @Test
    fun testFailedInstallFlatScreenContentB(): Unit = with(composeTestRule) {
        val automaticEnabled = true
        val startingTime = Time(LocalTime.now().hour.toByte(), LocalTime.now().minute.toByte())
        val startingDay = Date(day, month, year)
        val time = TimerValue(TimerValueType.SET_AUTO, TimePoint(startingTime, startingDay))
        val timeString = FullscreenMapperHelpers.convertTimeToLocalTime(
            Time(startingTime.hour, startingTime.minute),
            is24HourFormat
        )
        val monthText = FullscreenMapperHelpers.getMonthName(
            month,
            TextStyle.SHORT
        )
        setContent {
            ID10TestTheme {
                FlatMenuFailedInstallationScreen(emptyList(), time, automaticEnabled)
            }
        }

        val dayOfWeek = context.getString(R.string.rsu_hmi_start_later_tomorrow_lb).lowercase()

        onNodeWithText(
            context.getString(R.string.rsu_hmi_failed_installed_infos_flat_menu_b_lb)
                .format(dayOfWeek, day, monthText, timeString)
        ).assertIsDisplayed()
    }

    @Test
    fun testFailedInstallFlatScreenContentA(): Unit = with(composeTestRule) {
        val automaticEnabled = false
        val startingTime = Time(LocalTime.now().hour.toByte(), LocalTime.now().minute.toByte())
        val startingDay = Date(day, month, year)
        val time = TimerValue(
            type = TimerValueType.SET_MANUAL,
            timePoint = TimePoint(startingTime, startingDay)
        )

        setContent {
            ID10TestTheme {
                FlatMenuFailedInstallationScreen(emptyList(), time, automaticEnabled)
            }
        }

        onNodeWithText(
            context.getString(R.string.rsu_hmi_failed_installed_infos_flat_menu_a_lb)
        ).assertIsDisplayed()
    }
}

 */