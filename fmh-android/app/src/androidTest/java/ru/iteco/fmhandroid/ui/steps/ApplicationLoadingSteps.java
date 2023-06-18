package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.ApplicationLoadingElements;

public class ApplicationLoadingSteps {

    ApplicationLoadingElements applicationLoadingElements = new ApplicationLoadingElements();

    public void checkSplashscreenImageView() {
        Allure.step("Просмотр изображения на заставке");
        applicationLoadingElements.getSplashscreenImageView().check(matches(isDisplayed()));
    }

    public void checkSplashscreenTextView() {
        Allure.step("Текстовое представление заставки");
        applicationLoadingElements.getSplashscreenTextView().check(matches(isDisplayed()));
    }

    public void checkProgressIndicator() {
        Allure.step("Индикатора выполнения");
        applicationLoadingElements.getProgressIndicator().check(matches(isDisplayed()));
    }
}
