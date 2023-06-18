package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.invalidHour;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.invalidMinute;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.randomHour23;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.randomMinute59;
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

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.WatchSteps;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class WatchTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    MainSteps mainSteps = new MainSteps();
    WatchSteps watchSteps = new WatchSteps();
    CreatingClaimsSteps creatingClaimsSteps = new CreatingClaimsSteps();

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(authInfo());
        } finally {
            mainSteps.randomTransitionToCreatingClaims();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("Changing the clock appearance when choosing a time")
    @Description("Изменения вида часов при выборе времени ID 109")
    public void shouldBeChangedClock() {
        creatingClaimsSteps.clickTimeField();
        watchSteps.pressButtonToChangeTheWatchType();
        watchSteps.checkTypeOfDigitalClock();
    }

    @Test
    @DisplayName("Deselecting the clock time")
    @Description("Отмена выбора времени на часах ID 110")
    public void shouldBeCancelledTiming() {
        String timeBefore = watchSteps.timeBefore();

        creatingClaimsSteps.clickTimeField();
        watchSteps.pressButtonToChangeTheWatchType();
        watchSteps.checkTypeOfDigitalClock();

        watchSteps.settRandomlySelectedHour();
        watchSteps.settRandomlySelectedMinute();

        watchSteps.pressCancelTimeSettingButton();
        String timeAfter = watchSteps.timeAfter();
        watchSteps.checkClockReadingsBeforeInstallationAndAfterCancelingTheInstallation(timeBefore, timeAfter);
    }

    @Test
    @DisplayName("The time displayed in the claim must correspond to the selected time on the clock")
    @Description("Время отображающееся в претензии должно соответствовать выбранному времени на часах ID 111")
    public void shouldBeTimeToMatchTheChosen() {
        String hour = randomHour23();
        String minute = randomMinute59();

        String timeBefore = watchSteps.timeBefore();

        creatingClaimsSteps.clickTimeField();
        watchSteps.pressButtonToChangeTheWatchType();
        watchSteps.checkTypeOfDigitalClock();

        watchSteps.settTheHourSelectedRandomly(hour);
        watchSteps.settMinutesSelectedRandomly(minute);

        watchSteps.clickConfirmationButton();
        String timeAfter = watchSteps.timeAfter();
        watchSteps.checkSetTime(hour, minute, timeAfter, timeBefore);
    }

    @Test
    @DisplayName("There should be a notice to set the wrong time")
    @Description("Должно быть уведомление на установку некорректного времени ID 139")
    public void shouldNotBeStoredIncorrectTime() {
        String invalidHour = invalidHour();
        String invalidMinute = invalidMinute();

        creatingClaimsSteps.clickTimeField();
        watchSteps.pressButtonToChangeTheWatchType();
        watchSteps.checkTypeOfDigitalClock();

        watchSteps.settHourToAnInvalidValue(invalidHour);
        watchSteps.settMinutesToAnInvalidValue(invalidMinute);

        watchSteps.clickConfirmationButton();
        watchSteps.checkEnterValidTime(ActivityTestRule.getActivity(), "Enter a valid time");
    }
}
