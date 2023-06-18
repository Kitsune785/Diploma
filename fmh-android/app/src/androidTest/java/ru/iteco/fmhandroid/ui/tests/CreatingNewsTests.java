package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCategory;
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
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;
import ru.iteco.fmhandroid.ui.steps.WatchSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class CreatingNewsTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    MainSteps mainSteps = new MainSteps();
    NewsSteps newsSteps = new NewsSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
    CreatingNewsSteps creatingNewsSteps = new CreatingNewsSteps();
    WatchSteps watchSteps = new WatchSteps();

    String messageSaving = "Saving failed. Try again later.";
    String messageEmpty = "Fill empty fields";
    int position = 0;

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(authInfo());
        } finally {
            mainSteps.clickMenuButton();
            mainSteps.clickNewsName();
            newsSteps.clickButtonToGoToTheControlPanel();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("The name of the news creation page should be Creating News")
    @Description("Название страницы создания новостей должно быть Creating News ID 045")
    public void shouldBePageNameCreatingNews() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.checkNameOfTheCreatingNews();
    }

    @Ignore // Отдельно проходит
    @Test
    @DisplayName("Creating news with valid data")
    @Description("Создание новости с валидными данными ID 046")
    public void shouldBeCreatedNews() {
        String text = textSymbol(7);
        String validCategory = randomCategory();

        creatingNewsSteps.choosNews(position);

        String nameNewsItWas = creatingNewsSteps.nameNews();
        String publicationDateNewsItWas = creatingNewsSteps.publicationDateNews();
        String creationDateNewsItWas = creatingNewsSteps.creationDateNews();
        String authorNewsItWas = creatingNewsSteps.authorNews();
        String descriptionNewsItWas = creatingNewsSteps.descriptionNews();

        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.fillFieldsWithValidData(text, validCategory);
        creatingNewsSteps.clickSaveNewsButton();
        int positionNews = Helper.Search.searchNews(text);
        creatingNewsSteps.choosNews(positionNews);

        String nameNewsItWasHasBecomes = creatingNewsSteps.nameNewsPosition(positionNews);
        String publicationDateNewsItWasHasBecomes = creatingNewsSteps.publicationDateNewsPosition(positionNews);
        String creationDateNewsItWasHasBecomes = creatingNewsSteps.creationDateNewsPosition(positionNews);
        String authorNewsItWasHasBecomes = creatingNewsSteps.authorNewsPosition(positionNews);
        String descriptionNewsItWasHasBecomes = creatingNewsSteps.descriptionNewsPosition(positionNews);

        creatingNewsSteps.comparingTheDataOfTheCreatedNewsWithTheDataOfTheFirstNewsFromTheList(
                nameNewsItWas, nameNewsItWasHasBecomes, publicationDateNewsItWas, publicationDateNewsItWasHasBecomes,
                creationDateNewsItWas, creationDateNewsItWasHasBecomes, authorNewsItWas, authorNewsItWasHasBecomes,
                descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    @Test
    @DisplayName("Cancel news creation")
    @Description("Отмена создания новости ID 047")
    public void shouldBeCanceledNewsCreation() {
        String text = textSymbol(7);
        String validCategory = randomCategory();

        creatingNewsSteps.choosNews(position);

        String nameNewsItWas = creatingNewsSteps.nameNews();
        String publicationDateNewsItWas = creatingNewsSteps.publicationDateNews();
        String creationDateNewsItWas = creatingNewsSteps.creationDateNews();
        String authorNewsItWas = creatingNewsSteps.authorNews();
        String descriptionNewsItWas = creatingNewsSteps.descriptionNews();

        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.fillFieldsWithValidData(text, validCategory);
        creatingNewsSteps.clickExitButtonFromNewsCreation();
        creatingNewsSteps.clickConfirmationButtonToCancelTheCreationOfTheNews();

        creatingNewsSteps.choosNews(position);

        String nameNewsItWasHasBecomes = creatingNewsSteps.nameNews();
        String publicationDateNewsItWasHasBecomes = creatingNewsSteps.publicationDateNews();
        String creationDateNewsItWasHasBecomes = creatingNewsSteps.creationDateNews();
        String authorNewsItWasHasBecomes = creatingNewsSteps.authorNews();
        String descriptionNewsItWasHasBecomes = creatingNewsSteps.descriptionNews();

        creatingNewsSteps.checkDataOfTheFirstNewsFromTheListMustMatchAfterCancelingTheCreationOfTheNews(
                nameNewsItWas, nameNewsItWasHasBecomes, publicationDateNewsItWas, publicationDateNewsItWasHasBecomes,
                creationDateNewsItWas, creationDateNewsItWasHasBecomes, authorNewsItWas, authorNewsItWasHasBecomes,
                descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    @Test
    @DisplayName("If the fields in the news creation window are empty, an error appears when trying to save")
    @Description("При пустых полях в окне создания новости появляется ошибка при опытке сохранения ID 048")
    public void shouldBeAnErrorSavingNewsWithEmptyFields() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.clickSaveNewsButton();
        creatingNewsSteps.checkFillEmptyFields(ActivityTestRule.getActivity(), messageEmpty);
    }

    @Test
    @DisplayName("When creating a news item with an invalid category value, an error is highlighted")
    @Description("При создании новости с невалидным значением категории подсвечивается ошибка ID 049")
    public void shouldBeAnErrorOnSavingWithAnInvalidCategory() {
        String text = textSymbol(7);

        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.fillCategoryField(text);
        creatingNewsSteps.clickSaveNewsButton();
        creatingNewsSteps.checkSaveFailedTryAgainLater(ActivityTestRule.getActivity(), messageSaving);
    }

    @Test
    @DisplayName("Field names on the news creation page")
    @Description("Название полей на странице создания новости ID 130")
    public void shouldBeTheNameOfTheFieldsToFill() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.checkNameFieldInCreatingNews();
    }

    @Test
    @DisplayName("Dropdown list with available news creation categories")
    @Description("Выпадающий список с доступными категориями создания новостей ID 131")
    public void shouldBeDropDownListWithCategories() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.clickCategoryField();
        creatingNewsSteps.checkAppearanceOfTheDropDownList(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("When you click on the field with the date, a calendar should appear")
    @Description("При нажатии на поле с датой должен появиться календарь ID 132")
    public void shouldBeAppearCalendar() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.clickDateField();
        creatingNewsSteps.checkCalendarAppearance(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("When you click on the time field, a clock should appear")
    @Description("При нажатии на поле время должны появиться часы ID 133")
    public void shouldBeAppearClock() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.clickTimeField();
        creatingNewsSteps.checkAppearanceOfClockOfTheArrowType(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("When you press the button in the form of a keyboard in the watch, the clock changes to a digital view")
    @Description("При нажатии в часах кнопки в виде клавиатуры часы меняются на цифровой вид ID 134")
    public void shouldBeChangedToDigitalClock() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.clickTimeField();
        watchSteps.pressButtonToChangeTheWatchType();
        watchSteps.checkTypeOfDigitalClock();
    }

    @Test
    @DisplayName("The fields in the creation of the news are filled in Latin letters")
    @Description("Поля в создании новости заполняются латинскими буквами ID 135")
    public void shouldBeFieldsFilledInLatinLetters() {
        String validLanguageText = "test test";

        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.validLanguage(validLanguageText);
        creatingNewsSteps.checkPresenceOfWordsFromEnglishLettersInTheFields(validLanguageText);
    }

    @Test
    @DisplayName("Fields in news creation are not filled with Cyrillic characters")
    @Description("Поля в создании новости не заполняются кириллическими символами ID")
    public void shouldNotBeFieldsFilledWithCyrillicCharacters() {
        String invalidLanguageText = "тест тест";

        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.invalidLanguage(invalidLanguageText);
        creatingNewsSteps.checkAbsenceOfWordsFromRussianLettersInTheFields();
    }
}
