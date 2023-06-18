package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutElements {

    private final ViewInteraction version = onView(withId(R.id.about_version_title_text_view));
    private final ViewInteraction numVersion = onView(withId(R.id.about_version_value_text_view));
    private final ViewInteraction privacyPolicy = onView(withId(R.id.about_privacy_policy_label_text_view));
    private final ViewInteraction termsOfUse = onView(withId(R.id.about_terms_of_use_value_text_view));
    private final ViewInteraction linkPrivacyPolicy = onView(withId(R.id.about_privacy_policy_value_text_view));
    private final ViewInteraction linkTermsOfUse = onView(withId(R.id.about_terms_of_use_value_text_view));
    private final ViewInteraction teco = onView(withId(R.id.about_company_info_label_text_view));
    private final ViewInteraction aboutExitButton = onView(withId(R.id.about_back_image_button));

    public ViewInteraction getAboutExitButton() {
        return aboutExitButton;
    }

    public ViewInteraction getTermsOfUse() {
        return termsOfUse;
    }

    public ViewInteraction getLinkPrivacyPolicy() {
        return linkPrivacyPolicy;
    }

    public ViewInteraction getLinkTermsOfUse() {
        return linkTermsOfUse;
    }

    public ViewInteraction getTeco() {
        return teco;
    }

    public ViewInteraction getVersion() {
        return version;
    }

    public ViewInteraction getNumVersion() {
        return numVersion;
    }

    public ViewInteraction getPrivacyPolicy() {
        return privacyPolicy;
    }
}
