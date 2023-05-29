package localization;

import app.LocaleExplore;

import java.util.Locale;

public class DisplayLocales {
    public static void displayAvailableLocales() {
        Locale[] locales = Locale.getAvailableLocales();
        System.out.println(LocaleExplore.getMessage("locales"));

        for (Locale locale : locales) {
            System.out.println(locale.toString());
        }
    }
}