package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import ru.iteco.fmhandroid.ui.elements.AboutElements;
import io.qameta.allure.kotlin.Allure;

public class AboutSteps {
    AboutElements aboutElements = new AboutElements();

    public void checkNameAbout() {
        Allure.step("Название страницы About");
        aboutElements.getVersion().check(matches(isDisplayed()));
        aboutElements.getNumVersion().check(matches(isDisplayed()));
    }

    public void checkNamePrivacyPolicy() {
        Allure.step("Название пункта Privacy Policy");
        aboutElements.getPrivacyPolicy().check(matches(isDisplayed()));
    }

    public void checkNameLinkPrivacyPolicy() {
        Allure.step("Название ссылки Privacy Policy");
        aboutElements.getLinkPrivacyPolicy().check(matches(isDisplayed()));
    }

    public void checkTheLinksClickabilityLinkPrivacyPolicy() {
        Allure.step("Кликабельность ссылки Privacy Policy");
        aboutElements.getLinkPrivacyPolicy().check(matches(isDisplayed()));
    }

    public void checkNameTermsOfUse() {
        Allure.step("Названия пункта Terms Of Use");
        aboutElements.getTermsOfUse().check(matches(isDisplayed()));
    }

    public void checkNameLinkTermsOfUse() {
        Allure.step("Названия ссылки Terms Of Use");
        aboutElements.getLinkTermsOfUse().check(matches(isDisplayed()));
    }

    public void checkTheLinksClickabilityLinkTermsOfUse() {
        Allure.step("Кликабельность ссылки Terms Of Use");
        aboutElements.getLinkTermsOfUse().check(matches(isClickable()));
    }

    public void checkTheManufacturersName() {
        Allure.step("Названия компании");
        aboutElements.getTeco().check(matches(isDisplayed()));
    }

    public void clickAboutExitButton() {
         Allure.step("Выход из страницы About");
        aboutElements.getAboutExitButton().perform(click());
    }
}








