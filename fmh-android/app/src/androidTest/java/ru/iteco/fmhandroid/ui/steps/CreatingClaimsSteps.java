package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.Search.textSearchClaims;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.test.espresso.action.ViewActions;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.CreatingClaimsElements;

public class CreatingClaimsSteps {

    CreatingClaimsElements creatingClaimsElements = new CreatingClaimsElements();

    public void clickSaveButton() {
        Allure.step("Нажатие на кнопку сохранения");
        creatingClaimsElements.getSaveButton().perform(click());
    }

    public void clickCancelButtonToExitTheClaimCreation() {
        Allure.step("Нажатие на кнопку отмены выхода из создания претензии");
        creatingClaimsElements.getCancelButtonInWindow().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickCancelClaimCreationButton() {
        Allure.step("Нажатие на кнопку отмены создания претензии");
        creatingClaimsElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickButtonToConfirmTheCancellationOfTheClaimCreation() {
        Allure.step("Нажатие на кнопку подтверждения отмены создания претензии");
        creatingClaimsElements.getOkButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void textInput(String invalidText) {
        Allure.step("Ввод текста 51 символ");
        creatingClaimsElements.getTitleClaimField().perform(typeText(invalidText));
        SystemClock.sleep(3000);
    }

    public void clickDateField() {
        Allure.step("Нажатие на поле дата");
        creatingClaimsElements.getDateClaimField().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickTimeField() {
        Allure.step("Нажатие на поле время");
        creatingClaimsElements.getTimeClaimField().perform(click());
        SystemClock.sleep(3000);
    }

    public void fillInFieldsWithValidData(String titleText, String randomExecutor) {
        Allure.step("Заполнение полей валидными данными");
        WatchSteps watchScreenStep = new WatchSteps();
        CalendarSteps calendarScreenStep = new CalendarSteps();

        creatingClaimsElements.getTitleClaimField().perform(replaceText(titleText), closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingClaimsElements.getExecutorClaimField().perform(replaceText(randomExecutor)).perform(closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingClaimsElements.getDateClaimField().perform(click());
        watchScreenStep.clickConfirmationButton();
        SystemClock.sleep(2000);
        creatingClaimsElements.getTimeClaimField().perform(click());
        calendarScreenStep.clickOnTheConfirmButton();
        SystemClock.sleep(2000);
        creatingClaimsElements.getDescriptionClaimField().perform(replaceText(titleText), closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void fillFieldsWithValidDataToCreateClaimWithAnOpenStatus(String titleText) {
        Allure.step("Заполнение полей валидными данными для создания претензии с открытым статусом");
        WatchSteps watchScreenStep = new WatchSteps();
        CalendarSteps calendarScreenStep = new CalendarSteps();

        creatingClaimsElements.getTitleClaimField().perform(replaceText(titleText), closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingClaimsElements.getDateClaimField().perform(click());
        watchScreenStep.clickConfirmationButton();
        SystemClock.sleep(2000);
        creatingClaimsElements.getTimeClaimField().perform(click());
        calendarScreenStep.clickOnTheConfirmButton();
        SystemClock.sleep(2000);
        creatingClaimsElements.getDescriptionClaimField().perform(replaceText(titleText), closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void nameTitle() {
        Allure.step("Удаление названия");
        creatingClaimsElements.getTitleClaimField().perform(replaceText(""));
        SystemClock.sleep(3000);
    }

    public void searchForCreatedClaim(String titleText) {
        Allure.step("Поиск созданной претензии");
        textSearchClaims(titleText);
        SystemClock.sleep(3000);
    }

    public void checkDataOfTheCreatedClaimAndTheFoundOne(
            String title, String title2, String executor, String executor2, String date, String date2, String time, String time2,
            String description, String description2) {
        Allure.step("Проверка данных созданной претензии и найденной");
        assertEquals(title, title2);
        if (executor.equals("NOT ASSIGNED")) {
            assertEquals(executor, executor2);
        } else {
            onView(withId(R.id.executor_name_text_view)).check(matches(withText("NOT ASSIGNED"))).check(matches(isDisplayed()));
        }
        assertEquals(date, date2);
        assertEquals(time, time2);
        assertEquals(description.trim(), description2.trim());
        SystemClock.sleep(3000);
    }

    public void checkSetDateWithTheDateInTheField(String dateField, String today) {
        Allure.step("Проверка выставленной даты с датой в поле");
        creatingClaimsElements.getDateClaimField().check(matches(withText(dateField))).check(matches(isDisplayed()));
        assertEquals(today, dateField);
        SystemClock.sleep(3000);
    }

    public void checkDataAndStatusOfTheClaimCreatedAndFound(
            String title, String titleOnCaredClaims, String date, String dateOnCaredClaims, String time, String timeOnCaredClaims,
            String description, String descriptionOnCaredClaims) {
        Allure.step("Проверка данных и статуса созданной претензии и найденной");
        ClaimsSteps claimsScreenStep = new ClaimsSteps();

        assertEquals(title, titleOnCaredClaims);
        assertEquals(date, dateOnCaredClaims);
        assertEquals(time, timeOnCaredClaims);
        assertEquals(description, descriptionOnCaredClaims);

        claimsScreenStep.checkTheOpenStatus();
        SystemClock.sleep(3000);
    }

    public void checkNumberOfCharactersEnteredAndCharactersInTheField() {
        Allure.step("Проверка количества введенных символов и  символов в поле");
        String text = Helper.Text.getText(onView(withId(R.id.title_edit_text)));
        int textLength = text.length();
        assertEquals(50, textLength);
        SystemClock.sleep(3000);
    }

    public void checkNameOfTheClaimCreation() {
        Allure.step("Проверка названия экрана создания претензии creating Claims");
        creatingClaimsElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingClaimsElements.getClaimsNameScreen().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkFillEmptyFields(@NonNull AppActivity activity, String text) {
          Allure.step("Сообщение об ошибке Fill empty fields");
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public String title() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.title_edit_text), 0)));
    }

    public String executor() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.executor_drop_menu_auto_complete_text_view), 0)));
    }

    public String dateFromTheField() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.date_in_plan_text_input_edit_text), 0)));
    }

    public String time() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.time_in_plan_text_input_edit_text), 0)));
    }

    public String description() {
        return Helper.Text.getText(onView(withId(R.id.description_edit_text)));
    }

    public String titleOnCaredClaims() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.title_text_view), 0)));
    }

    public String executorOnCaredClaims() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.executor_name_text_view), 0)));
    }

    public String dateOnCaredClaims() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plane_date_text_view), 0)));
    }

    public String timeOnCaredClaims() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plan_time_text_view), 0)));
    }

    public String descriptionOnCaredClaims() {
        return Helper.Text.getText(onView(withId(R.id.description_text_view)));
    }

    public void clickExecutorField() {
        //  Allure.step("Нажатие на поле Executor");
        creatingClaimsElements.getExecutorClaimField().perform(click()).perform(ViewActions.closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void invalidLanguage(String title) {
        //  Allure.step("Ввод невалидного языка");
        try {
            creatingClaimsElements.getTitleClaimField().perform(typeText(title)).perform(closeSoftKeyboard());
        } catch (RuntimeException e) {
            creatingClaimsElements.getTitleClaimField().perform(closeSoftKeyboard());
        }
        SystemClock.sleep(2000);
        try {
            creatingClaimsElements.getExecutorClaimField().perform(typeText(title)).perform(closeSoftKeyboard());
        } catch (RuntimeException e) {

        }
        SystemClock.sleep(2000);
        try {
            creatingClaimsElements.getDescriptionClaimField().perform(typeText(title)).perform(closeSoftKeyboard());
        } catch (RuntimeException e) {
            creatingClaimsElements.getExecutorClaimField().perform(closeSoftKeyboard());
        }
        SystemClock.sleep(3000);
    }

    public void validLanguage(String title) {
        //  Allure.step("Ввод валидного языка");
        creatingClaimsElements.getTitleClaimField().perform(typeText(title)).perform(closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingClaimsElements.getExecutorClaimField().perform(typeText(title)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingClaimsElements.getDescriptionClaimField().perform(typeText(title)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void checkAbsenceOfWordsFromRussianLettersInTheFields() {
        //  Allure.step("Проверка на отсутствие в полях слов из русских букв");
        creatingClaimsElements.getTitleClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        creatingClaimsElements.getExecutorClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        creatingClaimsElements.getDescriptionClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkPresenceOfWordsFromEnglishLettersInTheFields(String validLanguageText) {
        // Allure.step("Проверка на присудствие в полях слов из английских букв");
        creatingClaimsElements.getTitleClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        creatingClaimsElements.getExecutorClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        creatingClaimsElements.getDescriptionClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkNameFieldInCreatingClaims() {
        //   Allure.step("Проверка идентифицирующих названий полей для заполнения");
        creatingClaimsElements.getTitleName().check(matches(isDisplayed()));
        creatingClaimsElements.getExecutorName().check(matches(isDisplayed()));
        creatingClaimsElements.getDateName().check(matches(isDisplayed()));
        creatingClaimsElements.getTimeName().check(matches(isDisplayed()));
        creatingClaimsElements.getDescriptionName().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkAbsenceOfSetYear() {
        //  Allure.step("Проверка отсутствия установленного года в поле");
        creatingClaimsElements.getDateClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkCalendarAppearance(@NonNull AppActivity activity) {
        // Allure.step("Проверка появления календаря");
        onView(withClassName(is("android.widget.DatePicker")))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkAppearanceOfTheDropDownList(@NonNull AppActivity activity) {
        //   Allure.step("Проверка появления выпадающего списка");
        onView(withClassName(is("android.widget.PopupWindow$PopupBackgroundView")))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkAppearanceOfClockOfTheArrowType(@NonNull AppActivity activity) {
        //  Allure.step("Проверка появления часов стрелочного типа");
        onView(withClassName(is("android.widget.RadialTimePickerView")))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
}
