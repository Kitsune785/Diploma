package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.generatorDate;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.generatorDate2;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.invalidGeneratorDate;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.localDate;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomNews;
import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;
import static ru.iteco.fmhandroid.ui.data.Helper.createNewsForCategory;
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

import java.text.ParseException;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.FilterNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class FilterNewsTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    MainSteps mainSteps = new MainSteps();
    NewsSteps newsSteps = new NewsSteps();
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
    FilterNewsSteps filterNewsSteps = new FilterNewsSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(authInfo());
        } finally {
            deletNewsUpToTheNumberOfTenControlPanel(7);
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("Screen title with news filter should be Filter News")
    @Description("Название экрана с фильтром новостей должно быть Filter News ID 072")
    public void shouldBeNameFilterNews() {
        mainSteps.clickMenuButton();
        mainSteps.clickNewsName();
        newsSteps.clickButtonToGoToFilterNews();
        filterNewsSteps.checkingTheScreenNameForNewsSearch();
    }

    @Test
    @DisplayName("Search news through the filter by all possible criteria")
    @Description("Поиск новости через фильтр по всем возможным критериям ID 073")
    public void shouldBeFoundByAllFilterCriteriaNews() throws ParseException {
        String localDate = localDate();
        String dateStartInput = generatorDate2();
        String dateEndInput = generatorDate();
        String category = randomCategory();
        String text = Helper.Text.textSymbol(4);

        createNewsForCategory(text, category);

        mainSteps.clickMenuButton();
        mainSteps.clickNewsName();

        newsSteps.clickButtonToGoToFilterNews();
        filterNewsSteps.checkingTheScreenNameForNewsSearch();

        filterNewsSteps.enteringSearchData(category, dateStartInput, dateEndInput);
        filterNewsSteps.clickSearchButton();

        int positionNews = Helper.Search.searchNews(text);
        controlPanelSteps.clickOnRandomlySelectedNewsItem(positionNews);
        String dateOnCardNews = newsSteps.dateOnCardNews(positionNews);

        newsSteps.checkingTheFoundDataFromTheNewsWithTheDataEnteredForTheSearch(
                dateOnCardNews, dateStartInput, dateEndInput, category, localDate, positionNews);
    }

    @Test
    @DisplayName("Search news through the filter by category")
    @Description("Поиск новости через фильтр по категории ID 074")
    public void shouldBeFoundNewsByCategory() {
        int position = randomNews(0);
        String category = randomCategory();
        String text = Helper.Text.textSymbol(5);
        createNewsForCategory(text, category);

        mainSteps.clickMenuButton();
        mainSteps.clickNewsName();

        newsSteps.clickButtonToGoToFilterNews();
        filterNewsSteps.checkingTheScreenNameForNewsSearch();

        filterNewsSteps.enterCategory(category);
        filterNewsSteps.clickSearchButton();

        String categoryText = newsSteps.categoryText(position);
        newsSteps.checkDataOfTheFoundNewsWithTheEnteredSearchData(category, categoryText);
    }

    @Test
    @DisplayName("Search news through a filter by date")
    @Description("Поиск новости через фильтр по дате ID 075")
    public void shouldBeFoundNewsFilteredByDate() throws ParseException {
        int position = randomNews(0, 1, 2);
        String localDate = localDate();
        String dateStartInput = generatorDate2();
        String dateEndInput = generatorDate();

        mainSteps.clickMenuButton();
        mainSteps.clickNewsName();
        newsSteps.clickButtonToGoToFilterNews();
        filterNewsSteps.checkingTheScreenNameForNewsSearch();
        filterNewsSteps.enterStartDate(dateStartInput);
        filterNewsSteps.enterEndOfTheDate(dateEndInput);
        filterNewsSteps.clickSearchButton();

        newsSteps.clickNews(position);
        String dateOnCardNews = newsSteps.dateOnCardNews(position);

        newsSteps.checkDateOfTheFoundNewsWithTheDataEnteredForTheSearch(
                dateOnCardNews, dateStartInput, dateEndInput, localDate);
    }

    @Test
    @DisplayName("Searching for news through a filter without setting search criteria")
    @Description("Поиск новости через фильтр без установки критериев поиска ID 076")
    public void shouldBeFoundAllNews() {
        int position = randomNews(0, 1, 2);

        mainSteps.clickMenuButton();
        mainSteps.clickNewsName();
        newsSteps.clickButtonToGoToFilterNews();

        filterNewsSteps.clickSearchButton();
        newsSteps.clickNews(position);
        try {
            newsSteps.checkDisplayOfTheFoundNewsData(position);
        } catch (NoMatchingViewException e) {
            newsSteps.checkDisplayOfTheInscriptionInTheAbsenceOfFoundNews();
        }
    }

    @Test
    @DisplayName("Cancellation of news search through the filter")
    @Description("Отмена поиска новости через фильтр ID 077")
    public void shouldBeCanceledFilterSearch() {
        mainSteps.clickMenuButton();
        mainSteps.clickNewsName();
        newsSteps.clickButtonToGoToFilterNews();
        filterNewsSteps.checkingTheScreenNameForNewsSearch();
        filterNewsSteps.clickCancelSearchButton();
        newsSteps.checkTheNameOfTheNews();
    }

    @Test
    @DisplayName("Notification about the absence of news matching the search criteria by filter")
    @Description("Уведомление об отсутствии новостей подходящих под критерии поиска по фильтру ID 078")
    public void shouldBeNotificationAboutTheAbsenceOfNewsByFilterCriteria() {
        String dateStartInput = invalidGeneratorDate();
        String dateEndInput = invalidGeneratorDate();

        mainSteps.clickMenuButton();
        mainSteps.clickNewsName();
        newsSteps.clickButtonToGoToFilterNews();
        filterNewsSteps.checkingTheScreenNameForNewsSearch();
        filterNewsSteps.enterDates(dateStartInput, dateEndInput);
        filterNewsSteps.clickSearchButton();
        newsSteps.checkDisplayOfTheInscriptionInTheAbsenceOfFoundNews();
    }

    @Test
    @DisplayName("Must be the name of the fields in the news filter")
    @Description("Должны быть название полей в фильтре новостей ID 137")
    public void shouldBeTheNameOfTheFieldsToFill() {
        mainSteps.clickMenuButton();
        mainSteps.clickNewsName();
        newsSteps.clickButtonToGoToFilterNews();
        filterNewsSteps.checkIdentifyingField();
    }
}
