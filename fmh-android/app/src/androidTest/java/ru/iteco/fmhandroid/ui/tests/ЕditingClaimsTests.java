package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomClaims;
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
import ru.iteco.fmhandroid.ui.steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.EditingClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.FilteringWindowSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.WatchSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ЕditingClaimsTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    MainSteps mainSteps = new MainSteps();
    FilteringWindowSteps filteringWindowSteps = new FilteringWindowSteps();
    EditingClaimsSteps editingClaimsSteps = new EditingClaimsSteps();
    ClaimsSteps claimsSteps = new ClaimsSteps();
    WatchSteps watchSteps = new WatchSteps();

    int position = randomClaims(0);

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
            claimsSteps.clickOnTheButtonToGoToTheFiltering();
            filteringWindowSteps.clickCheckBoxInProgress();
            filteringWindowSteps.clickButtonOk();
            claimsSteps.clickOnRandomlySelectedClaim(position);
            claimsSteps.checkTheOpenStatus();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("Claim editing screen title should be Editing Claims")
    @Description("Названия экрана с редактированием претензии должно быть Editing Claims ID 056")
    public void shouldBeEditingClaimsScreenTitle() {
        Helper.Swipes.swipeToBottom();
        claimsSteps.clickOnTheNotepadWithPencilButton();
        editingClaimsSteps.checkNameOfTheScreenForEditingClaims();
    }

    @Test
    @DisplayName("When you open the claim editing window, the data entered earlier is displayed")
    @Description("При открытии окна редактирования претензии, отображаются данные введенные ранее ID 057")
    public void shouldBeFilledInEarliertheFieldsInTheEdit() {
        Helper.Swipes.swipeToBottom();
        claimsSteps.clickOnTheNotepadWithPencilButton();
        editingClaimsSteps.checkFieldsAreFilledIn();
    }

    @Ignore // отдельно проходит
    @Test
    @DisplayName("It is possible to edit the claim")
    @Description("Есть возможность отредактировать претензию ID 058")
    public void shouldBeEditClaim() {
        claimsSteps.checkTheOpenStatus();
        String titleClaimFieldItWas = claimsSteps.authorText();
        String executorClaimFieldItWas = claimsSteps.executorText();
        String dateClaimFieldItWas = claimsSteps.planDateText();
        String timeClaimFieldItWas = claimsSteps.timeText();
        String descriptionClaimFieldItWas = claimsSteps.descriptionClaimField();

        claimsSteps.clickOnTheNotepadWithPencilButton();
        editingClaimsSteps.fillFieldsWithValidData();
        editingClaimsSteps.clickSaveButton();

        String titleClaimFieldItWasHasBecome = claimsSteps.authorText();
        String executorClaimFieldItWasHasBecome = claimsSteps.executorText();
        String dateClaimFieldItWasHasBecome = claimsSteps.planDateText();
        String timeClaimFieldItWasHasBecome = claimsSteps.timeText();
        String descriptionClaimFieldItWasHasBecome = claimsSteps.descriptionClaimField();

        editingClaimsSteps.checkDataBeforeEditingAndAfter(
                titleClaimFieldItWas, titleClaimFieldItWasHasBecome, executorClaimFieldItWas, executorClaimFieldItWasHasBecome,
                dateClaimFieldItWas, dateClaimFieldItWasHasBecome, timeClaimFieldItWas, timeClaimFieldItWasHasBecome,
                descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
    }

    @Test
    @DisplayName("Cancel claim editing")
    @Description("Отмена редактирования претензии ID 059")
    public void shouldBeCanceledClaimEditing() {
        claimsSteps.checkTheOpenStatus();
        String titleClaimFieldItWas = claimsSteps.authorText();
        String executorClaimFieldItWas = claimsSteps.executorText();
        String dateClaimFieldItWas = claimsSteps.planDateText();
        String timeClaimFieldItWas = claimsSteps.timeText();
        String descriptionClaimFieldItWas = claimsSteps.descriptionClaimField();

        Helper.Swipes.swipeToBottom();
        claimsSteps.clickOnTheNotepadWithPencilButton();
        editingClaimsSteps.clickUndoEditButton();
        editingClaimsSteps.clickButtonToConfirmTheCancellationOfEditing();

        claimsSteps.checkTheOpenStatus();
        String titleClaimFieldItWasHasBecome = claimsSteps.authorText();
        String executorClaimFieldItWasHasBecome = claimsSteps.executorText();
        String dateClaimFieldItWasHasBecome = claimsSteps.planDateText();
        String timeClaimFieldItWasHasBecome = claimsSteps.timeText();
        String descriptionClaimFieldItWasHasBecome = claimsSteps.descriptionClaimField();

        editingClaimsSteps.verificationOfClaimDataBeforeEditingAndAfterCancellationOfEditing(
                titleClaimFieldItWas, titleClaimFieldItWasHasBecome, executorClaimFieldItWas, executorClaimFieldItWasHasBecome,
                dateClaimFieldItWas, dateClaimFieldItWasHasBecome, timeClaimFieldItWas, timeClaimFieldItWasHasBecome,
                descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
    }

    @Test
    @DisplayName("Cancel notification of cancellation of claim editing")
    @Description("Отмена уведомление об отмене редактирования претензии ID 060")
    public void shouldNotBeCanceledEditingClaim() {
        Helper.Swipes.swipeToBottom();
        claimsSteps.clickOnTheNotepadWithPencilButton();
        editingClaimsSteps.checkNameOfTheScreenForEditingClaims();
        editingClaimsSteps.clickUndoEditButton();
        editingClaimsSteps.clickCancelButtonToExitEditingEditing();
        editingClaimsSteps.checkNameOfTheScreenForEditingClaims();
    }

    @Test
    @DisplayName("Warning for empty fields error in claim editing")
    @Description("Предупреждение на ошибку пустых полей в редактировании претензии ID 061")
    public void shouldBeAnErrorOnEmptyFieldsInClaimEditing() {
        Helper.Swipes.swipeToBottom();
        claimsSteps.clickOnTheNotepadWithPencilButton();
        editingClaimsSteps.deletTextFromTheTitleField();
        editingClaimsSteps.clickSaveButton();
        editingClaimsSteps.checkFillEmptyFields(ActivityTestRule.getActivity(), "Fill empty fields");
    }

    @Test
    @DisplayName("When editing an artist and then saving the claim status changes from Open to In progress")
    @Description("При редактировании исполнителя и дальнейшем сохранении статус претензии меняется с Open на In progress ID 062")
    public void shouldBeChangedStatusWhenEditingToInProgress() {
        String executor = "Ivanov Ivan Ivanovich";

        claimsSteps.clickOnTheNotepadWithPencilButton();
        editingClaimsSteps.clickExecutorField();
        editingClaimsSteps.clickRandomlySelectedArtist(ActivityTestRule.getActivity(), executor);
        editingClaimsSteps.clickSaveButton();
        claimsSteps.checkTheInProgressStatus();
    }

    @Test
    @DisplayName("The data of the Executor field is not saved if you enter values that are not included in the list")
    @Description("Не сохраняются данные поля Executor если внести значения не входящие в список ID 063")
    public void shouldNotBeSavedChangesIfTheExecutorIsNotInTheList() {
        String text = textSymbol(7);

        String executorClaimFieldItWas = claimsSteps.executorTextOnCard();

        claimsSteps.clickOnTheNotepadWithPencilButton();


        editingClaimsSteps.fillExecutorField(text);
        String executorClaimFieldInputText = editingClaimsSteps.executorClaim();
        editingClaimsSteps.clickSaveButton();

        String executorClaimFieldItWasHasBecome = claimsSteps.executorText();

        editingClaimsSteps.checkPerformerBeforeEditingAndAfter(
                executorClaimFieldItWas, executorClaimFieldItWasHasBecome, executorClaimFieldInputText);
    }
}
