package com.arun.factsapp

import androidx.lifecycle.ViewModelProviders
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.arun.factsapp.di.FactRepoInjection
import com.arun.factsapp.ui.main.MainFragment
import com.arun.factsapp.ui.main.viewmodel.FactResponseUI
import com.arun.factsapp.ui.main.viewmodel.FactViewModel
import com.arun.factsapp.ui.main.viewmodel.ViewModelFactory
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecyclerViewTest {

    @Rule @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java,
        true,
        true
    )
    @JvmField
    val fragment: MainFragment = MainFragment.newInstance()

    @Before
    fun setUp(){
        activityRule.activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment).commit()
        fragment.viewModel = ViewModelProviders.of(fragment, ViewModelFactory(FactRepoInjection.provideFactRepository())).get(
            FactViewModel::class.java)
    }

    @Test
    fun testRecyclerVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoadingVisible() {
        fragment.viewModel._response.postValue(
            FactResponseUI(
                true,
                emptyList(),
                isError = false,
                isEmpty = false
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.progressBar))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .check(matches((isDisplayed())))
    }

    @Test
    fun testErrorVisible() {
        fragment.viewModel._response.postValue(
            FactResponseUI(
                false,
                emptyList(),
                isError = true,
                isEmpty = false
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.error_text))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun testEmptyVisible() {
        fragment.viewModel._response.postValue(
            FactResponseUI(
                false,
                emptyList(),
                isError = false,
                isEmpty = true
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.empty_text))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .check(matches(not(isDisplayed())))
    }

}