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

import java.time.LocalDate;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.CalendarSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class CalendarTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    MainSteps mainSteps = new MainSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    CreatingClaimsSteps creatingClaimsSteps = new CreatingClaimsSteps();
    CalendarSteps calendarSteps = new CalendarSteps();

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(authInfo());
        } finally {
            mainSteps.clickButtonPlus();
            creatingClaimsSteps.checkNameOfTheClaimCreation();
            creatingClaimsSteps.clickDateField();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(3000);
    }

    @Test
    @DisplayName("The calendar must have the current date selected")
    @Description("В календаре должна быть выбрана текущая дата ID 107")
    public void shouldBeSelectedCurrentDate() {

        String dayOfWeekNow = String.valueOf(LocalDate.now().getDayOfWeek()).substring(0, 3).toLowerCase();
        String monthNow = String.valueOf(LocalDate.now().getMonth()).substring(0, 3).toLowerCase();
        String dayNow = String.valueOf(LocalDate.now().getDayOfMonth());
        String yearNow = String.valueOf(LocalDate.now().getYear());

        String dateAfter = calendarSteps.dateFromTheCalendarHeaderReturnString(dayOfWeekNow, monthNow, dayNow);
        ViewInteraction dateFromTheCalendarHeaderAfter = calendarSteps.dateFromTheCalendarHeaderBeforeReturnViewInteraction(dayOfWeekNow, monthNow, dayNow);
        calendarSteps.checkTheDisplayInTheCalendarHeaderOfTheMonthNameChange(
                dateFromTheCalendarHeaderAfter, dayOfWeekNow, monthNow, dayNow, dateAfter, dayOfWeekNow,
                monthNow, dayNow);

        String yearFromTheCalendarHeaderAfter = calendarSteps.yearFromTheCalendarHeaderReturnString(yearNow);
        ViewInteraction yearAfter = calendarSteps.yearFromTheCalendarHeaderReturnViewInteraction(yearFromTheCalendarHeaderAfter);
        calendarSteps.checkTheYearChangeCalendarDisplayInTheHeader(yearAfter, yearNow, yearFromTheCalendarHeaderAfter);
    }

    @Test
    @DisplayName("The date in the creation of the claim must match the date selected in the calendar")
    @Description("Дата в создании претензии должна соответствовать дате выбранной в календаре ID 108")
    public void shouldBeSelectedDate() {
        int randomYear = random(1, 2);

        int yearPlusYears = LocalDate.now().plusYears(randomYear).getYear();
        int monthNow = LocalDate.now().getMonthValue();
        int dayOfMonthNow = LocalDate.now().getDayOfMonth();

        calendarSteps.pressTheButtonToSelectTheYear();
        calendarSteps.settTheYear(randomYear);

        String today = calendarSteps.dateFormatting(yearPlusYears, monthNow, dayOfMonthNow);

        calendarSteps.clickOnTheConfirmButton();
        creatingClaimsSteps.checkNameOfTheClaimCreation();
        String dateField = creatingClaimsSteps.dateFromTheField();

        creatingClaimsSteps.checkSetDateWithTheDateInTheField(dateField, today);
    }
}
