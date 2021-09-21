package com.dipesh.finalassignment

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.dipesh.finalassignment.ui.SignupActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class SignupTesting {
    @get: Rule
    val testRule = ActivityScenarioRule(SignupActivity::class.java)

    @Test
    fun checkSignup() {
        onView(withId(R.id.etFirstName))
                .perform((typeText("User")))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.etLastName))
                .perform((typeText("user")))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.etPassword))
                .perform((typeText("123456")))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.etConfirmPass))
                .perform((typeText("123456")))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.etAddress))
                .perform((typeText("qwer")))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.etPhoneNo))
                .perform((typeText("123")))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.etMail))
                .perform((typeText("user22@")))

        Espresso.closeSoftKeyboard()

        onView(withId(R.id.btnSignup))
                .perform((click()))
    }
}