package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.CalendarElements;
import ru.iteco.fmhandroid.ui.elements.EditingNewsElements;
import ru.iteco.fmhandroid.ui.elements.WatchElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class EditingNewsSteps {

    EditingNewsElements editingNewsElements = new EditingNewsElements();

    public void deletNewsTitle() {
        Allure.step("Удаление названия новости");
        editingNewsElements.getTitleTextNewsField().perform(replaceText("")).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void clickSaveButton() {
        Allure.step("Нажатие на кнопку сохранить");
        editingNewsElements.getSaveButton().perform(click());
    }

    public void clickCancelNewsEditingButton() {
          Allure.step("Нажатие на кнопку отмены редактирования новости");
        editingNewsElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickCancelButtonToExitEditing() {
        Allure.step("Нажатие на кнопку отмены выхода из редактирования");
        editingNewsElements.getCancelButton2().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickButtonToConfirmTheCancellationOfNewsEditing() {
        Allure.step("Нажатие на кнопку подтверждения отмены редактирования новости");
        editingNewsElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void clickCheckBox() {
        Allure.step("Нажатие на Чек-бокс");
        editingNewsElements.getCheckBox().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickButtonToEnterTheNewsEditing(String nameNewsItWas) {
        Allure.step("Нажатие на кнопку для входа в редактирование новости");
        onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))).perform(click());
        SystemClock.sleep(3000);
    }

    public void clickNews(String nameNewsItWas) {
        Allure.step("Нажатие на новость");
        onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))).perform(click());
        SystemClock.sleep(3000);
    }

    public void enterTextInTheCategoryField(String text) {
        Allure.step("Ввод текста в поле Category");
        editingNewsElements.getCategoryField().perform(replaceText(text));
        SystemClock.sleep(3000);
    }

    public void fillNewsFieldsWithNewData(String text, String Category) {
        Allure.step("Заполнение полей новости новыми данными");
        WatchElements watchScreenElements = new WatchElements();
        CalendarElements calendarScreenElements = new CalendarElements();

        editingNewsElements.getCategoryField().perform(replaceText("")).perform(closeSoftKeyboard());
        editingNewsElements.getCategoryField().perform(replaceText(Category)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        editingNewsElements.getTitleTextNewsField().perform(replaceText("")).perform(closeSoftKeyboard());
        editingNewsElements.getTitleTextNewsField().perform(replaceText(text)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        editingNewsElements.getPublishDateField().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        editingNewsElements.getTimeField().perform(click());
        calendarScreenElements.getOkButton().perform(scrollTo(), click());
        editingNewsElements.getDescriptionField().perform(replaceText("")).perform(closeSoftKeyboard());
        editingNewsElements.getDescriptionField().perform(replaceText(text)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void checkNameOfTheEditingNews() {
         Allure.step("Проверка названия страницы Editing News");
        editingNewsElements.getEditingNameScreen().check(matches(withText("Editing"))).check(matches(isDisplayed()));
        editingNewsElements.getNewsNameScreen().check(matches(withText("News"))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkWhetherTheFieldsAreFilledWithData(
            String category, String titleTextNews, String publishDate, String time, String description) {
        Allure.step("Проверка заполненности полей данными");
        editingNewsElements.getCategoryField().check(matches(withText(category))).check(matches(isDisplayed()));
        editingNewsElements.getTitleTextNewsField().check(matches(withText(titleTextNews))).check(matches(isDisplayed()));
        editingNewsElements.getPublishDateField().check(matches(withText(publishDate))).check(matches(isDisplayed()));
        editingNewsElements.getTimeField().check(matches(withText(time))).check(matches(isDisplayed()));
        editingNewsElements.getDescriptionField().check(matches(withText(description))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkInitialDataOfTheNewsWithTheFillingDataAndTheFinal(
            String nameNewsItWas, String nameNewsInput, String publicationDateNewsItWas, String publicationDateNewsInput,
            String creationDateNewsItWas, String timeNewsInput, String descriptionNewsItWas, String descriptionNewsInput,
            String nameNewsItWasHasBecomes, String publicationDateNewsItWasHasBecomes, String creationDateNewsItWasHasBecomes,
            String descriptionNewsItWasHasBecomes) {
        Allure.step("Проверка начальных данных новости с данными заполнения и конечными");
        assertNotEquals(nameNewsItWas, nameNewsInput);
        if (publicationDateNewsItWas.equals(publicationDateNewsInput)) {
            assertEquals(publicationDateNewsItWas, publicationDateNewsInput);
        } else {
            assertNotEquals(publicationDateNewsItWas, publicationDateNewsInput);
        }
        if (creationDateNewsItWas.equals(timeNewsInput)) {
            assertEquals(creationDateNewsItWas, timeNewsInput);
        } else {
            assertNotEquals(creationDateNewsItWas, timeNewsInput);
        }
        if (descriptionNewsItWas.equals(descriptionNewsInput)) {
            assertEquals(descriptionNewsItWas, descriptionNewsInput);
        } else {
            assertNotEquals(descriptionNewsItWas, descriptionNewsInput);
        }

        assertNotEquals(nameNewsItWas , nameNewsItWasHasBecomes);

        if (publicationDateNewsItWas.equals(publicationDateNewsItWasHasBecomes)) {
            assertEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        } else {
            assertNotEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        }
        if (creationDateNewsItWas.equals(creationDateNewsItWasHasBecomes)) {
            assertEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        } else {
            assertNotEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        }
        assertNotEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
        SystemClock.sleep(3000);
    }

    public void checkNewsDataBeforeEditingAndAfterCancelingEditing(
            String nameNewsItWas, String nameNewsInput, String publicationDateNewsItWas, String publicationDateNewsInput,
            String creationDateNewsItWas, String timeNewsInput, String descriptionNewsItWas, String descriptionNewsInput,
            String nameNewsItWasHasBecomes, String publicationDateNewsItWasHasBecomes, String creationDateNewsItWasHasBecomes,
            String descriptionNewsItWasHasBecomes) {
          Allure.step("Проверка данных новости до редактирования и после отмены редактирования");
        assertNotEquals(nameNewsItWas , nameNewsInput);
        if (publicationDateNewsItWas.equals(publicationDateNewsInput)) {
            assertEquals(publicationDateNewsItWas, publicationDateNewsInput);
        } else {
            assertNotEquals(publicationDateNewsItWas, publicationDateNewsInput);
        }
        if (creationDateNewsItWas.equals(timeNewsInput)) {
            assertEquals(creationDateNewsItWas, timeNewsInput);
        } else {
            assertNotEquals(descriptionNewsItWas, descriptionNewsInput);
        }

        assertEquals(nameNewsItWas , nameNewsItWasHasBecomes);
        assertEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        assertEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        assertEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
        SystemClock.sleep(3000);
    }

    public void checkFillEmptyFields(@NonNull AppActivity activity, String text) {
        Allure.step("Предупреждающее сообщения Fill empty fields");
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkSavingFailedTryAgainLater(@NonNull AppActivity activity, String text) {
        Allure.step("Предупреждающее сообщения Saving failed. Try again later");
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public String categoryField() {
        return Helper.Text.getText(onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view))));
    }

    public String titleTextNewsField() {
        return Helper.Text.getText(onView(
                allOf(withId(R.id.news_item_title_text_input_edit_text))));
    }

    public String publishDateField() {
        return Helper.Text.getText(onView(allOf(withId(R.id.news_item_publish_date_text_input_edit_text))));
    }

    public String timeField() {
        return Helper.Text.getText(onView(
                allOf(withId(R.id.news_item_publish_time_text_input_edit_text))));
    }

    public String descriptionField() {
        return Helper.Text.getText(onView(
                allOf(withId(R.id.news_item_description_text_input_edit_text))));
    }

    public String nameNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
    }

    public String publicationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)));
    }

    public String creationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_create_date_text_view), 0)));
    }

    public String descriptionNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), 0)));
    }

    public ViewInteraction statusNews(String nameNewsItWas) {
        return onView(allOf(withId(R.id.news_item_published_text_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas))))))));
    }

    public String statusNewsText(String nameNewsItWas) {
        return Helper.Text.getText(onView(allOf(withId(R.id.news_item_published_text_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))));
    }

    public ViewInteraction statusNewsPosition(int position) {
        return onView(allOf(withIndex(withId(R.id.news_item_published_text_view), position)));
    }

    public void clickCategoryField() {
        //  Allure.step("Нажатие на поле Category");
        editingNewsElements.getCategoryField().perform(replaceText(""), click()).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void clickPublicationDateField() {
        //  Allure.step("Нажатие на поле Publication date");
        editingNewsElements.getPublishDateField().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void clickTimeField() {
        // Allure.step("Нажатие на поле Time");
        editingNewsElements.getTimeField().perform(click());
        SystemClock.sleep(3000);
    }

    public void invalidLanguage(String title) {
        //  Allure.step("Ввод невалидного языка");
        editingNewsElements.getTitleTextNewsField().perform(replaceText("")).perform(closeSoftKeyboard());
        SystemClock.sleep(2000);
        editingNewsElements.getDescriptionField().perform(replaceText("")).perform(closeSoftKeyboard());
        SystemClock.sleep(2000);
        try {
            editingNewsElements.getTitleTextNewsField().perform(typeText(title)).perform(closeSoftKeyboard());
        } catch (RuntimeException e) {
            editingNewsElements.getTitleTextNewsField().perform(closeSoftKeyboard());
        } finally {
            SystemClock.sleep(2000);
        }
        try {
            editingNewsElements.getDescriptionField().perform(typeText(title));
        } catch (RuntimeException e) {
            editingNewsElements.getDescriptionField().perform(closeSoftKeyboard());
        } finally {
            SystemClock.sleep(3000);
        }
    }

    public void validLanguage(String title) {
        //   Allure.step("Ввод валидного языка");
        editingNewsElements.getTitleTextNewsField().perform(replaceText(title));
        editingNewsElements.getCategoryField().perform(replaceText(title));
        editingNewsElements.getDescriptionField().perform(replaceText(title)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void checkPresenceOfWordsFromEnglishLettersInTheFields(String validLanguageText) {
        //  Allure.step("Проверка на присудствие в полях слов из английских букв");
        editingNewsElements.getTitleTextNewsField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingNewsElements.getCategoryField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingNewsElements.getDescriptionField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkAbsenceOfWordsFromRussianLettersInTheFields() {
        // Allure.step("Проверка на отсутствие в полях слов из русских букв");
        editingNewsElements.getTitleTextNewsField().check(matches(withText(""))).check(matches(isDisplayed()));
        editingNewsElements.getDescriptionField().check(matches(withText(""))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkNameFieldInEditingNews() {
        //  Allure.step("Проверка идентифицирующих названий полей");
        editingNewsElements.getCategoryName().check(matches(isDisplayed()));
        editingNewsElements.getTitleName().check(matches(isDisplayed()));
        editingNewsElements.getPublicationDateName().check(matches(isDisplayed()));
        editingNewsElements.getTimeName().check(matches(isDisplayed()));
        editingNewsElements.getDescriptionName().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkCalendarAppearance(@NonNull AppActivity activity) {
        //  Allure.step("Проверка появления календаря");
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
}
