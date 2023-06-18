package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.randomHour23;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.randomMinute59;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.WatchElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class WatchSteps {
    WatchElements watchElements = new WatchElements();

    public void pressButtonToChangeTheWatchType() {
        Allure.step("Нажатие на кнопку смены вида часов");
        watchElements.getButtonToChangeTheTypeOfClock().perform(click());
        SystemClock.sleep(3000);
    }

    public void settTheHourSelectedRandomly(String hour) {
        Allure.step("Установка часа выбранного случайным образом");
        watchElements.getInputHour().perform(replaceText(hour), closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void settMinutesSelectedRandomly(String minute) {
        Allure.step("Установка минут выбранных случайным образом");
        watchElements.getInputMinute().perform(replaceText(minute), closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void clickConfirmationButton() {
        Allure.step("Нажатие на кнопку подтверждения");
        watchElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void pressCancelTimeSettingButton() {
        Allure.step("Нажатие на кнопку отмены установки времени");
        watchElements.getCancelButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void settRandomlySelectedHour() {
        Allure.step("Установка случайно выбранного часа");
        watchElements.getInputHour().perform(replaceText(randomHour23()), closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void settRandomlySelectedMinute() {
        Allure.step("Установка случайно выбранной минуты");
        watchElements.getInputMinute().perform(replaceText(randomMinute59()), closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void checkSetTime(String hour, String minute, String timeAfter, String timeBefore) {
        Allure.step("Проверка выставленного времени");
        assertEquals(hour + ":" + minute, timeAfter);
        assertNotEquals(timeBefore, timeAfter);
        SystemClock.sleep(3000);
    }

    public void checkTypeOfDigitalClock() {
        Allure.step("Проверка вида цифровых часов");
        watchElements.getTextTime().check(matches(withText("Set time"))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkClockReadingsBeforeInstallationAndAfterCancelingTheInstallation(String timeBefore, String timeAfter) {
        Allure.step("Проверка показаний часов до  установки и после отмены установки");
        assertEquals(timeBefore, timeAfter);
        SystemClock.sleep(3000);
    }

    public String timeBefore() {
        return Helper.Text.getText(onView(withId(R.id.time_in_plan_text_input_edit_text)));
    }

    public String timeAfter() {
        return Helper.Text.getText(onView(withId(R.id.time_in_plan_text_input_edit_text)));
    }

    public void settHourToAnInvalidValue(String invalidHour) {
        // Allure.step("Установка часа невалидного значения");
        watchElements.getInputHour().perform(replaceText(invalidHour), closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void settMinutesToAnInvalidValue(String invalidMinute) {
        //   Allure.step("Установка минут невалидного значения");
        watchElements.getInputMinute().perform(replaceText(invalidMinute), closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void checkEnterValidTime(@NonNull AppActivity activity, String text) {
        //   Allure.step("Проверка появления предупреждающего сообщения Enter a valid time");
        onView(withText(text))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(withText("Enter a valid time"))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
}
