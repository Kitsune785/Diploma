package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomNews;
import static ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;
import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
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
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.EditingNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;
import ru.iteco.fmhandroid.ui.steps.WatchSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class EditingNewsTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    MainSteps mainSteps = new MainSteps();
    NewsSteps newsSteps = new NewsSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
    EditingNewsSteps editingNewsSteps = new EditingNewsSteps();
    WatchSteps watchSteps = new WatchSteps();

    String messageEmpty = "Fill empty fields";
    String messageSaving = "Saving failed. Try again later.";
    int position = randomNews(1);

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
    @DisplayName("The name of the news editing screen should be Editing News")
    @Description("Название экрана с редактированием новостей должно быть Editing News ID 064")
    public void shouldBeTheTitleEditingNews() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsEditing();
        editingNewsSteps.checkNameOfTheEditingNews();
    }

    @Test
    @DisplayName("When you open the news editing window, the data entered earlier is displayed")
    @Description("При открытии окна редактирования новости, отображаются данные введенные ранее ID 065")
    public void shouldBeDataEnteredEarlierInEditingTheNews() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsEditing();

        String category = editingNewsSteps.categoryField();
        String titleTextNews = editingNewsSteps.titleTextNewsField();
        String publishDate = editingNewsSteps.publishDateField();
        String time = editingNewsSteps.timeField();
        String description = editingNewsSteps.descriptionField();

        editingNewsSteps.checkWhetherTheFieldsAreFilledWithData(
                category, titleTextNews, publishDate, time, description);
    }

    @Test
    @DisplayName("It is possible to edit the news")
    @Description("Есть возможность отредактировать новость ID 066")
    public void shouldBeEditedNews() {
        String text = textSymbol(7);
        String Category = randomCategory();

        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);

        String nameNewsItWas = editingNewsSteps.nameNews();
        String publicationDateNewsItWas = editingNewsSteps.publicationDateNews();
        String creationDateNewsItWas = editingNewsSteps.creationDateNews();
        String descriptionNewsItWas = editingNewsSteps.descriptionNews();

        editingNewsSteps.clickButtonToEnterTheNewsEditing(nameNewsItWas);
        editingNewsSteps.fillNewsFieldsWithNewData(text, Category);

        String nameNewsInput = editingNewsSteps.titleTextNewsField();
        String publicationDateNewsInput = editingNewsSteps.publishDateField();
        String timeNewsInput = editingNewsSteps.timeField();
        String descriptionNewsInput = editingNewsSteps.descriptionField();

        editingNewsSteps.clickSaveButton();
        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);

        String nameNewsItWasHasBecomes = editingNewsSteps.nameNews();
        String publicationDateNewsItWasHasBecomes = editingNewsSteps.publicationDateNews();
        String creationDateNewsItWasHasBecomes = editingNewsSteps.creationDateNews();
        String descriptionNewsItWasHasBecomes = editingNewsSteps.descriptionNews();

        editingNewsSteps.checkInitialDataOfTheNewsWithTheFillingDataAndTheFinal(
                nameNewsItWas, nameNewsInput, publicationDateNewsItWas, publicationDateNewsInput,
                creationDateNewsItWas, timeNewsInput, descriptionNewsItWas, descriptionNewsInput,
                nameNewsItWasHasBecomes, publicationDateNewsItWasHasBecomes, creationDateNewsItWasHasBecomes,
                descriptionNewsItWasHasBecomes);
    }

    @Test
    @DisplayName("Cancel news editing")
    @Description("Отмена редактирования новости ID 067")
    public void shouldBeCanceledNewsEditing() {
        String text = textSymbol(7);
        String Category = randomCategory();

        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);

        String nameNewsItWas = editingNewsSteps.nameNews();
        String publicationDateNewsItWas = editingNewsSteps.publicationDateNews();
        String creationDateNewsItWas = editingNewsSteps.creationDateNews();
        String descriptionNewsItWas = editingNewsSteps.descriptionNews();

        editingNewsSteps.clickButtonToEnterTheNewsEditing(nameNewsItWas);
        editingNewsSteps.fillNewsFieldsWithNewData(text, Category);

        String nameNewsInput = editingNewsSteps.titleTextNewsField();
        String publicationDateNewsInput = editingNewsSteps.publishDateField();
        String timeNewsInput = editingNewsSteps.timeField();
        String descriptionNewsInput = editingNewsSteps.descriptionField();

        editingNewsSteps.clickCancelNewsEditingButton();
        editingNewsSteps.clickButtonToConfirmTheCancellationOfNewsEditing();

        String nameNewsItWasHasBecomes = editingNewsSteps.nameNews();
        String publicationDateNewsItWasHasBecomes = editingNewsSteps.publicationDateNews();
        String creationDateNewsItWasHasBecomes = editingNewsSteps.creationDateNews();
        String descriptionNewsItWasHasBecomes = editingNewsSteps.descriptionNews();
        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);

        editingNewsSteps.checkNewsDataBeforeEditingAndAfterCancelingEditing(
                nameNewsItWas, nameNewsInput, publicationDateNewsItWas, publicationDateNewsInput,
                creationDateNewsItWas, timeNewsInput, descriptionNewsItWas, descriptionNewsInput,
                nameNewsItWasHasBecomes, publicationDateNewsItWasHasBecomes, creationDateNewsItWasHasBecomes,
                descriptionNewsItWasHasBecomes);
    }

    @Test
    @DisplayName("Error warning when editing on empty fields")
    @Description("Предупреждение об ошибке при редактировании на пустые поля ID 068")
    public void shouldBeWarningAboutAnErrorOnEmptyFieldsInNewsEditing() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsEditing();
        editingNewsSteps.deletNewsTitle();
        editingNewsSteps.clickSaveButton();
        editingNewsSteps.checkFillEmptyFields(ActivityTestRule.getActivity(), messageEmpty);
    }

    @Test
    @DisplayName("When selecting a category not from the list, an error is highlighted when editing")
    @Description("При выборе категории не из списка при редактировании подсвечивается ошибка ID 069")
    public void shouldBeAnErrorForCategoryNotFromTheList() {
        String text = textSymbol(7);

        controlPanelSteps.clickOnTheButtonToGoToTheNewsEditing();
        editingNewsSteps.enterTextInTheCategoryField(text);
        editingNewsSteps.clickSaveButton();
        editingNewsSteps.checkSavingFailedTryAgainLater(ActivityTestRule.getActivity(), messageSaving);
    }

    @Test
    @DisplayName("Cancellation of news editing cancel notification")
    @Description("Отмена уведомления об отмене редактирования новости ID 070")
    public void shouldBeCanceledUndoEditingMessage() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsEditing();
        editingNewsSteps.clickCancelNewsEditingButton();
        editingNewsSteps.clickCancelButtonToExitEditing();
        editingNewsSteps.checkNameOfTheEditingNews();
    }

    @Test
    @DisplayName("Change of news status from Active to Not Active")
    @Description("Смена статуса новости с Active на Not Active ID 071")
    public void shouldBeChangedStatusFromActiveToNotActive() {
        Helper.setUpStatusNewsNotActive(position);

        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);

        String nameNewsItWas = editingNewsSteps.nameNews();
        ViewInteraction statusBefore = editingNewsSteps.statusNews(nameNewsItWas);

        editingNewsSteps.clickNews(nameNewsItWas);
        editingNewsSteps.clickCheckBox();
        editingNewsSteps.clickSaveButton();

        String statusAfter = editingNewsSteps.statusNewsText(nameNewsItWas);
        ViewInteraction statusAfter2 = editingNewsSteps.statusNewsPosition(position);

        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);
        controlPanelSteps.checkTheStatusChange(statusBefore.toString(), statusAfter, statusAfter2);
    }
}
