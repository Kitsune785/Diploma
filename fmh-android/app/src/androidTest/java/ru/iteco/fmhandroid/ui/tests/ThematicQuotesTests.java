package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.Rand.random;
import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.ThematicQuotesSteps;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ThematicQuotesTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    MainSteps mainSteps = new MainSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    ThematicQuotesSteps thematicQuotesSteps = new ThematicQuotesSteps();

    int position = random(0);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(authInfo());
        } finally {
            mainSteps.clickButtonInTheFormOfButterfly();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(3000);
    }

    @Test
    @DisplayName("The name of the page with quotes should be Love is all")
    @Description("Наименование страницы с цитатами должно быть Love is all ID 027")
    public void shouldBePageTitleLoveIsAll() {
        thematicQuotesSteps.checkName();
    }

    @Test
    @DisplayName("Expand the full content of the quote via the arrow button")
    @Description("Разворачивание полного содержания цитаты чреез кнопку стрелку IS 028")
    public void shouldBeTheContentOfTheQuoteExpanded() {
        thematicQuotesSteps.checkName();
        thematicQuotesSteps.quoteSelection(position);

        String title = thematicQuotesSteps.title(position);
        ViewInteraction title2 = thematicQuotesSteps.title2(position);
        String description = thematicQuotesSteps.description(position);
        ViewInteraction description2 = thematicQuotesSteps.description2(position);

        thematicQuotesSteps.checkText(title, title2, description, description2);

    }
}
