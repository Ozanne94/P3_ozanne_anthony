
package com.openclassrooms.entrevoisins.neighbour_list;

import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;




/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // Faites d'abord défiler jusqu'à la position qui doit être mise en correspondance et cliquez dessus.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * Lorsque nous supprimons un élément, l'élément n'est plus affiché
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // On enlève l'élément à la position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // Lorsque vous effectuez un clic sur une icône de suppression
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Alors : le nombre d'élément est 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }


    /**
     * Vérifiez que la page DetailNeighbour est lancée
     */
    @Test
    public void checkDetailNeighbourLaunch() {
    Intents.init();
    // Cliquez sur le premier élément de la liste
    onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    // Nous vérifions que les éléments de cette nouvelle activité existent
    intended(hasComponent(DetailNeighbour.class.getName()));
    }

    /**
     * Test de vérification que le nom d'utilisateur est correctement renseigné
     */
    @Test
    public void checkUserName() {

        // Cliquez sur le premier élément de la liste
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        // On vérifie que le TextView indiquant le nom de l'utilisateur est bien renseigné
        ViewInteraction textView = onView(
                allOf(withId(R.id.textName), withText("Caroline"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));
    }

    /**
     * Test de vérification que l'onglet Favoris n'affiche que les voisins marqués comme favoris
     */

    @Test
    public void checkFavoriteNeighbour() {

        // affiche les détails en cliquant sur un voisin
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));
        onView(withId(R.id.activity_detail_neighbour)).check(matches(isDisplayed()));
        // clique sur le bouton favoris
        onView(withId(R.id.addFavorite)).perform(click());
        // retour sur la liste des voisins
        onView(Matchers.allOf(withContentDescription("Navigate up"),isDisplayed())).perform(click());
        // aller sur la liste des favoris
        onView(withId(R.id.container)).perform(scrollRight());
        // vérifier si la liste des favoris contient au moins le dernier voisin ajouté
        onView(withId(R.id.list_favorite_neighbours)).check(matches(isDisplayed()));
        onView(withId(R.id.list_favorite_neighbours)).check(withItemCount(1));




    }

}


