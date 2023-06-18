package ru.iteco.fmhandroid.ui.tests;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.ApplicationLoadingSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ApplicationLoadingTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    ApplicationLoadingSteps applicationLoadingSteps = new ApplicationLoadingSteps();

    @After
    public void setUp() {
        SystemClock.sleep(3000);
    }

    @Test
    @DisplayName("Loading indicator appears while app is loading")
    @Description("Во время загрузки приложения появляется индекатор загрузки ID 001")
    public void shouldBeDisplayedLoadingIndicator() throws InterruptedException {
        try {
            applicationLoadingSteps.checkProgressIndicator();
        } catch (NoMatchingViewException ignore) {
            SystemClock.sleep(1000);
        } finally {
            applicationLoadingSteps.checkProgressIndicator();
        }
    }

    @Test
    @DisplayName("App loading time picture appears")
    @Description("Время загрузки приложения появляется картинка ID 002")
    public void shouldBeDisplayedImageOnUpload() {
        applicationLoadingSteps.checkSplashscreenImageView();
    }

    @Test
    @DisplayName("A quote appears while the app is loading")
    @Description("Во время загрузки приложения появляется  цитата ID 003")
    public void shouldBeQuoteDisplayed() {
        applicationLoadingSteps.checkSplashscreenTextView();
    }
}
