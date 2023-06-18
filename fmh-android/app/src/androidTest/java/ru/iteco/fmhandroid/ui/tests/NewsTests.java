package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;
import static ru.iteco.fmhandroid.ui.data.Helper.deletNewsUpToTheNumberOfTenControlPanel;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
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
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    MainSteps mainSteps = new MainSteps();
    NewsSteps newsSteps = new NewsSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();

    int position = 0;

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(authInfo());
        } finally {
            deletNewsUpToTheNumberOfTenControlPanel(6);
            mainSteps.clickMenuButton();
            mainSteps.clickNewsName();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("Наименование страницы с новостями должно быть News")
    @Description("The title of the news page should be  News ID 041")
    public void shouldBeTheNameNews() {
        newsSteps.checkTheNameOfTheNews();
    }

    @Test
    @DisplayName("On the news page, when you click on the three dots and stripes button, the news filter comes off")
    @Description("На странице новостей при нажатии на кнопку три точки и полоски отрывается фильтр новостей ID 042")
    public void shouldBeOpenTheFilterPage() {
        newsSteps.clickButtonToGoToFilterNews();
        newsSteps.checkNameFilterNews();
    }

    @Test
    @DisplayName("On the news page, when you press the notepad and pencil button, it opens Control panel")
    @Description("На странице новостей при нажатии кнопки блокнот и карандаш открывается Control panel ID 043")
    public void shouldBeOpenControlPanel() {
        newsSteps.clickButtonToGoToTheControlPanel();
        controlPanelSteps.checkTheNameOfTheControlPanel();
    }

    @Test
    @DisplayName("On the news page, when you click on the collapse/expand news arrow, the description of the news is displayed")
    @Description("На странице новостей при нажатии на стрелку свернуть/развернуть новость отображается описание новости ID 044")
    public void shouldBeVisibleTheDescriptionOfTheNews() {
        newsSteps.clickExpandNewsDescriptionButton(position);
        newsSteps.checkTextOfTheNewsDescriptionIsVisible(position);
    }
}
