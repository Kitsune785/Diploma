package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCheckBox;
import static ru.iteco.fmhandroid.ui.data.Helper.checkingStatus;

import android.os.SystemClock;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.ClaimsElements;
import ru.iteco.fmhandroid.ui.elements.FilteringWindowElements;
import io.qameta.allure.kotlin.Allure;

public class FilteringWindowSteps {

    MainSteps mainSteps = new MainSteps();
    ClaimsSteps claimsSteps = new ClaimsSteps();
    ClaimsElements claimsElements = new ClaimsElements();
    FilteringWindowElements filteringWindowElements = new FilteringWindowElements();

    public void clickButtonOk() {
        Allure.step("Нажатие на кнопку ok");
        filteringWindowElements.getOkButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickExitFilteringButton() {
        Allure.step("Нажатие на кнопку выхода из Filtering");
        filteringWindowElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }
    
    public void clickCheckBoxInProgress() {
        Allure.step("Нажатие на чек-бокс In progress");
        filteringWindowElements.getCheckBoxInProgress().perform(click());
        SystemClock.sleep(3000);
    }
    
    public void clickCheckBoxOpen() {
        Allure.step("Нажатие на чек-бокс Open");
        filteringWindowElements.getCheckBoxOpen().perform(click());
        SystemClock.sleep(3000);
    }
    
    public void clickCheckBoxExecuted() {
        Allure.step("Нажатие на чек-бокс Executed");
        filteringWindowElements.getCheckBoxExecuted().perform(click());
        SystemClock.sleep(3000);
    }
    
    public void clickCheckBoxCancelled() {
        Allure.step("Нажатие на чек-бокс Cancelled");
        filteringWindowElements.getCheckBoxCancelled().perform(click());
        SystemClock.sleep(3000);
    }

    public void checkStatus() {
        Allure.step("Проверка статуса");
        checkingStatus(Helper.Text.getText(claimsElements.getStatus()));
        SystemClock.sleep(3000);
    }

    public void checkMessageForUndiscoveredClaims() {
        Allure.step("Проверка сообщения при ненайденных претензиях");
        onView(withText(startsWith("There is nothing here yet"))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkScreenNameFiltering() {
        Allure.step("Проверка названия экрана filtering");
        filteringWindowElements.getFilteringNameScreen().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkMissingNameFiltering() {
        Allure.step("Проверка отсутствия название страницы Filtering");
        claimsElements.getScreenNameClaims().check(matches(not(filteringWindowElements.getFilteringNameScreen())));
        SystemClock.sleep(3000);
    }



    public void clickRandomlySelectedCheckBox() {
      //  Allure.step("Нажатие на выбранный случайным образом чек бокс");
        randomCheckBox();
        SystemClock.sleep(3000);
    }

    public void switchingToFilteringWindow() {
        //   Allure.step("Переход к экрану Filtering");
        mainSteps.clickMenuButton();
        mainSteps.clickClaimsName();
        SystemClock.sleep(3000);
        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        SystemClock.sleep(3000);
    }
}
