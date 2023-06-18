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
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.CalendarElements;
import ru.iteco.fmhandroid.ui.elements.ControlPanelElements;
import ru.iteco.fmhandroid.ui.elements.CreatingNewsElements;
import ru.iteco.fmhandroid.ui.elements.WatchElements;
import ru.iteco.fmhandroid.ui.steps.CalendarSteps;

public class CreatingNewsSteps {

    CreatingNewsElements creatingNewsElements = new CreatingNewsElements();

    public void clickExitButtonFromNewsCreation() {
        Allure.step("Нажатие на кнопку выхода из создания новости");
        creatingNewsElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickSaveNewsButton() {
        Allure.step("Нажатие на кнопку сохранения новости");
        creatingNewsElements.getSaveButton().perform(click());
    }

    public void fillFieldsWithValidData(String text, String validCategory) {
        Allure.step("Заполнение полей валидными данными");
        WatchElements watchScreenElements = new WatchElements();
        CalendarSteps calendarScreenStep = new CalendarSteps();

        creatingNewsElements.getCategoryFieldNews().perform(replaceText(validCategory)).perform(closeSoftKeyboard());
        creatingNewsElements.getTitleFieldNews().perform(typeText(text), click()).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsElements.getPublicationDateFieldNews().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        creatingNewsElements.getTimeFieldNews().perform(click());
        calendarScreenStep.clickOnTheConfirmButton();
        creatingNewsElements.getDescriptionFieldNews().perform(typeText(text), click()).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void fillCategoryField(String text) {
        Allure.step("Заполнение поля Category");
        WatchElements watchScreenElements = new WatchElements();
        CalendarElements calendarScreenElements = new CalendarElements();
                                                                      //randomCategory()
        creatingNewsElements.getCategoryFieldNews().perform(replaceText(text)).perform(closeSoftKeyboard());
        creatingNewsElements.getTitleFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsElements.getPublicationDateFieldNews().perform(click());
        calendarScreenElements.getOkButton().perform(scrollTo(), click());
        creatingNewsElements.getTimeFieldNews().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        creatingNewsElements.getDescriptionFieldNews().perform(click());
        creatingNewsElements.getDescriptionFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void choosNews(int position) {
        Allure.step("Выбор новости");
        ControlPanelElements controlPanelElements = new ControlPanelElements();
        controlPanelElements.getRecyclerView().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    public void checkNameOfTheCreatingNews() {
        Allure.step("Проверка названия экрана Creating News");
        creatingNewsElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingNewsElements.getNewsNameScreen().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void comparingTheDataOfTheCreatedNewsWithTheDataOfTheFirstNewsFromTheList(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
        Allure.step("Сравнение данных созданной новости с данными новости первой из списка");

        assertNotEquals(nameNewsItWas, nameNewsItWasHasBecomes);

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
        if (authorNewsItWas.equals(authorNewsItWasHasBecomes)) {
            assertEquals(authorNewsItWas, authorNewsItWasHasBecomes);
        } else {
            assertNotEquals(authorNewsItWas, authorNewsItWasHasBecomes);
        }
        assertNotEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
        SystemClock.sleep(3000);
    }

    public void checkDataOfTheFirstNewsFromTheListMustMatchAfterCancelingTheCreationOfTheNews(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
        Allure.step("Проверка данные первой новости из списка должны совпадать после отмены создания новости");
        assertEquals(nameNewsItWas.trim(), nameNewsItWasHasBecomes.trim());
        assertEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        assertEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        assertEquals(authorNewsItWas, authorNewsItWasHasBecomes);
        assertEquals(descriptionNewsItWas.trim(), descriptionNewsItWasHasBecomes.trim());
        SystemClock.sleep(3000);
    }

    public void checkFillEmptyFields(@NonNull AppActivity activity, String text) {
        Allure.step("Сообщение об ошибке Fill empty fields");
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkSaveFailedTryAgainLater(@NonNull AppActivity activity, String text) {
        Allure.step("Сообщение об ошибке Saving failed. Try again later");
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public String nameNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
    }

    public String nameNewsPosition(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String publicationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)));
    }

    public String publicationDateNewsPosition(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), position)));
    }

    public String creationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_create_date_text_view), 0)));
    }

    public String creationDateNewsPosition(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_create_date_text_view), position)));
    }

    public String authorNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_author_name_text_view), 0)));
    }

    public String authorNewsPosition(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_author_name_text_view), position)));
    }

    public String descriptionNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), 0)));
    }

    public String descriptionNewsPosition(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
    }

    public void clickCategoryField() {
        //   Allure.step("Нажатие на поле категория");
        creatingNewsElements.getCategoryFieldNews().perform(click()).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void clickDateField() {
        //  Allure.step("Нажатие на поле дата");
        creatingNewsElements.getPublicationDateFieldNews().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickTimeField() {
        // Allure.step("Нажатие на поле время");
        creatingNewsElements.getTimeFieldNews().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickConfirmationButtonToExitTheNewsCreation() {
        //   Allure.step("Нажатие на кнопку подтверждения выхода из создания новости");
        creatingNewsElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void clickCancelNewsCreationButton() {
        //Allure.step("Нажатие на кнопку отмены создания новости");
        creatingNewsElements.getCancelButton2().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void clickConfirmationButtonToCancelTheCreationOfTheNews() {
        //  Allure.step("Нажатие на кнопку подтверждения отмены создания новости");
        creatingNewsElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void validLanguage(String title) {
        //  Allure.step("Ввод валидного языка");
        creatingNewsElements.getCategoryFieldNews().perform(typeText(title)).perform(closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingNewsElements.getTitleFieldNews().perform(typeText(title)).perform(closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingNewsElements.getDescriptionFieldNews().perform(typeText(title)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void invalidLanguage(String title) {
        //  Allure.step("Ввод невалидного языка");
        try {
            creatingNewsElements.getTitleFieldNews().perform(typeText(title));
        } catch (RuntimeException e) {
            creatingNewsElements.getTitleFieldNews().perform(closeSoftKeyboard());
        }
        SystemClock.sleep(2000);
        try {
            creatingNewsElements.getDescriptionFieldNews().perform(typeText(title));
        } catch (RuntimeException e) {
            creatingNewsElements.getDescriptionFieldNews().perform(closeSoftKeyboard());
        }
        SystemClock.sleep(3000);
    }

    public void checkNameFieldInCreatingNews() {
        //  Allure.step("Проверка идентифицирующих названий полей для заполнения");
        creatingNewsElements.getCategoryName().check(matches(isDisplayed()));
        creatingNewsElements.getTitleName().check(matches(isDisplayed()));
        creatingNewsElements.getPublicationDateName().check(matches(isDisplayed()));
        creatingNewsElements.getTimeName().check(matches(isDisplayed()));
        creatingNewsElements.getDescriptionName().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkAbsenceOfWordsFromRussianLettersInTheFields() {
        //  Allure.step("Проверка на отсутствие в полях слов из русских букв");
        creatingNewsElements.getTitleFieldNews().check(matches(withText(""))).check(matches(isDisplayed()));
        creatingNewsElements.getDescriptionFieldNews().check(matches(withText(""))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkPresenceOfWordsFromEnglishLettersInTheFields(String text) {
        //   Allure.step("Проверка на присудствие в полях слов из английских букв");
        creatingNewsElements.getCategoryFieldNews().check(matches(withText(text))).check(matches(isDisplayed()));
        creatingNewsElements.getTitleFieldNews().check(matches(withText(text))).check(matches(isDisplayed()));
        creatingNewsElements.getDescriptionFieldNews().check(matches(withText(text))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkCalendarAppearance(@NonNull AppActivity activity) {
        //   Allure.step("Проверка появления календаря");
        onView(withClassName(is("android.widget.DatePicker")))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkAppearanceOfTheDropDownList(@NonNull AppActivity activity) {
        // Allure.step("Проверка появления выпадающего списка");
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
