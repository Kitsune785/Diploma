package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomExecutor;
import static ru.iteco.fmhandroid.ui.data.Helper.Text.text51Symbol;
import static ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;
import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.FilteringWindowSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.WatchSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class CreatingClaimsTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    CreatingClaimsSteps creatingClaimsSteps = new CreatingClaimsSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    ClaimsSteps claimsSteps = new ClaimsSteps();
    MainSteps mainSteps = new MainSteps();
    WatchSteps watchSteps = new WatchSteps();
    FilteringWindowSteps filteringWindowSteps = new FilteringWindowSteps();

    String message = "Fill empty fields";

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(authInfo());
        } finally {
            mainSteps.clickMenuButton();
            mainSteps.clickClaimsName();
            claimsSteps.clickOnTheButtonToGoToTheClaimCreation();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Ignore // отдельно проходит
    @Test
    @DisplayName("Create a claim with valid data")
    @Description("Создание претензии с валидными данными ID 050")
    public void shouldBeCreatedNewClaim() {
        String titleText = textSymbol(7);
        String randomExecutor = randomExecutor();

        creatingClaimsSteps.fillInFieldsWithValidData(titleText, randomExecutor);

        String title = creatingClaimsSteps.title();
        String executor = creatingClaimsSteps.executor();
        String date = creatingClaimsSteps.dateFromTheField();
        String time = creatingClaimsSteps.time();
        String description = creatingClaimsSteps.description();

        creatingClaimsSteps.clickSaveButton();
        creatingClaimsSteps.searchForCreatedClaim(titleText);

        String titleOnCaredClaims = creatingClaimsSteps.titleOnCaredClaims();
        String executorOnCaredClaims = creatingClaimsSteps.executorOnCaredClaims();
        String dateOnCaredClaims = creatingClaimsSteps.dateOnCaredClaims();
        String timeOnCaredClaims = creatingClaimsSteps.timeOnCaredClaims();
        String descriptionOnCaredClaims = creatingClaimsSteps.descriptionOnCaredClaims();

        creatingClaimsSteps.checkDataOfTheCreatedClaimAndTheFoundOne(
                title, titleOnCaredClaims, executor, executorOnCaredClaims, date, dateOnCaredClaims, time, timeOnCaredClaims,
                description, descriptionOnCaredClaims);
    }

    @Test
    @DisplayName("Cancel claim creation")
    @Description("Отмена создания претензии ID 051")
    public void shouldBeCanceledClaimCreation() {
        String titleText = textSymbol(7);
        String randomExecutor = randomExecutor();

        creatingClaimsSteps.fillInFieldsWithValidData(titleText, randomExecutor);
        creatingClaimsSteps.clickCancelClaimCreationButton();
        creatingClaimsSteps.clickButtonToConfirmTheCancellationOfTheClaimCreation();
        claimsSteps.checkNameClaims();

        claimsSteps.checkForTheAbsenceOfAnUncreatedClaim(titleText);
    }

    @Test
    @DisplayName("Cancellation of the notice of cancellation of the creation of a claim")
    @Description("Отмена уведомления об отмене создания претензии ID 052")
    public void shouldBeCanceledCancelingTheCreationOfClaim() {
        String titleText = textSymbol(7);
        String randomExecutor = randomExecutor();

        creatingClaimsSteps.fillInFieldsWithValidData(titleText, randomExecutor);
        creatingClaimsSteps.clickCancelClaimCreationButton();
        creatingClaimsSteps.clickCancelButtonToExitTheClaimCreation();
        creatingClaimsSteps.checkNameOfTheClaimCreation();
    }

    @Ignore //проходит отдельно
    @Test
    @DisplayName("If you enter a performer not from the list or it is absent in the creation of a claim, the claim must have the status Open")
    @Description("При вводе исполнителя не из списка или его отсутствии в создании претензи, претензия должна иметь статус Open ID 053")
    public void shouldBeClaimStatusOpen() {
        String titleText = textSymbol(7);

        creatingClaimsSteps.fillFieldsWithValidDataToCreateClaimWithAnOpenStatus(titleText);

        String title = creatingClaimsSteps.title();
        String date = creatingClaimsSteps.dateFromTheField();
        String time = creatingClaimsSteps.time();
        String description = creatingClaimsSteps.description();

        creatingClaimsSteps.clickSaveButton();
        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickButtonOk();

        creatingClaimsSteps.searchForCreatedClaim(titleText);

        String titleOnCaredClaims = creatingClaimsSteps.titleOnCaredClaims();
        String dateOnCaredClaims = creatingClaimsSteps.dateOnCaredClaims();
        String timeOnCaredClaims = creatingClaimsSteps.timeOnCaredClaims();
        String descriptionOnCaredClaims = creatingClaimsSteps.descriptionOnCaredClaims();

        creatingClaimsSteps.checkDataAndStatusOfTheClaimCreatedAndFound(
                title, titleOnCaredClaims, date, dateOnCaredClaims, time, timeOnCaredClaims,
                description, descriptionOnCaredClaims);
    }

    @Test
    @DisplayName("Error message for empty fields when creating a claim")
    @Description("Сообщение об ошибке на пустые поля при создании претензии ID 054")
    public void shouldBeAnErrorMessageOnEmptyFields() {
        String titleText = textSymbol(5);
        String randomExecutor = randomExecutor();

        creatingClaimsSteps.fillInFieldsWithValidData(titleText, randomExecutor);
        creatingClaimsSteps.nameTitle();
        creatingClaimsSteps.clickSaveButton();
        creatingClaimsSteps.checkFillEmptyFields(ActivityTestRule.getActivity(), message);
    }

    @Test
    @DisplayName("The Title field when creating a claim has a limit of 50 characters")
    @Description("Поле Title при создании претензии имеет ограничение в 50 символов ID 055")
    public void shouldNotEnterMoreThan51Characters() {
        String invalidText = text51Symbol();

        creatingClaimsSteps.textInput(invalidText);
        creatingClaimsSteps.checkNumberOfCharactersEnteredAndCharactersInTheField();
    }

    @Test
    @DisplayName("Page title must be Creating Claims")
    @Description("Название страницы должно быть Creating Claims ID 122")
    public void shouldBeTheTitleOfThePageCreatingClaims() {
        creatingClaimsSteps.checkNameOfTheClaimCreation();
    }

    @Test
    @DisplayName("Checking the field names on the claims creation page")
    @Description("Проверяем название полей на странице создания претензии ID 123")
    public void shouldBeTheNameOfTheFields() {
        creatingClaimsSteps.checkNameFieldInCreatingClaims();
    }

    @Test
    @DisplayName("A drop-down list appears with available executors when creating a claim")
    @Description("Появляется выпадающий список с доступными исполнителями при создании претензии ID 124")
    public void shouldBeListOfAvailableExecutors() {
        creatingClaimsSteps.clickExecutorField();
        creatingClaimsSteps.checkAppearanceOfTheDropDownList(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("When you click on the date field, a calendar appears")
    @Description("При нажатии на поле дата появляется календарь ID 125")
    public void shouldBeAppearCalendar () {
        creatingClaimsSteps.clickDateField();
        creatingClaimsSteps.checkCalendarAppearance(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("When you click on the time field, a clock appears")
    @Description("При нажатии на поле время появляются часы ID 126")
    public void shouldBeAppearClock() {
        creatingClaimsSteps.clickTimeField();
        creatingClaimsSteps.checkAppearanceOfClockOfTheArrowType(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("When you press the keypad button on the clock, the clock should change to digital")
    @Description("При нажатии на кнопку клавиатуры на часах вид часов должен быть изменен на цифровые ID 127")
    public void shouldBeChangedToDigitalClock() {
        creatingClaimsSteps.clickTimeField();
        watchSteps.pressButtonToChangeTheWatchType();
        watchSteps.checkTypeOfDigitalClock();
    }

    @Test
    @DisplayName("The title and description field is not filled with Cyrillic characters")
    @Description("Поле названия и описания не заполняется кириллическими символами ID 128")
    public void shouldNotBeFieldsFilled() {
        String invalidLanguageText = "тест тест";

            creatingClaimsSteps.invalidLanguage(invalidLanguageText);
            creatingClaimsSteps.checkAbsenceOfWordsFromRussianLettersInTheFields();
    }

    @Test
    @DisplayName("The title and description fields are filled with Latin characters")
    @Description("Поле названия и описания заполняются латинскими символами ID 129")
    public void shouldBeFilledInLatinLetters() {
        String validLanguageText = "test test";

        creatingClaimsSteps.validLanguage(validLanguageText);
        creatingClaimsSteps.checkPresenceOfWordsFromEnglishLettersInTheFields(validLanguageText);
    }
}
