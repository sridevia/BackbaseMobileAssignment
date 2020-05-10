package com.sridevi.backbase.mobileassignment;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.sridevi.backbase.mobileassignment.about.AboutActivity;
import com.sridevi.backbase.mobileassignment.activities.CityListActivity;
import com.sridevi.backbase.mobileassignment.data.CityData;
import com.sridevi.backbase.mobileassignment.fragments.CityListFragment;
import com.sridevi.backbase.mobileassignment.models.impl.CityListModelImpl;
import com.sridevi.backbase.mobileassignment.presenters.CityListPresenter;
import com.sridevi.backbase.mobileassignment.utils.SearchEngine;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class AboutPageInstrumentedTest {

    @Rule
    public ActivityTestRule<AboutActivity> activityTestRule = new ActivityTestRule(AboutActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.sridevi.backbase.mobileassignment", appContext.getPackageName());
    }

    @Test
    public void checkCityName() {

    }

}
