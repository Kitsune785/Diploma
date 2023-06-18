package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.ui.elements.CommentElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.AppActivity;

public class CommentSteps {

    CommentElements commentElements = new CommentElements();

    public void validLanguageTextComment(String textComment) {
        Allure.step("Ввод верного текстового комментария");
        commentElements.getCommentFieldName().perform(replaceText(textComment));
        SystemClock.sleep(3000);
    }

    public void clickOnTheSaveCommentButton() {
        Allure.step("Нажатие на кнопку сохранения комментария");
        commentElements.getSaveButton().perform(click());
    }

    public void clickOnTheButtonToCancelAddingComment() {
        Allure.step("Нажатие на кнопку отмены добавления комментария");
        commentElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }

    public void checkTheFieldCannotBeEmpty(@NonNull AppActivity activity, String text) {
        Allure.step("Сообщение об ошибке The field cannot be empty.");
        onView(withText(text)).inRoot(withDecorView(Matchers.not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }





    public void enteringAnIncorrectLanguageTextComment(String textComment) {
       // Allure.step("Ввод неверного языкового текстового комментария");
        commentElements.getCommentFieldName().perform(typeText(textComment));
        SystemClock.sleep(3000);
    }

    public void checkingTheEntryToTheCommentScreen() {
       // Allure.step("Проверка входа в экран комментария");
        commentElements.getSaveButton().check(matches(isDisplayed()));
        commentElements.getCancelButton().check(matches(isDisplayed()));
        commentElements.getCommentFieldName().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheFieldIsNotFilledWithText() {
      //  Allure.step("Проверка поле не заполнено текстом");
        commentElements.getCommentFieldName().check(matches(withText(""))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkTheFieldIsFilledWithText(String textComment) {
       // Allure.step("Проверка поле заполнено текстом");
        commentElements.getCommentFieldName().check(matches(withText(textComment))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
}
