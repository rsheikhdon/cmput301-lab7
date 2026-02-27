package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddCity() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name))
                .perform(typeText("Edmonton"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        // Checks that the ListView shows the city
        onData(is(instanceOf(City.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .check(matches(isDisplayed()));
    }

    @Test
    public void testClearCity() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name))
                .perform(typeText("Edmonton"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name))
                .perform(typeText("Vancouver"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        onView(withId(R.id.button_clear)).perform(click());

        onView(withText("Edmonton")).check(doesNotExist());
        onView(withText("Vancouver")).check(doesNotExist());
    }

    @Test
    public void testListView() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name))
                .perform(typeText("Edmonton"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());


        onData(is(instanceOf(City.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .onChildView(withId(android.R.id.text1))
                .check(matches(withText("Edmonton")));
    }

    @Test
    public void testActivitySwitch() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name))
                .perform(typeText("Edmonton"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        onData(is(instanceOf(City.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Checks the TextView in ShowActivity
        onView(withId(R.id.text_city_name))
                .check(matches(isDisplayed()))
                .check(matches(withText("Edmonton")));
    }

    @Test
    public void testCityNameConsistency() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name))
                .perform(typeText("Edmonton"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        onData(is(instanceOf(City.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.text_city_name))
                .check(matches(withText("Edmonton")));
    }

    @Test
    public void testBackButton() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name))
                .perform(typeText("Edmonton"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        onData(is(instanceOf(City.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.button_back)).perform(click());

        // Checks that MainActivity ListView is displayed again
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}


