package com.dipesh.finalassignment

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.dipesh.finalassignment.ui.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4::class)
class LoginTest {

    @get:Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun checkLoginUI(){
        onView(withId(R.id.etEmail))
                .perform(ViewActions.typeText("dipesh@"))

        onView(withId(R.id.etPassword))
                .perform(ViewActions.typeText("123456"))

        closeSoftKeyboard()

        onView(withId(R.id.btnLogin))
                .perform(ViewActions.click())
    }
}