package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.generatorDate;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.generatorDate2;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.invalidGeneratorDate;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.random;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomClaims;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomNews;
import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;
import static ru.iteco.fmhandroid.ui.data.Helper.createNews;
import static ru.iteco.fmhandroid.ui.data.Helper.createNewsForCategory;
import static ru.iteco.fmhandroid.ui.data.Helper.deletNewsUpToTheNumberOfTenControlPanel;
import static ru.iteco.fmhandroid.ui.data.Helper.setUpStatusNewsNotActive;

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
import ru.iteco.fmhandroid.ui.steps.AdvancedNewsSearchSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AdvancedNewsSearchTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    MainSteps mainSteps = new MainSteps();
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    AdvancedNewsSearchSteps advancedNewsSearchSteps = new AdvancedNewsSearchSteps();

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
    @DisplayName("The name of the news filter page should be Filter news")
    @Description("Название страницы с фильтром новостей должно быть Filter news ID089")
    public void shouldBeTitleFilterNews() {
        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.checkTheNameOfTheAdvancedSearch();
    }

    @Test
    @DisplayName("Search for news through the filter on the control panel page by all criteria")
    @Description("Поиск новостей через фильтр на странице контрольной панели по всем критериям ID 090")
    public void shouldBeFoundByAllCriteriaNews() throws ParseException {
        String category = randomCategory();
        String dateStartInput = generatorDate2();
        String dateEndInput = generatorDate();
        String text1 = Helper.Text.textSymbol(5);
        String text = text1 + " " + text1;
        createNewsForCategory(text, category);

        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.checkTheNameOfTheAdvancedSearch();

        advancedNewsSearchSteps.checkTheNameOfTheAdvancedSearch();
        advancedNewsSearchSteps.fillInFieldsWithSearchData(category, dateStartInput, dateEndInput);
        advancedNewsSearchSteps.clickOnTheFilterButtonToSearchForNews();

        int positionNews = Helper.Search.searchNews(text);
        controlPanelSteps.clickOnRandomlySelectedNewsItem(positionNews);
        String dateOnCardNews = controlPanelSteps.dateOnCardNews(positionNews);
        controlPanelSteps.comparisonOfSearchDataWithNewsData(dateOnCardNews, dateStartInput, dateEndInput, positionNews, category);
    }

    @Test
    @DisplayName("Search news through the filter on the control panel page by category")
    @Description("Поиск новости через фильтр на странице контрольной панели по категории ID 091")
    public void shouldBeFoundByCategoryNews() {
        String category = randomCategory();
        String text = Helper.Text.textSymbol(5);
        createNewsForCategory(text, category);

        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.checkTheNameOfTheAdvancedSearch();

        advancedNewsSearchSteps.fillInTheCategoryField(category);

        advancedNewsSearchSteps.clickOnTheFilterButtonToSearchForNews();

        int positionNews = Helper.Search.searchNews(text);
        controlPanelSteps.checkTheEnteredDataForTheSearchWithThoseObtainedFromTheNews(category, positionNews);
    }

    @Test
    @DisplayName("Search for news through the filter on the control panel page by date")
    @Description("Поиск новости через фильтр на странице контрольной панели по дате ID 092")
    public void mustSearchByDate() throws ParseException {
        int position = random(1, 2);
        String dateStartInput = generatorDate2();
        String dateEndInput = generatorDate();

        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.checkTheNameOfTheAdvancedSearch();
        advancedNewsSearchSteps.fillInFieldsForDateSearch(dateStartInput, dateEndInput);
        advancedNewsSearchSteps.clickOnTheFilterButtonToSearchForNews();

        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);
        controlPanelSteps.comparisonOfSearchDataByDateWithNewsData(position, dateStartInput, dateEndInput);
    }

    @Test
    @DisplayName("Search for news through a filter on the control panel page without selected criteria")
    @Description("Поиск новостей через фильтр на странице контрольной панеле без выбранных критериев ID 093")
    public void shoulBeFindAllNewsWhenSearchingWithoutCriteria() {
        int position = random( 1, 2);

        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.checkTheNameOfTheAdvancedSearch();
        advancedNewsSearchSteps.clickOnTheFilterButtonToSearchForNews();
        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);
        controlPanelSteps.checkTheFoundNews(position);
    }

    @Test
    @DisplayName("Cancel news search through the filter on the control panel page")
    @Description("Отмена поиска новостей через фильтр на странице контрольной панели ID 094")
    public void cancelingTheSearch() {
        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.checkTheNameOfTheAdvancedSearch();
        advancedNewsSearchSteps.clickOnTheCancelSearchButton();
        controlPanelSteps.checkTheNameOfTheControlPanel();
    }

    @Test
    @DisplayName("Error notification when searching for news through the filter on the control panel page for inappropriate criteria")
    @Description("Уведомление об ошибке при поиске новостей через фильтр на странице контрольной панели по неподходящим критериям ID 095")
    public void shouldBeAnErrorOnTheSearchForInvalidDilterCriteria() {
        String dateStartInput = invalidGeneratorDate();
        String dateEndInput = invalidGeneratorDate();

        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.checkTheNameOfTheAdvancedSearch();
        advancedNewsSearchSteps.fillInTheFieldsForTheDate(dateStartInput, dateEndInput);
        advancedNewsSearchSteps.clickOnTheFilterButtonToSearchForNews();
        advancedNewsSearchSteps.checkTheTextWhenNewsIsNotFound();
    }

    @Test
    @DisplayName("Search for news through the filter on the control panel page by status Active")
    @Description("Поиск новостей через фильтр на странице контрольной панели по статусу Active ID 096")
    public void shouldBeFindNewsWithActiveStatus() {
        int position = randomNews( 1);

        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);

        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.checkTheNameOfTheAdvancedSearch();
        advancedNewsSearchSteps.clickOnTheActiveCheckBox();
        advancedNewsSearchSteps.clickOnTheFilterButtonToSearchForNews();

        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);
        controlPanelSteps.checkTheStatusActive(position);
    }

    @Test
    @DisplayName("Search for news through the filter on the control panel page by status Not Active")
    @Description("Поиск новостей через фильтр на странице контрольной панели по статусу Not Active ID 097")
    public void shouldBeFindNewsWithTheStatusNotActive() {
        String category = randomCategory();
        String text = Helper.Text.textSymbol(5);
        createNews(text, category);

        int positionNews = Helper.Search.searchNews(text.trim());
        setUpStatusNewsNotActive(positionNews);

        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.clickOnTheNotActiveCheckBox();
        advancedNewsSearchSteps.clickOnTheFilterButtonToSearchForNews();

        controlPanelSteps.clickOnRandomlySelectedNewsItem(positionNews);
        controlPanelSteps.checkTheStatusNotActive(positionNews);
    }

    @Test
    @DisplayName("Search for news through the filter on the control panel page by status Active and Not Active")
    @Description("Поиск новостей через фильтр на странице контрольной панели по статусу Active и Not Active ID 098")
    public void shouldSearchByStatusAndActiveNotActive() {
        int position = randomClaims(0, 1);

        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.clickOnTheFilterButtonToSearchForNews();

        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);
        controlPanelSteps.checkTheStatusOfTheFoundNews(position);
    }

    @Test
    @DisplayName("Name of fields on the control panel page when searching")
    @Description("Название полей на странице контрольной панели при поиске ID 112")
    public void shouldBeFieldNames() {
        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.checkTheVisibilityOfIdentifyingFieldNames();
    }

    @Test
    @DisplayName("The name of the checkboxes on the control panel page when searching")
    @Description("Название чек-боксов на странцие контрольной панели при поиске ID 113")
    public void shouldBeTheNamesOfTheCheckboxes() {
        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        advancedNewsSearchSteps.checkTheVisibilityOfTheNamesOfCheckBoxes();
    }
}
