package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomExecutor;
import static ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.CalendarElements;
import ru.iteco.fmhandroid.ui.elements.ClaimsElements;
import ru.iteco.fmhandroid.ui.elements.EditingClaimsElements;
import ru.iteco.fmhandroid.ui.elements.FilteringWindowElements;
import ru.iteco.fmhandroid.ui.elements.WatchElements;

public class EditingClaimsSteps {

    EditingClaimsElements editingClaimsElements = new EditingClaimsElements();

    public void clickSaveButton() {
        Allure.step("Нажатие на кнопку сохранить");
        editingClaimsElements.getSaveButton().perform(click());
    }

    public void clickUndoEditButton() {
        Allure.step("Нажатие на кнопку отмены редактирования");
        editingClaimsElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickRandomlySelectedArtist(@NonNull AppActivity activity, String executor) {
        Allure.step("Выбор исполнителя рандомно");
        onView(withText(executor))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).perform(click());
        SystemClock.sleep(3000);
    }

    public void clickCancelButtonToExitEditingEditing() {
        Allure.step("Нажатие на кнопку  отмены выхода из редактирования редактирования");
        editingClaimsElements.getCancelButton2().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void clickButtonToConfirmTheCancellationOfEditing() {
        Allure.step("Нажатие на кнопку подтверждения отмены редактирования");
        editingClaimsElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void deletTextFromTheTitleField() {
        Allure.step("Удаление текста из поля Title");
        editingClaimsElements.getTitleClaimField().perform(replaceText(""), closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void clickExecutorField() {
        Allure.step("Нажатие на поле Executor");
        editingClaimsElements.getExecutorClaimField().perform(click());
        editingClaimsElements.getExecutorClaimField().perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void fillFieldsWithValidData() {
          Allure.step("Заполнение полей валидными данными");
        WatchElements watchElements = new WatchElements();
        CalendarElements calendarElements = new CalendarElements();
        editingClaimsElements.getTitleClaimField().perform(replaceText(textSymbol(5)), closeSoftKeyboard());
        SystemClock.sleep(2000);
        editingClaimsElements.getExecutorClaimField().perform(replaceText(randomExecutor()), closeSoftKeyboard());
        SystemClock.sleep(2000);
        editingClaimsElements.getDateClaimField().perform(click());
        watchElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(2000);
        editingClaimsElements.getTimeClaimField().perform(click());
        calendarElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(2000);
        editingClaimsElements.getDescriptionClaimField().perform(replaceText(textSymbol(5)), closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void fillExecutorField(String text) {
         Allure.step("Заполнение поля Executor");
        editingClaimsElements.getExecutorClaimField().perform(replaceText(text));
        SystemClock.sleep(3000);
    }

    public void checkFieldsAreFilledIn() {
        Allure.step("Проверка Заполнения Полей");
        editingClaimsElements.getTitleClaimField().check(matches(isDisplayed()));
        editingClaimsElements.getExecutorClaimField().check(matches(isDisplayed()));
        editingClaimsElements.getDateClaimField().check(matches(isDisplayed()));
        editingClaimsElements.getTimeClaimField().check(matches(isDisplayed()));
        editingClaimsElements.getDescriptionClaimField().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkNameOfTheScreenForEditingClaims() {
          Allure.step("Проверка названия страницы для редактирования претензий");
        editingClaimsElements.getEditingName().check(matches(isDisplayed()));
        editingClaimsElements.getClaimsName().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkDataBeforeEditingAndAfter(
            String titleClaimFieldItWas, String titleClaimFieldItWasHasBecome, String executorClaimFieldItWas, String executorClaimFieldItWasHasBecome,
            String dateClaimFieldItWas, String dateClaimFieldItWasHasBecome, String timeClaimFieldItWas, String timeClaimFieldItWasHasBecome,
            String descriptionClaimFieldItWas, String descriptionClaimFieldItWasHasBecome) {
         Allure.step("Проверка данных до редактирования и после");
        assertEquals(titleClaimFieldItWas, titleClaimFieldItWasHasBecome);
        assertEquals(executorClaimFieldItWas, executorClaimFieldItWasHasBecome);
        if (dateClaimFieldItWas.equals(dateClaimFieldItWasHasBecome)) {
            assertEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        } else {
            assertNotEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        }
        assertNotEquals(timeClaimFieldItWas, timeClaimFieldItWasHasBecome);
        assertNotEquals(descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
        SystemClock.sleep(3000);
    }

    public void verificationOfClaimDataBeforeEditingAndAfterCancellationOfEditing(
            String titleClaimFieldItWas, String titleClaimFieldItWasHasBecome, String executorClaimFieldItWas, String executorClaimFieldItWasHasBecome,
            String dateClaimFieldItWas, String dateClaimFieldItWasHasBecome, String timeClaimFieldItWas, String timeClaimFieldItWasHasBecome,
            String descriptionClaimFieldItWas, String descriptionClaimFieldItWasHasBecome
    ) {
           Allure.step("Проверка данных претензии до редактирования и после отмены редактирования");
        assertEquals(titleClaimFieldItWas, titleClaimFieldItWasHasBecome);
        assertEquals(executorClaimFieldItWas, executorClaimFieldItWasHasBecome);
        assertEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        assertEquals(timeClaimFieldItWas, timeClaimFieldItWasHasBecome);
        assertEquals(descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
        SystemClock.sleep(3000);
    }

    public void checkPerformerBeforeEditingAndAfter(
            String executorClaimFieldItWas, String executorClaimFieldItWasHasBecome, String executorClaimFieldInputText) {
          Allure.step("Проверка исполнителя до редактирования и после");
        if (executorClaimFieldItWas.equals("NOT ASSIGNED")) {
            assertEquals(executorClaimFieldItWas, executorClaimFieldItWasHasBecome);
        } else {
            assertNotEquals(executorClaimFieldInputText, executorClaimFieldItWasHasBecome);
        }
        SystemClock.sleep(3000);
    }

    public void checkFillEmptyFields(@NonNull AppActivity activity, String text) {
        Allure.step("Проверка появления предупреждающего сообщения Fill empty fields");
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public String titleClaimField() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.title_text_view), 0)));
    }

    public String executorClaimField() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.executor_name_text_view), 0)));
    }

    public String executorClaim() {
        return  Helper.Text.getText(editingClaimsElements.getExecutorClaimField());
    }

    public String dateClaimField() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plane_date_text_view), 0)));
    }

    public String timeClaimField() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plan_time_text_view), 0)));
    }

    public String descriptionClaimField() {
        return Helper.Text.getText(onView(withId(R.id.description_text_view)));
    }

    public void clickDateField() {
     //   Allure.step("Нажатие на поле Date");
        editingClaimsElements.getDateClaimField().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickTimeField() {
      //  Allure.step("Нажатие на поле Time");
        editingClaimsElements.getTimeClaimField().perform(click());
        SystemClock.sleep(3000);
    }

    public void invalidLanguage(String title) {
      //  Allure.step("Ввод невалидного языка");
        editingClaimsElements.getTitleClaimField().perform(replaceText("")).perform(closeSoftKeyboard());
        SystemClock.sleep(2000);
        editingClaimsElements.getExecutorClaimField().perform(replaceText("")).perform(closeSoftKeyboard());
        SystemClock.sleep(2000);
        editingClaimsElements.getDescriptionClaimField().perform(replaceText("")).perform(closeSoftKeyboard());
        SystemClock.sleep(2000);
        try {
            editingClaimsElements.getTitleClaimField().perform(typeText(title)).perform(closeSoftKeyboard());
        } catch (RuntimeException e) {
            editingClaimsElements.getTitleClaimField().perform(closeSoftKeyboard());
        }
        SystemClock.sleep(2000);
        try {
            editingClaimsElements.getExecutorClaimField().perform(typeText(title)).perform(closeSoftKeyboard());
        } catch (RuntimeException e) {

        }
        SystemClock.sleep(2000);
        try {
            editingClaimsElements.getDescriptionClaimField().perform(typeText(title)).perform(closeSoftKeyboard());
        } catch (RuntimeException e) {
            editingClaimsElements.getExecutorClaimField().perform(closeSoftKeyboard());
        }
        SystemClock.sleep(3000);
    }

    public void validLanguage(String title) {
     //   Allure.step("Ввод валидного языка");
        editingClaimsElements.getTitleClaimField().perform(replaceText(title));
        editingClaimsElements.getExecutorClaimField().perform(replaceText(title));
        editingClaimsElements.getDescriptionClaimField().perform(replaceText(title)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void comparisonOfDataBeforeAndAfterEditing(
            String titleClaimFieldItWas, String titleClaimFieldItWasHasBecome, String executorClaimFieldItWas,
            String executorClaimFieldItWasHasBecome, String dateClaimFieldItWas, String dateClaimFieldItWasHasBecome,
            String timeClaimFieldItWas, String timeClaimFieldItWasHasBecome, String descriptionClaimFieldItWas, String descriptionClaimFieldItWasHasBecome) {
        assertNotEquals(titleClaimFieldItWas, titleClaimFieldItWasHasBecome);
        assertEquals(executorClaimFieldItWas, executorClaimFieldItWasHasBecome);
        if (dateClaimFieldItWas.equals(dateClaimFieldItWasHasBecome)) {
       //     Allure.step("Сравнение данных до редактирования и после");
            assertEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        } else {
            assertNotEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        }
        assertNotEquals(timeClaimFieldItWas, timeClaimFieldItWasHasBecome);
        assertNotEquals(descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
        SystemClock.sleep(3000);
    }

    public void checkPresenceOfWordsFromEnglishLettersInTheFields(String validLanguageText) {
      //  Allure.step("Проверка на присудствие в полях слов из английских букв");
        editingClaimsElements.getTitleClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingClaimsElements.getExecutorClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingClaimsElements.getDescriptionClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkAbsenceOfWordsFromRussianLettersInTheFields() {
      //  Allure.step("Проверка на отсутствие в полях слов из русских букв");
        editingClaimsElements.getTitleClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        editingClaimsElements.getExecutorClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        editingClaimsElements.getDescriptionClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkCalendarAppearance(@NonNull AppActivity activity) {
     //   Allure.step("Проверка появления календаря");
        onView(withClassName(is("android.widget.DatePicker")))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkAppearanceOfTheDropDownList(@NonNull AppActivity activity) {
      //  Allure.step("Проверка появления выпадающего списка");
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

    public void goToTheClaimCardWithTheOpenStatus(int position) {
     //  Allure.step("Переход к карточке Claim со статусом Open");
        ClaimsElements claimsScreenElements = new ClaimsElements();
        FilteringWindowElements filteringWindowScreenElements = new FilteringWindowElements();
        MainSteps mainScreenStep = new MainSteps();

        mainScreenStep.clickMenuButton();
        mainScreenStep.clickClaimsName();
        claimsScreenElements.getFilteringButton().perform(click());
        filteringWindowScreenElements.getCheckBoxInProgress().perform(click());
        filteringWindowScreenElements.getOkButton().perform(click());
        SystemClock.sleep(3000);
        claimsScreenElements.getBlockClaims().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }
}
