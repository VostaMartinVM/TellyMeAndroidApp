package com.example.tellyme.view.activity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.tellyme.R;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)


public class UserPageActivityActivityTest {


    @Rule
    public ActivityTestRule<UserPageActivity> mUserPageActivityTestRule = new ActivityTestRule<UserPageActivity>(UserPageActivity.class);

    @Test
    public void clickOnLogoutMenuItem() throws Exception{
        openActionBarOverflowOrOptionsMenu(
                ApplicationProvider.getApplicationContext());
        onView(withText("Log out")).perform(click());
        onView(withId(R.id.login_with_email_button)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnSettingsMenuItem() throws Exception{
        openActionBarOverflowOrOptionsMenu(
                ApplicationProvider.getApplicationContext());
        onView(withText("Settings")).perform(click());
        onView(withId(R.id.settings_work_in_rpogress)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnAboutMenuItem() throws Exception{
        openActionBarOverflowOrOptionsMenu(
                ApplicationProvider.getApplicationContext());
        onView(withText("About")).perform(click());
        onView(withId(R.id.about_text)).check(matches(isDisplayed()));
    }

    @Test
    public void clickProfilePicture() throws Exception {

    }

    @Test
    public void clickProfileCover() throws Exception {
        onView(withId(R.id.profile_cover)).perform(click());
    }

}
