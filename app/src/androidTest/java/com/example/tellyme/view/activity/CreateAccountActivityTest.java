package com.example.tellyme.view.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.tellyme.R;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CreateAccountActivityTest {
    @Rule
    public ActivityTestRule<CreateAccountActivity> mCreateAccountActivityTestRule = new ActivityTestRule<CreateAccountActivity>(CreateAccountActivity.class);

    @Test
    public void clickOnSwitchToLogin() throws Exception{
        onView(withId(R.id.switch_to_login)).perform(click());
        onView(withId(R.id.switch_to_create_account)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnCreateAccountWithEmptyFields() throws Exception {
        onView(withId(R.id.create_account)).perform(click());

        onView(withId(R.id.create_account_error_msg)).check(matches(isDisplayed()));
    }


}
