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
import ru.iteco.fmhandroid.ui.steps.CommentSteps;
import ru.iteco.fmhandroid.ui.steps.FilteringWindowSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class CommentTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    MainSteps mainSteps = new MainSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    ClaimsSteps claimsSteps = new ClaimsSteps();
    CommentSteps commentSteps = new CommentSteps();
    FilteringWindowSteps filteringWindowSteps = new FilteringWindowSteps();

    int position = randomClaims(1);
    int positionComment = 0;
    String message = "The field cannot be empty.";
    String invalidLanguageTextComment = "тест тест";

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
            Helper.Swipes.swipeToBottom();
            claimsSteps.clickOnTheAddCommentButton();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(3000);
    }

    @Test
    @DisplayName("Adding a comment to a claim")
    @Description("Добаволение комментария в претензию ID 084")
    public void shouldBeAddedComment() {
        String validTextComment = textSymbol(7);

        commentSteps.validLanguageTextComment(validTextComment);
        commentSteps.clickOnTheSaveCommentButton();
        Helper.Swipes.swipeToBottom();

        claimsSteps.checkCommentShouldBeAdded(validTextComment);
    }

    @Ignore // Отдельно проходит
    @Test
    @DisplayName("Editing a comment in a claim")
    @Description("Редактирование комментария в претензии ID 087")
    public void shouldBeChangedComment() {
        String validTextComment1 = textSymbol(7);
        String validTextComment2 = textSymbol(7);

        commentSteps.validLanguageTextComment(validTextComment1);
        commentSteps.clickOnTheSaveCommentButton();

        claimsSteps.clickOnTheButtonToEnterTheCommentEditing(positionComment);
        commentSteps.validLanguageTextComment(validTextComment2);
        commentSteps.clickOnTheSaveCommentButton();
        claimsSteps.checkTheCommentBeforeEditingAndAfter(validTextComment2);
    }

    @Test
    @DisplayName("Cancel adding a comment to a claim")
    @Description("Отмена добавления комментария в претензию ID 085")
    public void shouldBeCanceledComment() {
        String validTextComment = textSymbol(7);

        commentSteps.validLanguageTextComment(validTextComment);
        commentSteps.clickOnTheButtonToCancelAddingComment();
        claimsSteps.checkTheCommentShouldNotBeCreated(validTextComment);
    }

    @Test
    @DisplayName("Error notification when trying to save an empty comment")
    @Description("Уведомление об ошибке при попытке сохранить пустой комментарий ID 086")
    public void shouldNotBeSavedAnEmptyComment() {
        commentSteps.clickOnTheSaveCommentButton();
        commentSteps.checkTheFieldCannotBeEmpty(ActivityTestRule.getActivity(), message);
    }

    @Test
    @DisplayName("Adding multiple comments to one claim")
    @Description("Добавление нескольких комментариев в одну претензию ID 088")
    public void shouldBeAddedTwoComments() {
        String validTextComment1 = textSymbol(7);
        String validTextComment2 = textSymbol(7);

        commentSteps.validLanguageTextComment(validTextComment1);
        commentSteps.clickOnTheSaveCommentButton();
        Helper.Swipes.swipeToBottom();
        claimsSteps.checkTheAddedComment(validTextComment1);
        claimsSteps.clickOnTheAddCommentButton();
        commentSteps.validLanguageTextComment(validTextComment2);
        commentSteps.clickOnTheSaveCommentButton();
        Helper.Swipes.swipeToBottom();
        claimsSteps.checkTheAddedComment(validTextComment2);
    }

    @Test
    @DisplayName("When you click + in a claim, a window for adding a comment opens.")
    @Description("При клике + в претензии открывается окно добавления комментария ID 120")
    public void shouldBeOpenPageForAddingComment() {
        commentSteps.checkingTheEntryToTheCommentScreen();
    }

    @Test
    @DisplayName("The comment field is filled in Latin letters")
    @Description("Поле комментарий заполняется латинскими буквами ID 118")
    public void shouldBeFilledInLatinLetters() {
        String validTextComment = textSymbol(7);

        commentSteps.validLanguageTextComment(validTextComment);
        commentSteps.checkTheFieldIsFilledWithText(validTextComment);
    }

    @Test
    @DisplayName("The comment field is not filled with Cyrillic letters")
    @Description("Поле комментарий не заполняется кириллическими буквами ID 119")
    public void shouldNotBeFilledInCyrillicLetters() {

        try {
            commentSteps.enteringAnIncorrectLanguageTextComment(invalidLanguageTextComment);
        } catch (RuntimeException e) {

        } finally {
            commentSteps.checkTheFieldIsNotFilledWithText();
        }
    }
}
