package localization;

import app.LocaleExplore;

import java.util.Locale;

public class SetLocale {
    public static void setLocale(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        Locale.setDefault(locale);
        LocaleExplore.updateMessagesBundle(locale);
        System.out.println(LocaleExplore.getMessage("locale.set", locale.toString()));
    }
}