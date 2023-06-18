package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomLogInToClaimsCreation;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomLogInToNewsCreation;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.ClaimsElements;
import ru.iteco.fmhandroid.ui.elements.ControlPanelElements;
import ru.iteco.fmhandroid.ui.elements.CreatingClaimsElements;
import ru.iteco.fmhandroid.ui.elements.CreatingNewsElements;
import ru.iteco.fmhandroid.ui.elements.MainElements;
import ru.iteco.fmhandroid.ui.elements.NewsElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainSteps {

    MainElements mainElements = new MainElements();

    public void clickMenuButton() {
        Allure.step("Нажатие на кнопку меню сверху");
        mainElements.getMainMenuImageButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickClaimsName() {
        Allure.step("Нажатие на название Claims");
        mainElements.getTitleClaimsScreen().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickNewsName() {
         Allure.step("Нажатие на название News");
        mainElements.getTitleNews().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickAboutName() {
        Allure.step("Нажатие на название About");
        mainElements.getTitleAbout().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickMainName() {
        Allure.step("Нажатие на название Main");
        mainElements.getTitleMain().perform(click());
        SystemClock.sleep(3000);
    }

    public void checkNameMainScreen() {
        Allure.step("Проверка нахождения пользователя на экране Main");
        mainElements.getChapterNews().check(matches(isDisplayed()));
        mainElements.getChapterClaims().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void clickButtonInTheFormOfButterfly() {
         Allure.step("Нажатие на кнопку в виде бабочки");
        mainElements.getThematicQuotesButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void checkTheNameOfTheNewsBlock() {
        Allure.step("Проверка названия блока с новостями");
        mainElements.getChapterNews().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void clickButtonExpandTheNewsFeed() {
        Allure.step("Нажатие на кнопку свернуть/развернуть новости");
        mainElements.getExpandNewsFeedButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void nameAllNewsIsVisible() {
        Allure.step("Проверка видимости названия в блоке ALL NEWS");
        mainElements.getAllNews().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void nameAllNewsIsNotVisible() {
        Allure.step("Проверка видимости названия в блоке all News не видно");
        mainElements.getAllNews().check(matches(not(isDisplayed())));
        SystemClock.sleep(3000);
    }

    public void clickAllNews() {
        Allure.step("Нажатие на текстовую ALL NEWS");
        mainElements.getAllNews().perform(click());
    }

    public void checkTheNameOfTheClaimsBlock() {
         Allure.step("Проверка названия блока с претензиями");
        mainElements.getChapterClaims().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void expandTheClaimsFeed() {
        Allure.step("Нажатие на кнопку свернуть/развернуть претензии");
        mainElements.getExpandTheClaimsFeedButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickButtonPlus() {
        Allure.step("Нажатие на кнопку +");
        mainElements.getCreateClaimsButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void nameAllClaimsIsVisible() {
        Allure.step("Проверка видимости названия в блоке all Claims");
        mainElements.getAllClaims().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void nameAllClaimsIsNotVisible() {
        Allure.step("Проверка видимости названия в блоке all Claims не видно");
        mainElements.getAllClaims().check(matches(not(isDisplayed())));
        SystemClock.sleep(3000);
    }

    public void clickAllClaims() {
        Allure.step("Нажатие на текс ALL CLAIMS");
        mainElements.getAllClaims().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickFirstClaimInTheList(int position) {
        Allure.step("Нажатие на первую претензию в списке");
        mainElements.getClaimList().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    public void checkNamesInTheList() {
        Allure.step("Проверка названий страниц в списке");
        mainElements.getTitleMain().check(matches(isDisplayed()));
        mainElements.getTitleNews().check(matches(isDisplayed()));
        mainElements.getTitleClaimsScreen().check(matches(isDisplayed()));
        mainElements.getTitleAbout().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void randomTransitionToCreatingClaims() {
        randomLogInToClaimsCreation();
    }

    public void swipeUpBlockClaims() {
        Allure.step("Свайп до первой претензии");
        mainElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        mainElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        mainElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        mainElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        SystemClock.sleep(5000);
    }

    public static void enterCreateNewsOne() {
        MainElements mainScreenElements = new MainElements();
        NewsElements newsScreenElements = new NewsElements();
        ControlPanelElements controlPanelScreenElements = new ControlPanelElements();
        CreatingNewsElements creatingNewsScreenElements = new CreatingNewsElements();

        mainScreenElements.getAllNews().perform(click());
        SystemClock.sleep(3000);
        newsScreenElements.getScreenNameNews().check(matches(isDisplayed()));
        newsScreenElements.getEditButton().perform(click());
        SystemClock.sleep(3000);
        controlPanelScreenElements.getControlPanelNameScreen().check(matches(isDisplayed()));
        controlPanelScreenElements.getCreateNewsButton().perform(click());
        SystemClock.sleep(5000);
        creatingNewsScreenElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingNewsScreenElements.getNewsNameScreen().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public static void enterCreateNewsTwo() {
        MainElements mainScreenElements = new MainElements();
        NewsElements newsScreenElements = new NewsElements();
        CreatingNewsElements creatingNewsScreenElements = new CreatingNewsElements();
        ControlPanelElements controlPanelScreenElements = new ControlPanelElements();

        mainScreenElements.getMainMenuImageButton().perform(click());
        SystemClock.sleep(3000);
        mainScreenElements.getTitleNews().perform(click());
        newsScreenElements.getScreenNameNews().check(matches(isDisplayed()));
        newsScreenElements.getEditButton().perform(click());
        SystemClock.sleep(3000);
        controlPanelScreenElements.getControlPanelNameScreen().check(matches(isDisplayed()));
        controlPanelScreenElements.getCreateNewsButton().perform(click());
        SystemClock.sleep(5000);
        creatingNewsScreenElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingNewsScreenElements.getNewsNameScreen().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public static void enterCreateClaimsButtonPlus() {
        MainElements mainScreenElements = new MainElements();
        CreatingClaimsElements creatingClaimsScreenElements = new CreatingClaimsElements();

        mainScreenElements.getCreateClaimsButton().perform(click());
        SystemClock.sleep(3000);
        creatingClaimsScreenElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getClaimsNameScreen().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public static void enterCreateClaimsAllClaims() {
        MainElements mainScreenElements = new MainElements();
        ClaimsElements claimsScreenElements = new ClaimsElements();
        CreatingClaimsElements creatingClaimsScreenElements = new CreatingClaimsElements();

        mainScreenElements.getAllClaims().perform(click());
        SystemClock.sleep(3000);
        claimsScreenElements.getCreateClaimsButton().perform(click());
        SystemClock.sleep(3000);
        creatingClaimsScreenElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getClaimsNameScreen().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public static void enterCreateClaimsActionButton() {
        MainElements mainScreenElements = new MainElements();
        ClaimsElements claimsScreenElements = new ClaimsElements();
        CreatingClaimsElements creatingClaimsScreenElements = new CreatingClaimsElements();

        mainScreenElements.getMainMenuImageButton().perform(click());
        SystemClock.sleep(3000);
        mainScreenElements.getTitleClaimsScreen().perform(click());
        SystemClock.sleep(3000);
        claimsScreenElements.getCreateClaimsButton().perform(click());
        SystemClock.sleep(3000);
        creatingClaimsScreenElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getClaimsNameScreen().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void clickExpandNewsDescriptionButton(int position) {
       // Allure.step("Нажатие на кнопку развертывания/свертывания описания Новости");
        mainElements.getExpandNewsButton().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    public void checkTextOfTheNewsDescriptionIsVisible(int position) {
       // Allure.step("Проверка видемости текста описания новости");
        String descriptionText = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
        ViewInteraction textNews =  onView(allOf(withId(R.id.news_item_description_text_view), withText(descriptionText)));
        SystemClock.sleep(3000);
        textNews.check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void randomTransitionToCreatingNews() {
        randomLogInToNewsCreation();
    }
}
