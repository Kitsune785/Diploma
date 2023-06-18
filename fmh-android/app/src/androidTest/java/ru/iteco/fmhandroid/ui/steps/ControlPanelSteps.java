package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.dateFormat;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.localDate;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import java.text.ParseException;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.ControlPanelElements;

public class ControlPanelSteps {

    ControlPanelElements controlPanelElements = new ControlPanelElements();

    public void clickOnTheButtonToGoToTheNewsCreation() {
        Allure.step("Нажатие на кнопку перехода к странице создания новости");
        controlPanelElements.getCreateNewsButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickTheButtonToGoToTheAdvancedNewsSearch() {
        Allure.step("Нажатие на кнопку для перехода на страницу расширенного поиска новостей");
        controlPanelElements.getFilterNewsButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheDeleteNewsButton() {
        Allure.step("Нажатие на кнопку удаления новости");
        controlPanelElements.getDeleteNews().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheDeleteNewsButtonPosition(String nameNews) {
        Allure.step("Нажатие на кнопку удаления новости");
        onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNews)))))))).perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheConfirmationButtonToDeleteTheNews() {
        Allure.step("Нажатие на кнопку подтверждения удаления новости");
        controlPanelElements.getOkDeleteNewsButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheButtonToGoToTheNewsEditing() {
        Allure.step("Нажатие на кнопку перехода на страницу редактирование новости");
        controlPanelElements.getEditingNewsButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnRandomlySelectedNewsItem(int position) {
        Allure.step("Нажатие на случайную новость");
        controlPanelElements.getRecyclerView().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    public void checkTheNameOfTheControlPanel() {
        Allure.step("Проверка названия Control Panel");
        controlPanelElements.getControlPanelNameScreen().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheStatusActive(int position) {
        Allure.step("Проверка статуса Active");
        String statusAfter = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_published_text_view), position)));

        onView(allOf(withIndex(withId(R.id.news_item_material_card_view), position))).check(matches(isDisplayed()));
        assertEquals("ACTIVE", statusAfter.toUpperCase());
        SystemClock.sleep(3000);
    }

    public void checkTheStatusNotActive(int position) {
        Allure.step("Проверка статуса Not Active");
        String statusAfter = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_published_text_view), position)));
        ViewInteraction statusAfter2 = onView(withIndex(withId(R.id.news_item_published_text_view), position));

        statusAfter2.check(matches(isDisplayed()));
        assertEquals("NOT ACTIVE", statusAfter.toUpperCase());
        SystemClock.sleep(3000);
    }

    public void checkTheStatusOfTheFoundNews(int position) {
        Allure.step("Проверка статуса найденной новости");
        String statusAfter = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_published_text_view), position)));
        ViewInteraction statusAfter2 = onView(withIndex(withId(R.id.news_item_published_text_view), position));

        statusAfter2.check(matches(isDisplayed()));
        if (statusAfter.equals("ACTIVE")) {
            assertEquals("ACTIVE", statusAfter.toUpperCase());
        }
        else if (statusAfter.equals("NOT ACTIVE")){
            assertEquals("NOT ACTIVE", statusAfter.toUpperCase());
            SystemClock.sleep(3000);
        }
    }

    public void checkTheStatusChange(String statusBefore, String statusAfter, ViewInteraction statusAfter2) {
        Allure.step("Проверка смены статуса");
        assertNotEquals(statusBefore, statusAfter);
        assertEquals("ACTIVE", statusAfter.toUpperCase().trim());

        statusAfter2.check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheFoundNews(int position) {
        Allure.step("Проверка найденной новости");
        ViewInteraction dateOnCardNews = onView(allOf(withIndex(withId(R.id.news_item_publication_date_text_view), position)));
        try {
            dateOnCardNews.check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            onView(withText(startsWith("There is nothing here yet"))).check(matches(isDisplayed()));
        }
        SystemClock.sleep(3000);
    }

    public void comparisonOfSearchDataByDateWithNewsData(int position, String dateStartInput, String dateEndInput) throws ParseException {
        Allure.step("Сравнение данных поиска по дате с данными новости");
        String dateOnCardNews = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), position)));

        String localDate = localDate();

        dateFormat(dateOnCardNews).after(dateFormat(dateStartInput));
        dateFormat(dateOnCardNews).before(dateFormat(dateEndInput));

        if (localDate.equals(dateOnCardNews)) {
            assertEquals(localDate, dateOnCardNews);
        } else {
            assertNotEquals(localDate, dateOnCardNews);
        }
        SystemClock.sleep(3000);
    }

    public void comparisonOfSearchDataWithNewsData(
            String dateOnCardNews, String dateStartInput, String dateEndInput, int position, String category) throws ParseException {
        Allure.step("Сравнение данных поиска с данными новости");
        String localDate = localDate();

        dateFormat(dateOnCardNews).after(dateFormat(dateStartInput));
        dateFormat(dateOnCardNews).before(dateFormat(dateEndInput));
        String categoryText = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
        assertEquals(category, categoryText);

        if (localDate.equals(dateOnCardNews)) {
            assertEquals(localDate, dateOnCardNews);
        } else {
            assertNotEquals(localDate, dateOnCardNews);
        }
        SystemClock.sleep(3000);
    }

    public void checkTheEnteredDataForTheSearchWithThoseObtainedFromTheNews(String category, int position) {
        Allure.step("Проверка введенных данных для поиска с полученными из новости");
        String categoryText = categoryTextOnCardNews(position);
        assertEquals(category, categoryText);
        SystemClock.sleep(3000);
    }

    public void checkTheDataOfTheFirstNewsInTheListBeforeAndAfterDeletingTheNews(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
        Allure.step("Проверка данных первой новости в списке до и после удаления новости");
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
        if (authorNewsItWas.equals(authorNewsItWasHasBecomes)) {
            assertEquals(authorNewsItWas, authorNewsItWasHasBecomes);
        } else {
            assertNotEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
        }
        SystemClock.sleep(3000);
    }

    public void checkTheVisibilityOfTheNewsDescription() {
         Allure.step("Проверка видимости описания новости");
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public String dateOnCardNews(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), position)));
    }

    public String categoryTextOnCardNews(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String nameNews() {
        return  Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
    }

    public String nameNewsPosition(int position) {
        return  Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String publicationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)));
    }

    public String creationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_create_date_text_view), 0)));
    }

    public String authorNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_author_name_text_view), 0)));
    }

    public String descriptionNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), 0)));
    }

    public String descriptionNewsPosition(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
    }

    public void changeOfSorting() {
        // Allure.step("Нажатие на кнопку смены сортировки новостей");
        controlPanelElements.getButtonSort().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheCancelConfirmationButtonToDeleteTheNews() {
        // Allure.step("Нажатие на кнопку отмены подтверждения удаления новости");
        controlPanelElements.getCancelDeleteNewsButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheButtonToGoToTheEditingNews(int position) {
         Allure.step("Нажатие на кнопку перехода к экрану Editing News");
        onView(withIndex(withId(R.id.edit_news_item_image_view), position)).perform(click());
        SystemClock.sleep(3000);
    }

    public void checkTheNewsBeforeSortingAndAfter(
            String firstNews, String firstDescription, String lastDescription,
            String firstNewsAgain, String lastNews, String firstDescriptionAgain) {
        //  Allure.step("Проверка новостей до сортировки и после");
        if (firstNews.equals(lastNews)) {
            assertEquals(firstDescription, firstDescriptionAgain);
            assertNotEquals(firstDescription, lastDescription);
        } else {
            assertEquals(firstNews, firstNewsAgain);
            assertNotEquals(firstNews, lastNews);
        }
        SystemClock.sleep(3000);
    }

    public void checkTheDataOfTheFirstNewsInTheListBeforeAndAfterCancelingTheDeletionOfTheNews(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
        //  Allure.step("Проверка данных первой новости в списке до и после отмены удаления новости");
        assertEquals(nameNewsItWas , nameNewsItWasHasBecomes);
        assertEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        assertEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        assertEquals(authorNewsItWas, authorNewsItWasHasBecomes);
        assertEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
        SystemClock.sleep(3000);
    }
}
