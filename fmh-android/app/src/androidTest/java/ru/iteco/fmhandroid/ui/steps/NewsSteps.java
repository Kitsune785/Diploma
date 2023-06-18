package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.dateFormat;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import java.text.ParseException;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.NewsElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class NewsSteps {

    NewsElements newsElements = new NewsElements();

    public void checkNameFilterNews() {
        Allure.step("Название страницы filterNews");
        newsElements.getFilterNewsPage().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void clickButtonToGoToFilterNews() {
        Allure.step("Нажатие на кнопку перехода в Filter News");
        newsElements.getFilterNewsButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickNews(int position) {
        Allure.step("Нажатие на новость");
        newsElements.getNewsRecyclerList().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    public void clickButtonToGoToTheControlPanel() {
        Allure.step("Нажатие на кнопку перехода в Control panel");
        newsElements.getEditButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickExpandNewsDescriptionButton(int position) {
        Allure.step("Нажатие на кнопку свернуть/развернуть описания Новости");
        newsElements.getExpandNewsButton().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    public void checkTheNameOfTheNews() {
        Allure.step("Проверка названия страницы News");
        newsElements.getScreenNameNews().check(matches(withText("News"))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTextOfTheNewsDescriptionIsVisible(int position) {
        Allure.step("Проверка видемости текста описания новости");
        String descriptionText = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
        ViewInteraction textNews =  onView(allOf(withId(R.id.news_item_description_text_view), withText(descriptionText)));
        SystemClock.sleep(3000);
        textNews.check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkDateOfTheFoundNewsWithTheDataEnteredForTheSearch(
            String dateOnCardNews, String dateStartInput, String dateEndInput, String localDate) throws ParseException {
        Allure.step("Проверка даты найденной новости с данными введенными для поиска");
        dateFormat(dateOnCardNews).after(dateFormat(dateStartInput));
        dateFormat(dateOnCardNews).before(dateFormat(dateEndInput));

        if (localDate.equals(dateOnCardNews)) {
            assertEquals(localDate, dateOnCardNews);
        } else {
            assertNotEquals(localDate, dateOnCardNews);
        }
        SystemClock.sleep(3000);
    }

    public void checkDataOfTheFoundNewsWithTheEnteredSearchData(String category, String categoryText) {
        Allure.step("Проверка данных найденной новости с введенными данными поиска");
        assertEquals(category, categoryText);
        SystemClock.sleep(3000);
    }

    public void checkingTheFoundDataFromTheNewsWithTheDataEnteredForTheSearch(
            String dateOnCardNews, String dateStartInput, String dateEndInput, String category, String localDate, int position) throws ParseException {
         Allure.step("Проверка найденных данных из новости с введенными для поиска");
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

    public void checkDisplayOfTheFoundNewsData(int position) {
        Allure.step("Проверка отображения найденной новости");
        onView(withIndex(withId(R.id.news_item_date_text_view), position)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.news_item_title_text_view), position)).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkDisplayOfTheInscriptionInTheAbsenceOfFoundNews() {
        Allure.step("Проверка уведомления при отсутствии найденных по критериям новостей");
        onView(withText(startsWith("There is nothing here yet"))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public String dateOnCardNews(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_date_text_view), position)));
    }

    public String categoryText(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String news() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
    }

    public String takeTheDescriptionOfTheFirstNewsBeforeSorting(int position) {
      //  Allure.step("Взять описание первой новости до сортировки");
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
    }

    public String takeTheNameOfTheFirstNewsAfterSorting(int position) {
       // Allure.step("Взять название первой новости после первой сортировки");
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String takeTheDescriptionOfTheFirstNewsAfterTheFirstSorting(int position) {
       // Allure.step("Взять описание первой новости после первой сортировки");
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
    }

    public String takeTheNameOfTheFirstNewsAfterTwoSorts(int position) {
       // Allure.step("Взять название первой новости после двух сортировок");
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String takeTheDescriptionOfTheFirstNewsAfterTwoSorts(int position) {
      //  Allure.step("Взять описание первой новости после двух сортировок");
        return Helper.Text.getText(onView(allOf(withIndex(withId(R.id.news_item_description_text_view), position))));
    }

    public void reconciliationOfNewsTitlesAndDescriptionsAfterSorting(
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

    public void clickNewsSortingChangeButton() {
        //  Allure.step("Нажатие на кнопку смены сортировки новостей");
        newsElements.getButtonSort().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickButtonExpandTheNewsFeed(int position) {
        //  Allure.step("Нажатие на кнопку для развертывания/свертывания новостного блока");
        newsElements.getExpandNewsButton().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    public String takeTheNameOfTheFirstNews(int position) {
        //  Allure.step("Взять название первой новости до сортировки");
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }
}
