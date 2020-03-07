package com.openclassrooms.entrevoisins.neighbour_list;

import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.FavoritesFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.allOf;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Timestamp;

@RunWith(AndroidJUnit4.class)
public class FavoritesTest {
    private static int FAVORITES_COUNT=1;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);


    @Test
    public void FavoritesAdd(){

        onView(withId(R.id.list_neighbours)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, MyViewAction.clickChildViewWithId(R.id.item_list_name)));
        onView(withId(R.id.Favori_Add)).perform(click());
        onView(withId(R.id.Detailed_Neighbour)).perform(pressBack());
        onView(ViewMatchers.withText("Favorites"))
                .perform(click());
        //onView(withId(R.id.list_neighbours_favorites)).check(matches(isDisplayed()));

        //onView(withId(R.id.container)).perform(swipeLeft());

        onView(allOf(withId(R.id.item_list_name), withText("Jack"),isDisplayed())).check(matches(withText("Jack")));

    }
    @Test
    public void FavoritesTabShowFavortite() {
        onView(ViewMatchers.withText("Favorites"))
                .perform(click());
        onView(withId(R.id.list_neighbours_favorites)).check(withItemCount(FAVORITES_COUNT));
    }
}
