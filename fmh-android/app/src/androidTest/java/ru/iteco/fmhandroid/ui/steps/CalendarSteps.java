package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.headerCalendarDate;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.headerCalendarYear;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.settingTheDate;
import static ru.iteco.fmhandroid.ui.data.Helper.Text.firstUpperCase;
import static ru.iteco.fmhandroid.ui.data.Helper.clickNextMonth;
import static ru.iteco.fmhandroid.ui.data.Helper.clickPreviousMonth;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.CalendarElements;
import io.qameta.allure.kotlin.Allure;

public class CalendarSteps {

    CalendarElements calendarElements = new CalendarElements();

    public void clickOnTheConfirmButton() {
        Allure.step("Нажатие на кнопку подтверждения");
        calendarElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void pressTheButtonToSelectTheYear() {
        Allure.step("Нажатие на кнопку для выбора года");
        calendarElements.getButtonOfTheYear().perform(click());
        SystemClock.sleep(3000);
    }

    public void settTheYear(int randomYear) {
        Allure.step("Установка года");
        calendarElements.getYear().atPosition(randomYear).perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void clickTheButtonToGoToTheNextMonthTwelveTimes(int randomMonth) {
        Allure.step("Нажатие на кнопку перехода к следующему месяцу ");
        clickNextMonth(randomMonth);
        SystemClock.sleep(3000);
    }

    public void clickOnTheButtonToGoToThePreviousMonthTwelveTimes(int randomMonth) {
        Allure.step("Нажатие на кнопку перехода к предыдущему месяцу");
        clickPreviousMonth(randomMonth);
        SystemClock.sleep(3000);
    }

    public void clickOnTheCancelYearSettingButton() {
        Allure.step("Нажатие на кнопку отмены при установке года");
        calendarElements.getButtonToCancelTheYearSetting().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void settDate(int yearInt, int monthInt, int dayInt) {
        Allure.step("Установка даты");
        settingTheDate(yearInt, monthInt, dayInt);
        SystemClock.sleep(3000);
    }

    public void checkTheDisplayInTheCalendarHeaderOfTheMonthNameChange(ViewInteraction dateFromTheCalendarHeader, String dayOfWeek, String month, String day, String dateFromTheCalendar, String dayOfWeekPlusMonth, String monthPlusTwoMonth, String dayPlusYearPlusMonth) {
        Allure.step("Отображения в календаре изменения месяца, числа");
        dateFromTheCalendarHeader.check(matches(isDisplayed())).check(matches(withText(firstUpperCase(dayOfWeekPlusMonth) + ", " + firstUpperCase(monthPlusTwoMonth) + " " + dayPlusYearPlusMonth)));
        assertEquals(firstUpperCase(dayOfWeek) + ", " + firstUpperCase(month) + " " + day, dateFromTheCalendar);
    }

    public void checkTheYearChangeCalendarDisplayInTheHeader(ViewInteraction yearFromTheCalendarHeader, String yearNumberOfMonths, String year) {
        Allure.step("Отображения в календаря изменения года");
        yearFromTheCalendarHeader.check(matches(withText(year))).check(matches(isDisplayed()));
        assertEquals(yearNumberOfMonths, year);
    }

    public String dateFormatting(int yearPlusYears, int monthNow, int dayOfMonthNow) {
        return LocalDate.of(yearPlusYears, monthNow, dayOfMonthNow).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public ViewInteraction dateFromTheCalendarHeaderBeforeReturnViewInteraction(String dayOfWeek, String month, String day) {
        return headerCalendarDate(firstUpperCase(dayOfWeek), firstUpperCase(month), day);
    }

    public String dateFromTheCalendarHeaderReturnString(String dayOfWeek, String month, String day) {
        return Helper.Text.getText(headerCalendarDate(firstUpperCase(dayOfWeek), firstUpperCase(month), day));
    }

    public String yearFromTheCalendarHeaderReturnString(String year) {
        return Helper.Text.getText(headerCalendarYear(year));
    }

    public ViewInteraction yearFromTheCalendarHeaderReturnViewInteraction(String year) {
        return headerCalendarYear(year);
    }
}
