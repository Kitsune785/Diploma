package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomClaims;
import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.FilteringWindowSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class FilteringTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    MainSteps mainSteps = new MainSteps();
    ClaimsSteps claimsSteps = new ClaimsSteps();
    FilteringWindowSteps filteringWindowSteps = new FilteringWindowSteps();

    int position = randomClaims(0, 1, 2);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(authInfo());
        } finally {
            mainSteps.clickMenuButton();
            mainSteps.clickClaimsName();
            claimsSteps.clickOnTheButtonToGoToTheFiltering();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("The name of the page with the filter on the claims page should be Filtering")
    @Description("Название страницы с фильтром на странице претензий должно быть Filtering ID 099")
    public void shouldBeTitleFiltering() {
        filteringWindowSteps.checkScreenNameFiltering();
    }

    @Test
    @DisplayName("Search for a claim through the filter on the claims page by status Open")
    @Description("Поиск претензии через фильтр на странице претензий по статусу Open ID 100")
    public void shouldBeSearchByStatusOpen() {
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickCheckBoxOpen();
        filteringWindowSteps.clickRandomlySelectedCheckBox();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        filteringWindowSteps.checkStatus();
    }

    @Test
    @DisplayName("Search for a claim through the filter on the claims page by status In progress")
    @Description("Поиск претензии через фильтр на странице претензий по статусу In progress ID 101")
    public void shouldBeSearchByStatusInProgress() {
        filteringWindowSteps.clickCheckBoxOpen();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkTheInProgressStatus();
    }

    @Test
    @DisplayName("Search for a claim through the filter on the claims page by status Executed")
    @Description("Поиск претензии через фильтр на странице претензий по статусу Executed ID 102")
    public void shouldBeSearchByStatusExecuted() {
        filteringWindowSteps.clickCheckBoxOpen();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickCheckBoxExecuted();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkTheExecutedStatus();
    }

    @Test
    @DisplayName("Search for a claim through the filter on the claims page by status Cancelled")
    @Description("Поиск претензии через фильтр на странице претензий по статусу Cancelled ID 103")
    public void shouldBeSearchByStatusCancelled() {
        filteringWindowSteps.clickCheckBoxOpen();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickCheckBoxCancelled();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkTheCanceledStatus();
    }

    @Test
    @DisplayName("Search for a claim through the filter on the claims page by all statuses")
    @Description("Поиск претензии через фильтр на странице претензий по всем статусам ID 104")
    public void shouldBeSearchByAllStatus() {
        filteringWindowSteps.clickCheckBoxExecuted();
        filteringWindowSteps.clickCheckBoxCancelled();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        filteringWindowSteps.checkStatus();
    }

    @Test
    @DisplayName("Claims search error notification with invalid search parameters")
    @Description("Уведомление об ошибке поиска претензий при невалидных параметрах поиска ID 105")
    public void shouldBeAnErrorNotificationWhenSearching() {
        filteringWindowSteps.clickCheckBoxOpen();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickButtonOk();
        filteringWindowSteps.checkMessageForUndiscoveredClaims();
    }

    @Test
    @DisplayName("Cancel search for claims through the filter on the claims page")
    @Description("Отмена поиска претензий через фильтр на странице претензий ID 106")
    public void shouldBeCanceledFilterSearch() {
        filteringWindowSteps.checkScreenNameFiltering();
        filteringWindowSteps.clickExitFilteringButton();
        filteringWindowSteps.checkMissingNameFiltering();
        claimsSteps.checkNameClaims();
    }
}
