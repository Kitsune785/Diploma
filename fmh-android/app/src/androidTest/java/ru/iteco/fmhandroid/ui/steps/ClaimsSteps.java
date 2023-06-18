package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.Search.searchForAnUncreatedClaim;
import static ru.iteco.fmhandroid.ui.data.Helper.Search.searchForAnUncreatedComment;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import org.junit.Assert;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.elements.ClaimsElements;

public class ClaimsSteps {

    ClaimsElements claimsElements = new ClaimsElements();

    public void clickOnTheButtonToGoToTheClaimCreation() {
        Allure.step("Нажатие на кнопку перехода на экран создания претензии");
        claimsElements.getCreateClaimsButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheButtonToGoToTheFiltering() {
        Allure.step("Нажатие на кнопку перехода на экран Filtering");
        claimsElements.getFilteringButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheNotepadWithPencilButton() {
        Allure.step("Нажатие на кнопку блокнот с карандашом для перехода к экрану для редактирования");
        claimsElements.getEditClaimsButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheButtonWithTheNotepadIconWithGear() {
        Allure.step("Нажатие на кнопку с иконкой блокнот с шестеренкой");
        claimsElements.getButtonChangeStatus().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTakeToWork() {
        Allure.step("Нажатие на взять в работу");
        claimsElements.getTakeToWork().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnReset() {
        Allure.step("Нажатие на Сбросить");
        claimsElements.getThrowOff().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheOkButtonToAddComment() {
        Allure.step("Нажатие на кнопку ок добавления комментария");
        claimsElements.getOkButtonAddComment().perform(scrollTo(), click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheAddCommentButton() {
        Allure.step("Нажатие на кнопку добавления комментария");
        claimsElements.getAddComment().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnTheButtonToEnterTheCommentEditing(int position) {
        Allure.step("Нажатие на кнопку для входа в экран редактирования комментария");
        claimsElements.setEditClaimsButton(position);
        claimsElements.getEditCommentButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnToExecute() {
        Allure.step("Нажатие на для выполнения");
        claimsElements.getToExecute().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnToCancel() {
        Allure.step("Нажатие на для отмены");
        claimsElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void clickOnRandomlySelectedClaim(int position) {
        Allure.step("Нажатие на случайно выбранную претензию");
        claimsElements.getBlockClaims().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    public void checkNameClaims() {
        Allure.step("Названия экрана Claims");
        claimsElements.getScreenNameClaims().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void verificationOfIdentifyingNamesInTheClaim() {
        Allure.step("Идентификация названий в претензии");
        claimsElements.getTitle().check(matches(isDisplayed()));
        claimsElements.getExecutorLabel().check(matches(isDisplayed()));
        claimsElements.getPlanDateLabel().check(matches(isDisplayed()));
        claimsElements.getStatus().check(matches(isDisplayed()));
        claimsElements.getDescriptionText().check(matches(isDisplayed()));
        claimsElements.getAuthorLabel().check(matches(isDisplayed()));
        claimsElements.getCreatedLabel().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkClaim() {
        Allure.step("Названия в претензии");
        claimsElements.getExecutorText().check(matches(isDisplayed()));
        claimsElements.getPlanDateText().check(matches(isDisplayed()));
        claimsElements.getTimeText().check(matches(isDisplayed()));
        claimsElements.getAuthorText().check(matches(isDisplayed()));
        claimsElements.getDescriptionText().check(matches(isDisplayed()));
        claimsElements.getStatus().check(matches(isDisplayed()));
        claimsElements.getTitle().check(matches(isDisplayed()));
        claimsElements.getExecutorLabel().check(matches(isDisplayed()));
        claimsElements.getPlanDateLabel().check(matches(isDisplayed()));
        claimsElements.getAuthorLabel().check(matches(isDisplayed()));
        claimsElements.getCreatedLabel().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void fillInTheCommentField(String text) {
        Allure.step("Заполнение поля комментария");
        claimsElements.getCommentField().perform(typeText(text)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    public void clickOnTheButtonToCancelAddingComment() {
        Allure.step("Нажатие на кнопку отмены добавления комментария");
        claimsElements.getCancelAddCommentButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void checkTheAbsenceOfComment(String commentText) {
        Allure.step("Проверка отсутствия комментария");
        ViewInteraction commentTextFromField = onView(allOf(withId(R.id.comment_description_text_view), withParent(withParent(allOf(withId(R.id.claim_comments_list_recycler_view), withChild(withChild(allOf(withText(commentText)))))))));
        claimsElements.getCommentStatus().check(matches(not(withText(commentTextFromField.toString()))));
        SystemClock.sleep(3000);
    }

    public void checkTheVisibilityOfTheAddedComment() {
        Allure.step("Проверка видимости добавленного комментария");
        claimsElements.getCommentStatus().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkCommentShouldBeAdded(String validTextComment) {
        Allure.step("Проверка должен добавиться комментарий");
        onView(allOf(withId(R.id.comment_description_text_view), withText(validTextComment.trim()),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(validTextComment.trim()))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheCommentBeforeEditingAndAfter(String validTextComment) {
        Allure.step("Проверка комментария до редактирования и после");
        onView(allOf(withId(R.id.comment_description_text_view), withText(validTextComment),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(validTextComment))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheAddedComment(String validTextComment) {
        Allure.step("Наличие добавленного комментария");
        onView(allOf(withId(R.id.comment_description_text_view), withText(validTextComment.trim()),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(validTextComment.trim()))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheCommentShouldNotBeCreated(String validTextComment) {
        Allure.step("Коментарий не должен создаться");
        assertNotEquals(validTextComment , searchForAnUncreatedComment(validTextComment.trim()));
        SystemClock.sleep(3000);
    }

    public void checkTheOpenStatus() {
        Allure.step("Проверка статуса Open");
        claimsElements.getStatusOpen().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheInProgressStatus() {
        Allure.step("Проверка статуса In Progress");
        claimsElements.getStatusInProgress().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheExecutedStatus() {
        Allure.step("Проверка статуса Executed");
        claimsElements.getStatusExecuted().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheCanceledStatus() {
        Allure.step("Проверка статуса Canceled");
        claimsElements.getStatusCancelled().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkForTheAbsenceOfAnUncreatedClaim(String titleText) {
        Allure.step("Проверка отсутствия в блоке претензий отмененной при создании претензии");
        assertNotEquals(titleText, searchForAnUncreatedClaim(titleText));
        SystemClock.sleep(3000);
    }

    public void comparisonOfDataBeforeAndAfterEditing(
            String executorTextItWas, String executorTextItWasHasBecome, String planDateTextItWas, String planDateTextItWasHasBecome,
            String timeTextItWas, String timeTextItWasHasBecome, String authorTextItWas, String authorTextItWasHasBecome) {
        Allure.step("Сравнение данных до редактирования и после отмены редактирования");
        Assert.assertEquals(executorTextItWas, executorTextItWasHasBecome);
        Assert.assertEquals(planDateTextItWas, planDateTextItWasHasBecome);
        Assert.assertEquals(timeTextItWas, timeTextItWasHasBecome);
        Assert.assertEquals(authorTextItWas, authorTextItWasHasBecome);
        SystemClock.sleep(3000);
    }

    public String commentText() {
        return Helper.Text.getText(claimsElements.getCommentField());
    }

    public String executorText() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.executor_name_text_view), 0)));
    }

    public String executorTextOnCard() {
        return Helper.Text.getText(onView(withId(R.id.executor_name_text_view)));
    }

    public String planDateText() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plane_date_text_view), 0)));
    }

    public String timeText() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plan_time_text_view), 0)));
    }

    public String authorText() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.author_name_text_view), 0)));
    }

    public String descriptionClaimField() {
        return Helper.Text.getText(onView(withId(R.id.description_text_view)));
    }

//    public void checkingTheStatusShouldNotBeExecuted() {
//        //  Allure.step("Проверка статус не должен быть Executed");
//        claimsElements.getStatus().check(matches(not(withText("Executed"))));
//        SystemClock.sleep(3000);
//    }

//    public void pressingTheExitButton() {
//        // Allure.step("Нажатие на кнопку выхода");
//        claimsElements.getExitButton().perform(click());
//        SystemClock.sleep(3000);
//    }
//
//    public void clickingOnTheCloseButton() {
//        //   Allure.step("Нажатие на кнопку закрыть");
//        claimsElements.getCloseImageButton().perform(click());
//        SystemClock.sleep(3000);
//    }

//    public void checkingTheStatusShouldNotBeCancelled() {
//        //  Allure.step("Проверка статус не должен быть Cancelled");
//        claimsElements.getStatus().check(matches(not(withText("Cancelled"))));
//        SystemClock.sleep(3000);
//    }
}
