package com.example.pendulumtestjava.info;


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
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InfoVideosTest {

    @Rule
    public ActivityTestRule<FirebaseAuthActivity> mActivityTestRule = new ActivityTestRule<>(FirebaseAuthActivity.class);

    @Test
    public void infoVideosTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.singleInfo),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.singleCard),
                                        0),
                                4)));
        appCompatButton.perform(scrollTo(), click());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.doubleInfo),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.doubleCard),
                                        0),
                                4)));
        appCompatButton2.perform(scrollTo(), click());

        pressBack();
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
