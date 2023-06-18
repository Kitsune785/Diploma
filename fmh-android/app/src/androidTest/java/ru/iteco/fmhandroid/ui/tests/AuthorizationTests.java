package ru.iteco.fmhandroid.ui.tests;

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

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    MainSteps mainSteps = new MainSteps();

    String MessageEmpty = "Login and password cannot be empty";
    String messageWrong = "Wrong login or password";

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            authorizationSteps.checkTheNameOfTheAuthorization();
        } catch (NoMatchingViewException e) {
            authorizationSteps.clickTheExitProfileButton();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(3000);
    }

    @Test
    @DisplayName("Filling in the fields in Latin letters")
    @Description("Заполнение полей латинскими буквами ID 004")
    public void shouldBeFilledInLatinLetters() {
        String loginPassword = "test";

        authorizationSteps.validLanguage(loginPassword);
        authorizationSteps.checkThePresenceOfTheEnteredDataInTheFields(loginPassword);
    }

    @Test
    @DisplayName("Filling in the fields in Cyrillic letters")
    @Description("Заполнение полей кириллическими буквами ID 005")
    public void shouldBeFilledInCyrillicLetters() {
        String invalidLoginPasswordText = "тест";

        try {
            authorizationSteps.invalidLanguage(invalidLoginPasswordText);
        } catch (RuntimeException ignored) {

        } finally {
            authorizationSteps.checkTheAbsenceOfTheEnteredDataInTheFields(invalidLoginPasswordText);
        }
    }

    @Test
    @DisplayName("Login with valid data")
    @Description("Вход в систему с валидными данными ID 006")
    public void shouldBeLogIn() {
        authorizationSteps.validLoginPassword(authInfo());
        mainSteps.checkNameMainScreen();
    }

    @Test
    @DisplayName("Error when entering invalid username and password")
    @Description("Ошибка при вводе невалидного логина и пароля ID 007")
    public void shouldNotEnterApplicationsWithInvalidData() {
        authorizationSteps.invalidAuthorizationLoginPassword();
        authorizationSteps.checkTheWrongLoginOrPassword(ActivityTestRule.getActivity(), messageWrong);
    }

    @Test
    @DisplayName("Error with empty username and password")
    @Description("Ошибка при пустом логине и пароля ID 008")
    public void shouldBeAnErrorWithAnEmptyLoginAndPassword() {
        authorizationSteps.invalidAuthorization();
        authorizationSteps.checkTheLoginAndPasswordCannotBeEmpty(ActivityTestRule.getActivity(), MessageEmpty);
    }

    @Test
    @DisplayName("Exiting the application")
    @Description("Выход из приложения ID 009")
    public void shouldLogOutOfProfile() {
        authorizationSteps.validLoginPassword(authInfo());
        mainSteps.checkNameMainScreen();
        authorizationSteps.clickTheExitProfileButton();
        authorizationSteps.checkTheNameOfTheAuthorization();
    }

    @Test
    @DisplayName("Authorization page name must be Authorization")
    @Description("Название страницы авторизации должно быть Authorization")
    public void shouldBeTheNameAuthorization() {
        authorizationSteps.checkTheNameOfTheAuthorization();
    }

    @Test
    @DisplayName("Field names must be login and password")
    @Description("Наименование полей должно быть login и password ID115")
    public void shouldBeFieldNamesLoginAndPassword () {
        authorizationSteps.checkIdentifyingFieldNames();
    }

    @Test
    @DisplayName("Warning on empty fields when filling in login and password")
    @Description("Предупреждение на пустые поля при заполнении логина и пароля ID 116")
    public void shouldBeWarningIfTheLoginAndPasswordFieldsAreEmpty() {
        authorizationSteps.invalidAuthorization();
        authorizationSteps.checkTheLoginAndPasswordCannotBeEmpty(ActivityTestRule.getActivity(), MessageEmpty);
    }
}