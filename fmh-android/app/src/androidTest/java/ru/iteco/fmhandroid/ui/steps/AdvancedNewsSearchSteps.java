package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.startsWith;

import android.os.SystemClock;

import ru.iteco.fmhandroid.ui.elements.AdvancedNewsSearchElements;
import io.qameta.allure.kotlin.Allure;

public class AdvancedNewsSearchSteps {
    AdvancedNewsSearchElements advancedNewsSearchElements = new AdvancedNewsSearchElements();

    public void checkTheNameOfTheAdvancedSearch() {
        Allure.step("Названия экрана расширенного поиска");
        advancedNewsSearchElements.getNamePage().check(matches(isDisplayed()));
    }

    public void fillInFieldsForDateSearch(String dateStartInput, String dateEndInput) {
        Allure.step("Заполнение полей поиска по дате");
        advancedNewsSearchElements.getDateStartField().perform(replaceText(dateStartInput));
        SystemClock.sleep(3000);
        advancedNewsSearchElements.getDateEndField().perform(replaceText(dateEndInput));
        SystemClock.sleep(3000);
    }

    public void fillInTheCategoryField(String category) {
        Allure.step("Заполнение поля категории");
        advancedNewsSearchElements.getFieldNameCategory().perform(replaceText(category));
        SystemClock.sleep(3000);
    }

    public void checkTheVisibilityOfIdentifyingFieldNames() {
        Allure.step("Видимость названий полей");
        advancedNewsSearchElements.getFieldNameCategory().check(matches(isDisplayed()));
        advancedNewsSearchElements.getFieldNameStartDate().check(matches(isDisplayed()));
        advancedNewsSearchElements.getFieldNameEndDate().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheVisibilityOfTheNamesOfCheckBoxes() {
        Allure.step("Видимость названий чек-боксов");
        advancedNewsSearchElements.getCheckBoxActive().check(matches(isDisplayed()));
        advancedNewsSearchElements.getCheckBoxNotActive().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void fillInFieldsWithSearchData(String category, String dateStartInput, String dateEndInput) {
        Allure.step("Заполнение полей данными для поиска");
        advancedNewsSearchElements.getFieldNameCategory().perform(replaceText(category));
        SystemClock.sleep(3000);
        advancedNewsSearchElements.getFieldNameStartDate().perform(replaceText(dateStartInput));
        SystemClock.sleep(3000);
        advancedNewsSearchElements.getFieldNameEndDate().perform(replaceText(dateEndInput));
        SystemClock.sleep(3000);
    }

    public void fillInTheFieldsForTheDate(String dateStartInput, String dateEndInput) {
        Allure.step("Заполнение полей для даты");
        advancedNewsSearchElements.getDateStartField().perform(replaceText(dateStartInput));
        SystemClock.sleep(3000);
        advancedNewsSearchElements.getDateEndField().perform(replaceText(dateEndInput));
        SystemClock.sleep(3000);
    }

    public void checkTheTextWhenNewsIsNotFound() {
        Allure.step("Текст при ненайденных новостях");
        onView(withText(startsWith("There is nothing here yet"))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void clickOnTheFilterButtonToSearchForNews() {
        Allure.step("Нажатие на кнопку filter");
        advancedNewsSearchElements.getFilterButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheCancelSearchButton() {
        Allure.step("Нажатие на кнопку Cancel");
        advancedNewsSearchElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheActiveCheckBox() {
        Allure.step("Нажатие на чек-бокс Active");
        advancedNewsSearchElements.getCheckBoxNotActive().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheNotActiveCheckBox() {
        Allure.step("Нажатие на чек-бокс Not Active");
        advancedNewsSearchElements.getCheckBoxActive().perform(click());
        SystemClock.sleep(3000);
    }
}
