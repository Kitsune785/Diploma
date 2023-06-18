package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Helper.Rand.random;
import static ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;
import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;
import static ru.iteco.fmhandroid.ui.data.Helper.createClaim;

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
import ru.iteco.fmhandroid.ui.steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.EditingClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.FilteringWindowSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ClaimsTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    MainSteps mainSteps = new MainSteps();
    ClaimsSteps claimsSteps = new ClaimsSteps();
    CreatingClaimsSteps creatingClaimsSteps = new CreatingClaimsSteps();
    FilteringWindowSteps filteringWindowSteps = new FilteringWindowSteps();
    EditingClaimsSteps editingClaimsSteps = new EditingClaimsSteps();

    int position = random(0);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainSteps.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationSteps.validLoginPassword(authInfo());
        } finally {
            mainSteps.clickAllClaims();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("Claim page title should be Claims")
    @Description("Название страницы с претензиями должно быть Claims ID 029")
    public void shouldBePageNameClaims() {
        claimsSteps.checkNameClaims();
    }

    @Test
    @DisplayName("Switching to the claims filter via the three circles and stripes button")
    @Description("Переход на странице на фильтр претензий через кнопку три кружка и полоски ID 030")
    public void shouldBeFilterOpen() {
        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        filteringWindowSteps.checkScreenNameFiltering();
    }

    @Test
    @DisplayName("Switching on the claims page to creating claims via the + button")
    @Description("Переход на странице претензий к созданию претензий через кнопку + ID 031")
    public void shouldBeOpenClaimCreationPage() {
        claimsSteps.clickOnTheButtonToGoToTheClaimCreation();
        creatingClaimsSteps.checkNameOfTheClaimCreation();
    }

    @Test
    @DisplayName("Go to the page with claims to the selected claim")
    @Description("Переход на странице с претензиями к выбранной претензии ID 032")
    public void shouldBeOpenedThePageWithTheSelectedClaim() {
        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkClaim();
    }

    @Test
    @DisplayName("Change of claim status from Open to In progress")
    @Description("Изменение статуса претензии с Open на In progress ID 033")
    public void shouldBeChangedClaimStatusToInProgress() {
        createClaim();

        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkTheOpenStatus();
        Helper.Swipes.swipeToBottom();

        claimsSteps.clickOnTheButtonWithTheNotepadIconWithGear();
        claimsSteps.clickOnTakeToWork();
        Helper.Swipes.swipeToTop();
        claimsSteps.checkTheInProgressStatus();
    }

    @Test
    @DisplayName("Go to the claim editing page")
    @Description("Переход на страницу с редактированием претензии ID 034")
    public void shouldBeOpenedThePageWithEditingTheClaim() {
        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.clickOnTheNotepadWithPencilButton();
        editingClaimsSteps.checkNameOfTheScreenForEditingClaims();
    }

    @Test
    @DisplayName("Editing all fields in the claim and saving changes")
    @Description("Редактирование всех полей в претензии и сохранения изменений ID 035")
    public void shouldBeClaimEdited() {
        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkTheOpenStatus();

        String titleClaimFieldItWas = claimsSteps.authorText();
        String executorClaimFieldItWas = claimsSteps.executorText();
        String dateClaimFieldItWas = claimsSteps.planDateText();
        String timeClaimFieldItWas = claimsSteps.timeText();
        String descriptionClaimFieldItWas = claimsSteps.descriptionClaimField();

        Helper.Swipes.swipeToBottom();
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
    @DisplayName("You can undo editing a claim when making changes to all fields")
    @Description("Можно отменить редактирования претензии при внесении изменений во все поля ID 036")
    public void shouldBeCanceledClaimEditing() {
        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkTheOpenStatus();

        String executorTextItWas = claimsSteps.executorText();
        String planDateTextItWas = claimsSteps.planDateText();
        String timeTextItWas = claimsSteps.timeText();
        String authorTextItWas = claimsSteps.authorText();

        Helper.Swipes.swipeToBottom();
        claimsSteps.clickOnTheNotepadWithPencilButton();
        editingClaimsSteps.checkNameOfTheScreenForEditingClaims();
        editingClaimsSteps.fillFieldsWithValidData();
        editingClaimsSteps.clickUndoEditButton();
        editingClaimsSteps.clickButtonToConfirmTheCancellationOfEditing();

        Helper.Swipes.swipeToTop();
        claimsSteps.checkTheOpenStatus();
        String executorTextItWasHasBecome = claimsSteps.executorText();
        String planDateTextItWasHasBecome = claimsSteps.planDateText();
        String timeTextItWasHasBecome = claimsSteps.timeText();
        String authorTextItWasHasBecome = claimsSteps.authorText();

        claimsSteps.comparisonOfDataBeforeAndAfterEditing(
                executorTextItWas, executorTextItWasHasBecome, planDateTextItWas, planDateTextItWasHasBecome,
                timeTextItWas, timeTextItWasHasBecome, authorTextItWas, authorTextItWasHasBecome);
    }

    @Test
    @DisplayName("Cancellation of making a comment and changing the status of the claim through the status Throw off")
    @Description("Отмена внесения комментария и смены статуса претензии через статус Throw off ID 037")
    public void shouldNotBeChangedStatusAndTheCommentSaved() {
        String text = textSymbol(7);

        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkTheOpenStatus();

        Helper.Swipes.swipeToBottom();
        claimsSteps.clickOnTheButtonWithTheNotepadIconWithGear();
        claimsSteps.clickOnTakeToWork();
        Helper.Swipes.swipeToTop();
        claimsSteps.checkTheInProgressStatus();
        claimsSteps.clickOnTheButtonWithTheNotepadIconWithGear();
        claimsSteps.clickOnReset();
        claimsSteps.fillInTheCommentField(text);
        String commentText = claimsSteps.commentText();
        claimsSteps.clickOnTheButtonToCancelAddingComment();

        claimsSteps.checkTheAbsenceOfComment(commentText);
        Helper.Swipes.swipeToTop();
        claimsSteps.checkTheInProgressStatus();
    }

    @Test
    @DisplayName("Changing the status of a claim from In progress to Open while saving the comment")
    @Description("Изменение статуса претензии с In progress на Open с сохранением комментария ID 038")
    public void shouldBeChangedStatusToOpen() {
        String text = textSymbol(7);

        createClaim();

        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkTheOpenStatus();

        claimsSteps.clickOnTheButtonWithTheNotepadIconWithGear();
        claimsSteps.clickOnTakeToWork();
        claimsSteps.checkTheInProgressStatus();
        claimsSteps.clickOnTheButtonWithTheNotepadIconWithGear();
        claimsSteps.clickOnReset();
        claimsSteps.fillInTheCommentField(text);
        claimsSteps.clickOnTheOkButtonToAddComment();
        claimsSteps.checkTheVisibilityOfTheAddedComment();
        claimsSteps.checkTheOpenStatus();
    }

    @Test
    @DisplayName("Change the status of the claim from In progress to Executed with the comment saved")
    @Description("Изменение статуса претензии с In progress на Executed с сохранением комментария ID 039")
    public void shouldBeChangedStatusToExecuted() {
        String text = textSymbol(7);

        createClaim();

        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickButtonOk();

        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkTheOpenStatus();
        Helper.Swipes.swipeToBottom();

        claimsSteps.clickOnTheButtonWithTheNotepadIconWithGear();
        claimsSteps.clickOnTakeToWork();
        Helper.Swipes.swipeToTop();
        claimsSteps.checkTheInProgressStatus();
        Helper.Swipes.swipeToBottom();
        claimsSteps.clickOnTheButtonWithTheNotepadIconWithGear();
        claimsSteps.clickOnToExecute();
        claimsSteps.fillInTheCommentField(text);
        claimsSteps.clickOnTheOkButtonToAddComment();
        claimsSteps.checkTheVisibilityOfTheAddedComment();
        Helper.Swipes.swipeToTop();
        claimsSteps.checkTheExecutedStatus();
    }

    @Test
    @DisplayName("Claim closing, status change to Canceled")
    @Description("Закрытие претензии, изменение статуса на Canceled ID 040")
    public void shouldBeChangedStatusToCanceled() {
        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickButtonOk();
        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkTheOpenStatus();
        Helper.Swipes.swipeToBottom();

        claimsSteps.clickOnTheButtonWithTheNotepadIconWithGear();
        claimsSteps.clickOnToCancel();
        Helper.Swipes.swipeToTop();
        claimsSteps.checkTheCanceledStatus();
    }

    @Test
    @DisplayName("Status change from In progress to Open")
    @Description("Изменение статуса с In progress на Open ID 117")
    public void shouldBeStatusChangeFromInProgressToOpen() {
        String text = textSymbol(7);

        claimsSteps.clickOnTheButtonToGoToTheFiltering();
        filteringWindowSteps.clickCheckBoxInProgress();
        filteringWindowSteps.clickButtonOk();

        claimsSteps.clickOnRandomlySelectedClaim(position);
        claimsSteps.checkTheOpenStatus();
        Helper.Swipes.swipeToBottom();

        claimsSteps.clickOnTheButtonWithTheNotepadIconWithGear();
        claimsSteps.clickOnTakeToWork();

        claimsSteps.checkTheInProgressStatus();
        Helper.Swipes.swipeToBottom();
        claimsSteps.clickOnTheButtonWithTheNotepadIconWithGear();
        claimsSteps.clickOnReset();
        claimsSteps.fillInTheCommentField(text);
        claimsSteps.clickOnTheOkButtonToAddComment();
        claimsSteps.checkTheVisibilityOfTheAddedComment();
        Helper.Swipes.swipeToTop();
        claimsSteps.checkTheOpenStatus();
    }
}
