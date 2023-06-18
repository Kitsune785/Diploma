package ru.iteco.fmhandroid.ui.tests;

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
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    MainSteps mainSteps = new MainSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    AboutSteps aboutSteps = new AboutSteps();

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(Helper.authInfo());
        } finally {
            mainSteps.clickMenuButton();
            mainSteps.clickAboutName();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(3000);
    }

    @Test
    @DisplayName("There is information about the current version of the application")
    @Description("Есть информация о текущей версии приложения ID 022")
    public void shouldDisplayInformationAboutTheVersion() {
        aboutSteps.checkNameAbout();
    }

    @Test
    @DisplayName("Link to privacy policy works")
    @Description("Ссылка на политику конфиденциальности работает ID 023")
    public void shouldFollowTheLinkPrivacyPolicy() {
        aboutSteps.checkNamePrivacyPolicy();
        aboutSteps.checkNameLinkPrivacyPolicy();
        aboutSteps.checkNameLinkPrivacyPolicy();
        aboutSteps.checkNameLinkTermsOfUse();
    }

    @Test
    @DisplayName("Link to terms of use works")
    @Description("Ссылка на условия использования работает ID 024")
    public void shouldFollowTheLinkTermsOfUse() {
        aboutSteps.checkNameTermsOfUse();
        aboutSteps.checkNameLinkTermsOfUse();
        aboutSteps.checkTheLinksClickabilityLinkTermsOfUse();
        aboutSteps.checkTheLinksClickabilityLinkPrivacyPolicy();
    }

    @Test
    @DisplayName("Availability of information about the manufacturer")
    @Description("Наличие информации о изготовителе ПО ID 025")
    public void shouldNameOfTheManufacturer() {
        aboutSteps.checkTheManufacturersName();
    }

    @Test
    @DisplayName("Return to previous page")
    @Description("Возврата на предыдущую страницу ID 026")
    public void shouldReturnPriviousPage() {
        aboutSteps.clickAboutExitButton();
    }
}
