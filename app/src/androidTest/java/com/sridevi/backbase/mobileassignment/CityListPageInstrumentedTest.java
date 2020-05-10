package com.sridevi.backbase.mobileassignment;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.sridevi.backbase.mobileassignment.activities.CityListActivity;
import com.sridevi.backbase.mobileassignment.data.CityData;
import com.sridevi.backbase.mobileassignment.fragments.CityListFragment;
import com.sridevi.backbase.mobileassignment.interfaces.CitiesList;
import com.sridevi.backbase.mobileassignment.models.impl.CityListModelImpl;
import com.sridevi.backbase.mobileassignment.presenters.CityListPresenter;
import com.sridevi.backbase.mobileassignment.presenters.impl.CityListPresenterImpl;
import com.sridevi.backbase.mobileassignment.utils.SearchEngine;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CityListPageInstrumentedTest {

    @Rule
    public ActivityTestRule<CityListActivity> activityTestRule = new ActivityTestRule(CityListActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.sridevi.backbase.mobileassignment", appContext.getPackageName());
    }

    @Test
    public void searchWithCityNameTest() {

        String searchString = "ala";

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        FragmentManager fragmentManager = activityTestRule.getActivity().getSupportFragmentManager();
        CityListFragment cityListFragment = (CityListFragment) fragmentManager.findFragmentById(R.id.cityListFragment);

        final CountDownLatch latch = new CountDownLatch(1);

        CityListModelImpl cityListModel = new CityListModelImpl(new CityListPresenter() {
            @Override
            public void getCitiesData() {
            }

            @Override
            public void onSuccess(List<CityData> citiesList) {
                SearchEngine searchEngine = new SearchEngine(citiesList, null);
                List<CityData> citiesFiltered = searchEngine.searchSync(searchString);
                onView(withId(R.id.searchEditText)).perform(typeText(searchString));
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                assertEquals(citiesFiltered.size(), cityListFragment.getRecyclerView().getAdapter().getItemCount());
                latch.countDown();
            }

            @Override
            public void onFail() {
            }
        }, appContext);

        cityListModel.getCitiesData();

    }

}
