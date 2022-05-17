package com.example.tellyme.view.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.tellyme.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)


public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    // Menu fragment testing
    @Test
    public void isHomeFragmentVisible() throws Exception{
        onView(withId(R.id.home_frame_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnMessagesButton() throws Exception{
        onView(withId(R.id.home_message_button)).perform(click());
        onView(withId(R.id.message_list)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnCreatePost() throws Exception{
        onView(withId(R.id.openPostWindow)).perform(click());
        onView(withId(R.id.add_movie_to_post_button)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnAddMovieToPostButton() throws Exception {
        onView(withId(R.id.openPostWindow)).perform(click());
        onView(withId(R.id.add_movie_to_post_button)).check(matches(isDisplayed()));
        onView(withId(R.id.add_movie_to_post_button)).perform(click());
        onView(withId(R.id.search_fragment_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnDeletePostButton() throws Exception {
        onView(withId(R.id.openPostWindow)).perform(click());
        onView(withId(R.id.add_movie_to_post_button)).check(matches(isDisplayed()));
        onView(withId(R.id.delete_post_button)).perform(click());
        onView(withText("Delete post")).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnDeletePostPopUpWindow() throws Exception {
        onView(withId(R.id.openPostWindow)).perform(click());
        onView(withId(R.id.add_movie_to_post_button)).check(matches(isDisplayed()));
        onView(withId(R.id.delete_post_button)).perform(click());
        onView(withText("Yes")).perform(click());
        onView(withId(R.id.home_frame_layout)).check(matches(isDisplayed()));

    }

    // My lists fragment testing

    @Test
    public void clickListMenuItem() throws Exception{
        onView(withId(R.id.my_list_item)).perform(click());
        onView(withId(R.id.my_list_fragment)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnShowsListsItem() throws Exception{
        onView(withId(R.id.my_list_item)).perform(click());
        onView(withId(R.id.my_list_fragment)).check(matches(isDisplayed()));


    }

    // Shows fragment testing

    @Test
    public void clickShowsMenuItem() throws Exception{
        onView(withId(R.id.shows_item)).perform(click());
        onView(withId(R.id.watch_list_shows_fragment)).check(matches(isDisplayed()));
    }

    // Movies fragment testing

    @Test
    public void clickMoviesMenuItem() throws Exception{
        onView(withId(R.id.movies_item)).perform(click());
        onView(withId(R.id.watch_list_movies_fragment)).check(matches(isDisplayed()));
    }



    @Test
    public void clickHomeMenuItem() throws Exception{
        onView(withId(R.id.my_list_item)).perform(click());
        onView(withId(R.id.my_list_fragment)).check(matches(isDisplayed()));
        onView(withId(R.id.home_item)).perform(click());
        onView(withId(R.id.home_frame_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void clickAvatarIcon() throws Exception{
        onView(withId(R.id.user_button)).perform(click());
        onView(withId(R.id.profile_cover)).check(matches(isDisplayed()));
    }

    // Search fragment testing

    @Test
    public void clickSearchFloatingActionButton() throws Exception {
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.search_fragment)).check(matches(isDisplayed()));
    }

    @Test
    public void clickSearchShowsIcon() throws Exception {
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.search_fragment)).check(matches(isDisplayed()));
        onView(withId(R.id.search_shows_button)).perform(click());
        onView(withId(R.id.search_shows_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void clickSearchMoviesIcon() throws Exception {
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.search_fragment)).check(matches(isDisplayed()));
        onView(withId(R.id.search_movies_button)).perform(click());
        onView(withId(R.id.search_movies_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void clickSearchPeopleIcon() throws Exception {
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.search_fragment)).check(matches(isDisplayed()));
        onView(withId(R.id.search_people_button)).perform(click());
        onView(withId(R.id.search_people_work_in_progress)).check(matches(isDisplayed()));
    }



}
