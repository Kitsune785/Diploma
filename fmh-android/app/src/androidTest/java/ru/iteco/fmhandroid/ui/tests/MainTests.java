package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.Rand.random;
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
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;
import ru.iteco.fmhandroid.ui.steps.ThematicQuotesSteps;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    MainSteps mainSteps = new MainSteps();
    NewsSteps newsSteps = new NewsSteps();
    AboutSteps aboutSteps = new AboutSteps();
    ClaimsSteps claimsSteps = new ClaimsSteps();
    CreatingClaimsSteps creatingClaimsSteps = new CreatingClaimsSteps();
    ThematicQuotesSteps thematicQuotesSteps = new ThematicQuotesSteps();

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(authInfo());
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("When opening the menu, a list of pages is displayed Main, Claims, News, About")
    @Description("При открытии меню отображается список страниц Main, Claims, News, About ID 010")
    public void shouldBeDisplayedPageNamesInTheMenu() {
        mainSteps.clickMenuButton();
        mainSteps.checkNamesInTheList();
    }

    @Test
    @DisplayName("Switching from the main page to the page with claims through the menu")
    @Description("Переход с главной страницы на страницу с претензиями через меню ID 011")
    public void shouldClaimsPageOpen() {
        mainSteps.clickMenuButton();
        mainSteps.clickClaimsName();
        claimsSteps.checkNameClaims();
    }

    @Test
    @DisplayName("Switching from the main page to the news page through the menu")
    @Description("Переход с главной страницы на страницу с новостями через меню ID 012")
    public void shouldNewsPageOpen() {
        mainSteps.clickMenuButton();
        mainSteps.clickNewsName();
        newsSteps.checkTheNameOfTheNews();
    }

    @Test
    @DisplayName("Switching from the main page to the About page through the menu")
    @Description("Переход с главной страницы на страницу О приложении через меню ID 013")
    public void shouldAboutPageOpen() {
        mainSteps.clickMenuButton();
        mainSteps.clickAboutName();
        aboutSteps.checkNameAbout();
    }

    @Test
    @DisplayName("Switching from any page to the main page through the menu")
    @Description("Переход с любой страницы на главную страницу  через меню ID 014")
    public void shouldMainPageOpen() {
        mainSteps.clickMenuButton();
        mainSteps.clickNewsName();
        newsSteps.checkTheNameOfTheNews();
        mainSteps.clickMenuButton();
        mainSteps.clickMainName();
        mainSteps.checkTheNameOfTheNewsBlock();
        mainSteps.checkTheNameOfTheClaimsBlock();
    }

    @Test
    @DisplayName("Going to the Love Is All page from the main page")
    @Description("Переход на страницу Love is All с главной страницы ID 015")
    public void shouldBeTransitionToThePageWithQuotes() {
        mainSteps.clickButtonInTheFormOfButterfly();
        thematicQuotesSteps.checkName();
    }

    @Test
    @DisplayName("When you click on the main page, All news should go to the page with the news")
    @Description("При нажатии на главной странице All news должен перейти на страницу с новостями ID 016")
    public void shouldGoToTheNewsPage() {
        mainSteps.clickAllNews();
        newsSteps.checkTheNameOfTheNews();
    }

    @Test
    @DisplayName("When you click on the main page All Claims should go to the page with claims")
    @Description("При нажатии на главной странице All Claims должен перейти на страницу с претензиями ID 017")
    public void shouldGoToTheClaimsPage() {
        mainSteps.clickAllClaims();
        claimsSteps.checkNameClaims();
    }

    @Test
    @DisplayName("Going to the page for creating a new claim through the main screen")
    @Description("Переход на страницу создания новой претензии через главный экран ID 018")
    public void shouldBeOpenedthePageWithTheCreationOfClaim() {
        mainSteps.clickButtonPlus();
        creatingClaimsSteps.checkNameOfTheClaimCreation();
    }

    @Test
    @DisplayName("When you click on the main screen on the news field, the block with the news is collapsed / unfolded")
    @Description("При нажатии на главном экране на поле новостей сварачивается/разворачивается блок с новостями ID 019")
    public void shouldBeExpandedAndCollapseNewsBlock() {
        mainSteps.clickButtonExpandTheNewsFeed();
        mainSteps.nameAllNewsIsNotVisible();
        mainSteps.clickButtonExpandTheNewsFeed();
        mainSteps.nameAllNewsIsVisible();
    }

    @Test
    @DisplayName("When you click on the main screen on the claim field, the block with claims is welded / unfolded")
    @Description("При нажатии на главном экране на поле претензии сварачивается/разворачивается блок с претензиями ID 020")
    public void shouldBeExpandedAndCollapseNewsClaims() {
        mainSteps.expandTheClaimsFeed();
        mainSteps.nameAllClaimsIsNotVisible();
        mainSteps.expandTheClaimsFeed();
        mainSteps.nameAllClaimsIsVisible();
    }

    @Test
    @DisplayName("When you click on a claim in the claims block, you go to the selected claim")
    @Description("При нажатии на претензию в блоке претензий происходи переход к выбранной претензии ID 021")
    public void shouldBeTransitionInTheSelectedClaim() {
        int position = 0;

        mainSteps.swipeUpBlockClaims();
        mainSteps.clickFirstClaimInTheList(position);
        claimsSteps.verificationOfIdentifyingNamesInTheClaim();
    }

    @Test
    @DisplayName("On the main screen, the description of the news should be collapsed/expanded")
    @Description("На главном экране должно сворачиваться/разворачиваться описание новости ID 138")
    public void shouldCollapseUnfoldNewsDescription() {
        int position = random(0, 1, 2);

        mainSteps.clickExpandNewsDescriptionButton(position);
        mainSteps.checkTextOfTheNewsDescriptionIsVisible(position);
    }
}
