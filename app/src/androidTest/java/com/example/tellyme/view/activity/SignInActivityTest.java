package com.example.tellyme.view.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.tellyme.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class SignInActivityTest {

    @Rule
    public ActivityTestRule<SignInActivity> mSignInActivityTestRule = new ActivityTestRule<SignInActivity>(SignInActivity.class);


    @Test
    public void clickLoginWithEmailButton() throws Exception {
        onView(withId(R.id.login_with_email_button)).perform(click());
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
    }

    @Test
    public void clickCreateAccountButton() throws Exception {
        onView(withId(R.id.create_account_button)).perform(click());
        onView(withId(R.id.create_account)).check(matches(isDisplayed()));
    }

    @Test
    public void continueWithGoogle() throws Exception {
        onView(withId(R.id.google_authentication_button)).perform(click());
    }

    @Test
    public void continueWithFacebook() throws Exception {
        onView(withId(R.id.facebook_authentication_button)).perform(click());
    }

}