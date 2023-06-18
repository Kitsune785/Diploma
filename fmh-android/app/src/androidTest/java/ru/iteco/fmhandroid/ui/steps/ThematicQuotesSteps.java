package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.ThematicQuotesElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ThematicQuotesSteps {

    ThematicQuotesElements thematicQuotesElements = new ThematicQuotesElements();

    public void checkName() {
        Allure.step("Проветка названия страницы");
        thematicQuotesElements.getLoveIsAll().check(matches(isDisplayed()));
        thematicQuotesElements.getLoveIsAll().check(matches(withText("Love is all")));
        SystemClock.sleep(3000);
    }

    public void quoteSelection(int position) {
        Allure.step("Выбор цитаты");
        thematicQuotesElements.getRecyclerView().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    public void checkText(String title, ViewInteraction title2, String description, ViewInteraction description2) {
        Allure.step("Проверка названия и описания");
        assertEquals("«Хоспис для меня - это то, каким должен быть мир.", title);
        title2.check(matches(isDisplayed()));
        assertEquals("Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер", description);
        description2.check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public String title(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.our_mission_item_title_text_view), position))).replaceAll("^\"|\"$", "");
    }

    public String description(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.our_mission_item_description_text_view), position))).replaceAll("^\"|\"$", "");
    }

    public ViewInteraction title2(int position) {
        return onView(withIndex(withId(R.id.our_mission_item_title_text_view), position));
    }

    public ViewInteraction description2(int position) {
        return onView(withIndex(withId(R.id.our_mission_item_description_text_view), position));
    }

    public void openDescription() {
        // Allure.step("Нажатие на кнопку развернуть/свернуть описание цитаты");
        thematicQuotesElements.getQuoteTitle().perform(click());
        SystemClock.sleep(3000);
    }
}
