package com.example.pendulumtestjava.saving_delete;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.firebase.FirebaseAuthActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TimeSavingCheck_DeletePendulumsTests {

    @Rule
    public ActivityTestRule<FirebaseAuthActivity> mActivityTestRule = new ActivityTestRule<>(FirebaseAuthActivity.class);

    @Test
    public void timeSavingCheck_DeletePendulumsTests() {
        //******************************* The below tests were created with the current 'lastPlayed'
        //******************************* data, so in case of a new test, should be replaced with the current 'lastPlayed' values
        ViewInteraction textView = onView(
                allOf(withId(R.id.lastPlayedSingle), withText("2020-06-13 16:57:47"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.singleCard),
                                        0),
                                3),
                        isDisplayed()));
        textView.check(matches(withText("2020-06-13 16:57:47")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.lastPlayedDouble), withText("2020-06-13 16:57:49"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.doubleCard),
                                        0),
                                3),
                        isDisplayed()));
        textView2.check(matches(withText("2020-06-13 16:57:49")));

        ViewInteraction tabView = onView(
                allOf(withContentDescription("Saved"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tablayout_id),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1),
                        3),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1),
                        3),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction floatingActionButton3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1),
                        3),
                        isDisplayed()));
        floatingActionButton3.perform(click());

        ViewInteraction floatingActionButton5 = onView(
                allOf(withId(R.id.deleteSingle),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        isDisplayed()));
        floatingActionButton5.perform(click());

        ViewInteraction floatingActionButton6 = onView(
                allOf(withId(R.id.deleteDouble),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                1),
                        isDisplayed()));
        floatingActionButton6.perform(click());

        ViewInteraction floatingActionButton7 = onView(
                allOf(withId(R.id.deleteAll),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                2),
                        isDisplayed()));
        floatingActionButton7.perform(click());

        ViewInteraction floatingActionButton8 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1),
                        3),
                        isDisplayed()));
        floatingActionButton8.perform(click());

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("Pendulums"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tablayout_id),
                                        0),
                                0),
                        isDisplayed()));
        tabView2.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
