package com.example.artspace

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.example.artspace.ui.theme.ArtSpaceTheme

import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class ArtSpaceUITests {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun changingArtworkUsingButtons() {
        val expectedTitle = listOf(
            R.string.art_1_name,
            R.string.art_2_name,
            R.string.art_3_name,
        ).map {
            composeTestRule.activity.getString(it)
        }

        composeTestRule.onNodeWithText(expectedTitle[0]).assertExists()

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText(expectedTitle[0]).assertDoesNotExist()
        composeTestRule.onNodeWithText(expectedTitle[1]).assertExists()

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText(expectedTitle[1]).assertDoesNotExist()
        composeTestRule.onNodeWithText(expectedTitle[2]).assertExists()

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText(expectedTitle[2]).assertDoesNotExist()
        composeTestRule.onNodeWithText(expectedTitle[0]).assertExists()

        composeTestRule.onNodeWithText("Previous").performClick()
        composeTestRule.onNodeWithText(expectedTitle[0]).assertDoesNotExist()
        composeTestRule.onNodeWithText(expectedTitle[2]).assertExists()

        composeTestRule.onNodeWithText("Previous").performClick()
        composeTestRule.onNodeWithText(expectedTitle[2]).assertDoesNotExist()
        composeTestRule.onNodeWithText(expectedTitle[1]).assertExists()


        composeTestRule.onNodeWithText("Previous").performClick()
        composeTestRule.onNodeWithText(expectedTitle[1]).assertDoesNotExist()
        composeTestRule.onNodeWithText(expectedTitle[0]).assertExists()
    }
}
