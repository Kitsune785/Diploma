package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.steps.MainSteps.enterCreateClaimsActionButton;
import static ru.iteco.fmhandroid.ui.steps.MainSteps.enterCreateClaimsAllClaims;
import static ru.iteco.fmhandroid.ui.steps.MainSteps.enterCreateClaimsButtonPlus;
import static ru.iteco.fmhandroid.ui.steps.MainSteps.enterCreateNewsOne;
import static ru.iteco.fmhandroid.ui.steps.MainSteps.enterCreateNewsTwo;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.espresso.contrib.PickerActions;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import io.bloco.faker.Faker;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.CalendarElements;
import ru.iteco.fmhandroid.ui.elements.ClaimsElements;
import ru.iteco.fmhandroid.ui.elements.ControlPanelElements;
import ru.iteco.fmhandroid.ui.elements.CreatingClaimsElements;
import ru.iteco.fmhandroid.ui.elements.CreatingNewsElements;
import ru.iteco.fmhandroid.ui.elements.EditingNewsElements;
import ru.iteco.fmhandroid.ui.elements.FilteringWindowElements;
import ru.iteco.fmhandroid.ui.elements.MainElements;
import ru.iteco.fmhandroid.ui.elements.WatchElements;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

public class Helper {

    private Helper() {
    }

    public static class AuthInfo {
        private final String login;
        private final String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static AuthInfo authInfo() {
        String login = "login2";
        String password = "password2";
        return new AuthInfo(login, password);
    }

    public static AuthInfo invalidAuthInfo() {
        Faker faker = new Faker();
        Random random = new Random();
        String[] invalidLogin = {"", "", " ", faker.name.firstName() + "@" + faker.name.lastName()};
        String[] emptyString = {"", " "};
        String[] invalidPassword = {" ", ""};
        String randomEmptyString = emptyString[random.nextInt(emptyString.length)];
        String randomPassword = invalidPassword[random.nextInt(invalidPassword.length)];
        String randomLogin = invalidLogin[random.nextInt(invalidLogin.length)];
        String login = (randomLogin.equals("login1") ? randomEmptyString : invalidLogin[0]);
        String password = (randomPassword.equals("") ? randomEmptyString : invalidPassword[0]);
        return new AuthInfo(login, password);
    }

    public static AuthInfo invalidLoginPasswordAuthInfo() {
        Faker faker = new Faker();
        Random random = new Random();
        String[] login = {"login", "login2", "///////", "./..", faker.name.firstName(), faker.name.lastName()};
        String[] password = {"pass", "password", "@@@@@@", "121587"};
        String invalidLogin = login[random.nextInt(login.length)];
        String invalidPassword = password[random.nextInt(password.length)];
        return new AuthInfo(invalidLogin, invalidPassword);
    }

    public static void clickNextMonth(int quantity) {
        CalendarElements calendarElements = new CalendarElements();
        for (int i = 0; i < quantity; i++) {
            calendarElements.getNextMonthButton().perform(scrollTo(), click());
        }
    }

    public static void swipeNextMonth(int quantity) {
        CalendarElements calendarElements = new CalendarElements();
        for (int i = 0; i < quantity; i++) {
            calendarElements.getCalendarMonthView().perform(swipeRight());

        }
    }

    public static void swipePreviousMonth(int quantity) {
        CalendarElements calendarElements = new CalendarElements();
        for (int i = 0; i < quantity; i++) {
            calendarElements.getCalendarMonthView().perform(swipeLeft());
        }
    }

    public static void clickPreviousMonth(int quantity) {
        CalendarElements calendarElements = new CalendarElements();
        for (int i = 0; i < quantity; i++) {
            calendarElements.getPreviousMonthButton().perform(scrollTo(), click());
        }
    }

    public static void createClaims(int quantity) {
        for (int i = 0; i < quantity; i++) {
            createClaim();
        }
    }

    public static void createClaim() {
        WatchElements watchElements = new WatchElements();
        CalendarElements calendarElements = new CalendarElements();
        CreatingClaimsElements creatingClaimsElements = new CreatingClaimsElements();
        MainElements mainElements = new MainElements();

        SystemClock.sleep(2000);
        mainElements.getCreateClaimsButton().perform(click());
        String titleText = Text.textSymbol(5);

        creatingClaimsElements.getTitleClaimField().perform(replaceText(titleText), closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingClaimsElements.getDateClaimField().perform(click());
        watchElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(2000);
        creatingClaimsElements.getTimeClaimField().perform(click());
        calendarElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(2000);
        creatingClaimsElements.getDescriptionClaimField().perform(replaceText(Text.textSymbol(10)), closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingClaimsElements.getSaveButton().perform(click());
        SystemClock.sleep(2000);
    }

    public static void createNewsForCategory(String text, String category) {
        ControlPanelElements controlPanelElements = new ControlPanelElements();
        CreatingNewsElements creatingNewsElements = new CreatingNewsElements();
        WatchElements watchElements = new WatchElements();
        CalendarElements calendarElements = new CalendarElements();

        controlPanelElements.getCreateNewsButton().perform(click());

        creatingNewsElements.getCategoryFieldNews().perform(replaceText(category)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsElements.getTitleFieldNews().perform(replaceText(category)).perform(closeSoftKeyboard());
        creatingNewsElements.getPublicationDateFieldNews().perform(click());
        watchElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        creatingNewsElements.getTimeFieldNews().perform(click());
        calendarElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        creatingNewsElements.getDescriptionFieldNews().perform(replaceText(text)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsElements.getSaveButton().perform(click());
        SystemClock.sleep(2000);
    }

    public static void createNews(String text, String category) {
        ControlPanelElements controlPanelElements = new ControlPanelElements();
        CreatingNewsElements creatingNewsElements = new CreatingNewsElements();
        WatchElements watchElements = new WatchElements();
        CalendarElements calendarElements = new CalendarElements();

        controlPanelElements.getCreateNewsButton().perform(click());

        creatingNewsElements.getCategoryFieldNews().perform(replaceText(category)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsElements.getTitleFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
        creatingNewsElements.getPublicationDateFieldNews().perform(click());
        watchElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        creatingNewsElements.getTimeFieldNews().perform(click());
        calendarElements.getOkButton().perform(scrollTo(), click());
        creatingNewsElements.getDescriptionFieldNews().perform(replaceText(text)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsElements.getSaveButton().perform(click());
        SystemClock.sleep(2000);
    }

    public static void deletNewsUpToTheNumberOfTenControlPanel(int position) {
        MainSteps mainSteps = new MainSteps();
        NewsSteps newsSteps = new NewsSteps();
        ControlPanelSteps controlPanelSteps = new ControlPanelSteps();

        mainSteps.clickMenuButton();
        mainSteps.clickNewsName();
        newsSteps.clickButtonToGoToTheControlPanel();

        int positionNews = position;

        while (true) {
            try {
                controlPanelSteps.clickOnRandomlySelectedNewsItem(positionNews);
                String description = controlPanelSteps.descriptionNews();
                controlPanelSteps.clickOnTheDeleteNewsButtonPosition(description);
                SystemClock.sleep(2000);
                controlPanelSteps.clickOnTheConfirmationButtonToDeleteTheNews();
                SystemClock.sleep(2000);
            } catch (RuntimeException exception) {
                break;
            }
            positionNews += 1;
        }
    }

    public static class Rand {

        private static final Random rand = new Random();

        @SafeVarargs
        public static int randomClaims(@NonNull int... items) {
            return items[rand.nextInt(items.length)];
        }

        @SafeVarargs
        public static int randomNews(@NonNull int... items) {
            return items[rand.nextInt(items.length)];
        }

        @SafeVarargs
        public static int random(@NonNull int... items) {
            return items[rand.nextInt(items.length)];
        }

        public static String randomExecutor() {
            String[] executor = {
                    "Ivanov Ivan Ivanovich",
                    "Иванов Иван Иванович",
                    "Семенов Семен Семенович",
                    "Антонов Антон Антонович",
                    "Петров Петр Петрович",
                    "Федоров Иван Петрович",
                    "Ivanov Ivan Sidorovich",
            };
            return executor[rand.nextInt(executor.length)];
        }

        public static String randomCategory() {
            String[] category = {
                    "Объявление",
                    "День рождения",
                    "Зарплата",
                    "Профсоюз",
                    "Праздник",
                    "Массаж",
                    "Благодарность",
                    "Нужна помощь"
            };
            return category[rand.nextInt(category.length)];
        }

        public static int randomDay() {
            int day;
            int localDateDay = LocalDate.now().getDayOfMonth();
            int min = 1;
            int max = 30;
            max -= min;
            int randomDay = (int) ((Math.random() * ++max) + min);
            if (randomDay < localDateDay || randomDay + localDateDay > 28) {
                day = 0;
            } else {
                day = randomDay;
            }
            return day;
        }

        public static int randomMonth() {
            int min = 1;
            int max = 12;
            max -= min;
            return (int) ((Math.random() * ++max) + min);
        }

        public static void randomCheckBox() {
            FilteringWindowElements filteringWindowElements = new FilteringWindowElements();

            int min = 0;
            int max = 3;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);

            if (random == 0) {
                filteringWindowElements.getCheckBoxOpen().perform(click());
            } else if (random == 1) {
                filteringWindowElements.getCheckBoxInProgress().perform(click());
            } else if (random == 2) {
                filteringWindowElements.getCheckBoxExecuted().perform(click());
            } else {
                filteringWindowElements.getCheckBoxCancelled().perform(click());
            }
            SystemClock.sleep(2000);
        }

        public static void randomLogInToClaimsCreation() {
            int min = 1;
            int max = 3;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);

            if (random == 1) {
                enterCreateClaimsAllClaims();
            } else if (random == 2) {
                enterCreateClaimsActionButton();
            } else {
                enterCreateClaimsButtonPlus();
            }
            SystemClock.sleep(2000);
        }

        public static void randomLogInToNewsCreation() {
            int min = 1;
            int max = 2;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);

            if (random == 1) {
                enterCreateNewsOne();
            } else {
                enterCreateNewsTwo();
            }
            SystemClock.sleep(2000);
        }
    }

    public static class Swipes {

        static void swiper(int start, int end, int delay) {
            long downTime = SystemClock.uptimeMillis();
            long eventTime = SystemClock.uptimeMillis();
            Instrumentation inst = getInstrumentation();

            MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 500, start, 0);
            inst.sendPointerSync(event);
            eventTime = SystemClock.uptimeMillis() + delay;
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 500, end, 0);
            inst.sendPointerSync(event);
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 500, end, 0);
            inst.sendPointerSync(event);
            SystemClock.sleep(2000);
        }

        public static void swipeToBottom(){
            swiper(1000, 100, 0);
        }


        public static void scrollSlowlyDown(){
            swiper(775, 100, 100);
        }

        public static void swipeToTop(){
            swiper(100, 1000, 0);
        }

        public static void scrollSlowlyUp(){
            swiper(100, 775, 100);
        }
    }

    public static ViewAction swipeUpSlow() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.BOTTOM_CENTER,
                GeneralLocation.TOP_CENTER, Press.FINGER);
    }

    public static void setUpStatusNewsNotActive(int position) {
        ControlPanelElements controlPanelElements = new ControlPanelElements();
        EditingNewsElements editingNewsElements = new EditingNewsElements();

        controlPanelElements.getRecyclerView().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
        String nameNewsItWas = Text.getText(controlPanelElements.getNewsItemTitle());
        String statusBefore = Text.getText(onView(allOf(withId(R.id.news_item_published_text_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))));
        onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))).perform(click());
        SystemClock.sleep(3000);
        if (statusBefore.toUpperCase().trim().equals("ACTIVE")) {
            editingNewsElements.getCheckBox().perform(click());
            editingNewsElements.getSaveButton().perform(click());
        } else {
            editingNewsElements.getCancelButton().perform(click());
            editingNewsElements.getOkButton().perform(scrollTo(), click());
        }
        SystemClock.sleep(2000);
    }

    public static void checkingStatus(String statusClaims) {
        ClaimsElements claimsElements = new ClaimsElements();
        String[] status = {"Open", "In progress", "Executed", "Cancelled"};

        if (statusClaims.equals("Open")) {
            claimsElements.getStatus().check(matches(withText(status[0])));
        } else {
            claimsElements.getStatus().check(matches(not(withText(status[0]))));
        }
        if (statusClaims.equals("In progress")) {
            claimsElements.getStatus().check(matches(withText(status[1])));
        } else {
            claimsElements.getStatus().check(matches(not(withText(status[1]))));
        }
        if (statusClaims.equals("Executed")) {
            claimsElements.getStatus().check(matches(withText(status[2])));
        } else {
            claimsElements.getStatus().check(matches(not(withText(status[2]))));
        }
        if (statusClaims.equals("Cancelled")) {
            claimsElements.getStatus().check(matches(withText(status[3])));
        } else {
            claimsElements.getStatus().check(matches(not(withText(status[3]))));
        }
        SystemClock.sleep(2000);
    }

    public static class Search {

        public static int searchNews(String text) {
            ControlPanelSteps controlPanelSteps = new ControlPanelSteps();
            ControlPanelElements controlPanelElements = new ControlPanelElements();
            int position = 0;
            boolean notFound = true;
            String description;

            while (notFound) {
                try {
                    controlPanelSteps.clickOnRandomlySelectedNewsItem(position);
                    SystemClock.sleep(2000);
                    description = controlPanelSteps.descriptionNewsPosition(position);
                    if (text.trim().equals(description.trim())) {
                        notFound = false;
                    } else {
                        notFound = true;
                        position += 1;
                        SystemClock.sleep(2000);
                    }
                } catch (PerformException e) {
                    break;
                }
            }
            return position;
        }

        public static int searchComment(String text) {
            ClaimsElements claimsElements = new ClaimsElements();
            int position = 0;
            boolean notFound = true;

            while (notFound) {
                try {
                    onView(withText(text)).check(matches(isDisplayed()));
                    notFound = false;
                } catch (PerformException e) {
                    break;
                }
                claimsElements.getBlockComment().check(matches(isDisplayed())).perform(actionOnItemAtPosition(position, swipeDown()));
                position += 1;
            }
            return position;
        }

        public static String searchForAnUncreatedComment(String text) {
            ClaimsElements claimsElements = new ClaimsElements();

            boolean notFound = true;
            int position = 0;
            String commentTextString = Text.getText(onView(allOf(withId(R.id.comment_description_text_view),
                    withParent(withParent(withIndex(withId(R.id.claim_comments_list_recycler_view), position))))));
            while (notFound) {
                try {
                    onView(withText(text)).check(matches(isDisplayed()));
                    notFound = false;
                } catch (NoMatchingViewException ignore) {
                }
                try {
                    claimsElements.getBlockComment().check(matches(isDisplayed())).perform(actionOnItemAtPosition(position, swipeUp()));
                    position += 1;
                } catch (PerformException e) {
                    break;
                }
            }
            return commentTextString;
        }

        public static void textSearchClaims(String text) {
            ClaimsElements claimsElements = new ClaimsElements();

            boolean notFound = true;
            int position = 0;
            while (notFound) {
                try {
                    onView(withText(text)).check(matches(isDisplayed()));
                    notFound = false;
                } catch (NoMatchingViewException ignored) {

                }
                claimsElements.getBlockClaims().check(matches(isDisplayed())).perform(actionOnItemAtPosition(position, swipeUp()));
                position += 1;
            }
            SystemClock.sleep(3000);
            claimsElements.getBlockClaims().check(matches(isDisplayed())).perform(actionOnItemAtPosition(position - 1, click()));
        }

        public static String searchForAnUncreatedClaim(String text) {
            ClaimsElements claimsElements = new ClaimsElements();

            boolean notFound = true;
            int position = 0;

            String commentTextString = Text.getText(onView(allOf(withId(R.id.description_material_text_view),
                    withParent(withParent(withIndex(withId(R.id.claim_list_card), position))))));
            while (notFound) {
                try {
                    onView(withText(text)).check(matches(isDisplayed()));
                    notFound = false;
                } catch (NoMatchingViewException ignore) {
                }
                try {
                    claimsElements.getBlockClaims().check(matches(isDisplayed())).perform(actionOnItemAtPosition(position, swipeUp()));
                    position += 1;
                } catch (PerformException e) {
                    break;
                }
            }
            return commentTextString;
        }
    }

    public static class DateTime {
        public static ViewInteraction headerCalendarDate(String dayOfWeek, String month, String day) {
            return onView(allOf(withClassName(is("com.google.android.material.textview.MaterialTextView")), withText(dayOfWeek + ", " + month + " " + day),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.LinearLayout")),
                                    0),
                            1)));
        }

        public static ViewInteraction headerCalendarYear(String year) {
            return onView(allOf(withClassName(is("com.google.android.material.textview.MaterialTextView")), withText(year),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.LinearLayout")),
                                    0),
                            0)));
        }

        public static void settingTheDate(int year, int month, int day) {
            onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month, day)).perform(click());
        }

        public static Date dateFormat(String date) throws ParseException {
            final SimpleDateFormat format = new SimpleDateFormat(
                    "dd.MM.yyyy", Locale.US);
            format.setLenient(false);
            return format.parse(date);
        }

        public static String localDate() {
            return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String invalidGeneratorDate() {
            LocalDate startDate = LocalDate.now().plusYears(3);
            long start = startDate.getDayOfMonth();
            LocalDate endDate = LocalDate.now().plusYears(3);
            long end = endDate.lengthOfYear();
            long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
            return startDate.plusDays(randomEpochDay).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String generatorDate() {
            LocalDate startDate = LocalDate.now().plusDays(3);
            long start = startDate.getDayOfMonth();
            LocalDate endDate = LocalDate.now().plusYears(1);
            long end = endDate.lengthOfYear();
            long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
            return startDate.plusDays(randomEpochDay).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String generatorDate2() {
            LocalDate startDate = LocalDate.now().minusYears(1);
            long start = startDate.getDayOfMonth();
            LocalDate endDate = LocalDate.now().plusDays(3);
            long end = endDate.lengthOfYear();
            long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
            return startDate.plusDays(randomEpochDay).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String randomHour23() {
            String hour;
            int min = 0;
            int max = 9;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);
            if (random < 10) {
                hour = "0" + random;
            } else {
                hour = String.valueOf(random);
            }
            return hour;
        }

        public static String randomMinute59() {
            String minute;
            int min = 0;
            int max = 59;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);
            if (random < 10) {
                minute = "0" + random;
            } else {
                minute = String.valueOf(random);
            }
            return minute;
        }

        public static String invalidHour() {
            int min = 24;
            int max = 99;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);
            return String.valueOf(random);
        }

        public static String invalidMinute() {
            String minute;
            int min = 0;
            int max = 99;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);
            if (random < 10) {
                minute = "0" + random;
            } else {
                minute = String.valueOf(random);
            }
            return minute;
        }
    }

    public static class Text {

        public static String text() {
            Faker faker = new Faker();
            return faker.name.lastName();
        }

        public static String firstUpperCase(String text) {
            if (text == null || text.isEmpty())
                return "";
            return text.substring(0, 1).toUpperCase() + text.substring(1);
        }

        public static String textSymbol(int numberOfLetters) {
            Random random = new Random();
            String randomText;
            String[] texts = {
                    " ", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
                    "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "!", "@", "#", "$", "%", "^", "&",
                    "*", "(", ")", "_", "+"
            };
            randomText = texts[random.nextInt(texts.length)];
            for (int i = 1; i < numberOfLetters; i++) {
                randomText = randomText.concat(texts[random.nextInt(texts.length)]);
            }
            return randomText;
        }

        public static String text51Symbol() {
            return "qwertyuiop[]asdfghjkl;'zxcvbnm,./1234567890-=+_)(*&";
        }


        public static String getText(ViewInteraction matcher) {
            final String[] text = new String[1];
            ViewAction viewAction = new ViewAction() {

                @Override
                public Matcher<View> getConstraints() {
                    return isAssignableFrom(TextView.class);
                }

                @Override
                public String getDescription() {
                    return "Text of the view";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    TextView textView = (TextView) view;
                    text[0] = textView.getText().toString();
                }
            };
            matcher.perform(viewAction);
            return text[0];
        }
    }

    public static Matcher<View> childAtPosition(Matcher<View> matcher, final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }
}
