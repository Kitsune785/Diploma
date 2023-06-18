package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static ru.iteco.fmhandroid.ui.data.Helper.invalidAuthInfo;
import static ru.iteco.fmhandroid.ui.data.Helper.invalidLoginPasswordAuthInfo;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.AuthorizationElements;

public class AuthorizationSteps {

    AuthorizationElements authorizationElements = new AuthorizationElements();

    public void checkTheNameOfTheAuthorization() {
        Allure.step("Названия Экрана Авторизации");
        authorizationElements.getAuthorization().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void validLoginPassword(Helper.AuthInfo info) {
        Allure.step("Ввод валидного логина и пароля");
        checkTheNameOfTheAuthorization();
        authorizationElements.getLoginField().perform(typeText(info.getLogin()));
        authorizationElements.getPasswordField().perform(typeText(info.getPassword())).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        authorizationElements.getButton().perform(click());
        SystemClock.sleep(5000);
    }

    public void invalidAuthorization() {
        Allure.step("Авторизация с невалидными данными/пустыми данными");
        checkTheNameOfTheAuthorization();
        authorizationElements.getLoginField().perform(typeText(invalidAuthInfo().getLogin()));
        authorizationElements.getPasswordField().perform(typeText(invalidAuthInfo().getPassword())).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        authorizationElements.getButton().perform(click());
    }

    public void clickTheExitProfileButton() {
        Allure.step("Нажатие кнопки выйти из профиля значек человечка");
        ViewInteraction user = onView((withId(R.id.authorization_image_button)));
        user.perform(click());
        ViewInteraction exitButton = onView(withText("Log out"));
        exitButton.perform(click());
        SystemClock.sleep(5000);
    }

    public void validLanguage(String loginPassword) {
        Allure.step("Ввод символов латиницы");
        authorizationElements.getLoginField().perform(typeText(loginPassword));
        authorizationElements.getPasswordField().perform(typeText(loginPassword)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void invalidLanguage(String loginPassword) {
        Allure.step("Ввод кириллических символов");
        authorizationElements.getLoginField().perform(typeText(loginPassword));
        authorizationElements.getPasswordField().perform(typeText(loginPassword)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void checkThePresenceOfTheEnteredDataInTheFields(String loginPassword) {
        Allure.step("Наличие данных в полях");
        authorizationElements.getLoginField().check(matches(isDisplayed()));
        authorizationElements.getPasswordField().check(matches(isDisplayed()));
        authorizationElements.getLoginField().check(matches(withText(loginPassword)));
        authorizationElements.getPasswordField().check(matches(withText(loginPassword)));
        SystemClock.sleep(3000);
    }

    public void checkTheAbsenceOfTheEnteredDataInTheFields(String loginPassword) {
        Allure.step("Проверка отсутствия в полях введенных данных");
        authorizationElements.getLoginField().check(matches(isDisplayed()));
        authorizationElements.getPasswordField().check(matches(isDisplayed()));
        authorizationElements.getLoginField().check(matches(not(withText(loginPassword))));
        authorizationElements.getPasswordField().check(matches(not(withText(loginPassword))));
        SystemClock.sleep(3000);
    }

    public void checkTheLoginAndPasswordCannotBeEmpty(@NonNull AppActivity activity, String text) {
          Allure.step("Сообщение об ошибке на пустые поля");
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheWrongLoginOrPassword(@NonNull AppActivity activity, String text) {
        Allure.step("Сообщение об ошибке на некорректный логин или пароль");
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkIdentifyingFieldNames() {
        Allure.step("Названий полей");
        authorizationElements.getLoginField().check(matches(isDisplayed()));
        authorizationElements.getPasswordField().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void invalidAuthorizationLoginPassword() {
        Allure.step("Авторизация с невалидными данными");
        checkTheNameOfTheAuthorization();
        authorizationElements.getLoginField().perform(typeText(invalidLoginPasswordAuthInfo().getLogin()));
        authorizationElements.getPasswordField().perform(typeText(invalidLoginPasswordAuthInfo().getPassword())).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        authorizationElements.getButton().perform(click());
    }
}