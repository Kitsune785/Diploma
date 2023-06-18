package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.os.SystemClock;

import ru.iteco.fmhandroid.ui.elements.FilterNewsElements;
import io.qameta.allure.kotlin.Allure;

public class FilterNewsSteps {

    FilterNewsElements filterNewsElements = new FilterNewsElements();

    public void enteringSearchData(String category, String dateStartInput, String dateEndInput) {
        Allure.step("Ввод данных для поиска");
        filterNewsElements.getCategoryField().perform(replaceText(category));
        SystemClock.sleep(2000);
        filterNewsElements.getDateStartField().perform(replaceText(dateStartInput));
        SystemClock.sleep(2000);
        filterNewsElements.getDateEndField().perform(replaceText(dateEndInput));
        SystemClock.sleep(3000);
    }

    public void clickCancelSearchButton() {
        Allure.step("Нажатие на кнопку отмены поиска");
        filterNewsElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickSearchButton() {
        Allure.step("Нажатие на кнопку поиска");
        filterNewsElements.getFilterButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void enterCategory(String category) {
        Allure.step("Ввод категории");
        filterNewsElements.getCategoryField().perform(replaceText(category));
        SystemClock.sleep(3000);
    }

    public void enterStartDate(String dateStartInput) {
        Allure.step("Ввод начала даты");
        filterNewsElements.getDateStartField().perform(replaceText(dateStartInput));
        SystemClock.sleep(3000);
    }

    public void enterEndOfTheDate(String dateEndInput) {
        Allure.step("Ввод конечной даты");
        filterNewsElements.getDateEndField().perform(replaceText(dateEndInput));
        SystemClock.sleep(3000);
    }

    public void enterDates(String dateStartInput, String dateEndInput) {
        Allure.step("Ввод дат");
        filterNewsElements.getDateStartField().perform(replaceText(dateStartInput));
        filterNewsElements.getDateEndField().perform(replaceText(dateEndInput));
        SystemClock.sleep(3000);
    }

    public void checkingTheScreenNameForNewsSearch() {
       Allure.step("Проверка названия страницы поиска");
        filterNewsElements.getNameFilterNews().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkIdentifyingField() {
        Allure.step("Идентифицирующие поля");
        filterNewsElements.getFieldNameCategory().check(matches(isDisplayed()));
        filterNewsElements.getFieldNameStartDate().check(matches(isDisplayed()));
        filterNewsElements.getFieldNameEndDate().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
}
