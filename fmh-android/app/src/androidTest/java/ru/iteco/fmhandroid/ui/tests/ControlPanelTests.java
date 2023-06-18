package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomNews;
import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;
import static ru.iteco.fmhandroid.ui.data.Helper.createNews;
import static ru.iteco.fmhandroid.ui.data.Helper.deletNewsUpToTheNumberOfTenControlPanel;

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
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingNewsSteps;
import ru.iteco.fmhandroid.ui.steps.EditingNewsSteps;
import ru.iteco.fmhandroid.ui.steps.FilterNewsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ControlPanelTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    MainSteps mainSteps = new MainSteps();
    ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
    CreatingNewsSteps creatingNewsSteps = new CreatingNewsSteps();
    FilterNewsSteps filterNewsSteps = new FilterNewsSteps();
    EditingNewsSteps editingNewsSteps = new EditingNewsSteps();

    int position = randomNews(0);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(authInfo());
        } finally {
            deletNewsUpToTheNumberOfTenControlPanel(7);
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("Control panel page name must be Control Panel")
    @Description("Название страницы контрольной панели должно быть Control Panel ID 079")
    public void shouldBeNameControlPanel() {
        controlPanelSteps.checkTheNameOfTheControlPanel();
    }

    @Test
    @DisplayName("Pressing the dotted strip button on the control panel page opens the news filter window")
    @Description("При нажатии на странице контрольной панели кнопки полоски с точками открывается окно фильтра новостей ID 080")
    public void shouldBeOpenNewsFilter() {
        controlPanelSteps.clickTheButtonToGoToTheAdvancedNewsSearch();
        filterNewsSteps.checkingTheScreenNameForNewsSearch();
    }

    @Test
    @DisplayName("When you click the + button on the control panel page, the news creation page opens")
    @Description("При нажатии на странице контрольно панели кнопки + открывается страница создания новости ID 081")
    public void shouldBeOpenNewsCreation() {
        controlPanelSteps.clickOnTheButtonToGoToTheNewsCreation();
        creatingNewsSteps.checkNameOfTheCreatingNews();
    }

    @Test
    @DisplayName("Deleting news on the control panel page")
    @Description("Удаление новости на странице контрольной панели ID 082")
    public void shouldBeRemovedNews() {
        String category = randomCategory();
        String text = Helper.Text.textSymbol(7);

        createNews(text, category);

        String nameNewsItWas = controlPanelSteps.nameNews();
        String publicationDateNewsItWas = controlPanelSteps.publicationDateNews();
        String creationDateNewsItWas = controlPanelSteps.creationDateNews();
        String authorNewsItWas = controlPanelSteps.authorNews();
        String descriptionNewsItWas = controlPanelSteps.descriptionNews();

        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);
        controlPanelSteps.clickOnTheDeleteNewsButton();
        controlPanelSteps.clickOnTheConfirmationButtonToDeleteTheNews();
        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);

        String nameNewsItWasHasBecomes = controlPanelSteps.nameNews();
        String publicationDateNewsItWasHasBecomes = controlPanelSteps.publicationDateNews();
        String creationDateNewsItWasHasBecomes = controlPanelSteps.creationDateNews();
        String authorNewsItWasHasBecomes = controlPanelSteps.authorNews();
        String descriptionNewsItWasHasBecomes = controlPanelSteps.descriptionNews();

        controlPanelSteps.checkTheDataOfTheFirstNewsInTheListBeforeAndAfterDeletingTheNews(
                nameNewsItWas, nameNewsItWasHasBecomes, publicationDateNewsItWas, publicationDateNewsItWasHasBecomes,
                creationDateNewsItWas, creationDateNewsItWasHasBecomes, authorNewsItWas, authorNewsItWasHasBecomes,
                descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    @Test
    @DisplayName("When you click the down arrow on the control panel page next to the news, the information about the news expands")
    @Description("При нажатии на станице контрольной панели стрелки вниз около новости информация о новости разворачивается ID 083")
    public void shouldBeExpandedInformationAboutTheNews() {
        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);
        controlPanelSteps.checkTheVisibilityOfTheNewsDescription();

    }

    @Test
    @DisplayName("Pressing the notepad and pencil button in the control panel opens the news editing page")
    @Description("При нажати кнопки блокнот и каратндаш в контрольной панеле открывается страница редактирования новости ID 121")
    public void shouldBeOpenedPageForEditingTheNews () {
        int position = randomNews( 0, 1, 2);

        controlPanelSteps.clickOnRandomlySelectedNewsItem(position);
        controlPanelSteps.clickOnTheButtonToGoToTheEditingNews(position);
        editingNewsSteps.checkNameOfTheEditingNews();
    }
}
