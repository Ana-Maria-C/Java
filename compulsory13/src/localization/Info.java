package localization;
import app.LocaleExplore;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Info {
    public static void displayInfo(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);

        System.out.println(LocaleExplore.getMessage("info", locale.toString()));
        System.out.println("Country: " + locale.getDisplayCountry(locale));
        System.out.println("Language: " + locale.getDisplayLanguage(locale));
        System.out.println("Currency: " + Currency.getInstance(locale).getCurrencyCode() +
                " (" + Currency.getInstance(locale).getDisplayName() + ")");
        System.out.println("Week Days: " + String.join(", ", DateFormatSymbols.getInstance(locale).getWeekdays()));
        System.out.println("Months: " + String.join(", ", DateFormatSymbols.getInstance(locale).getMonths()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", locale);
        String formattedDate = dateFormat.format(new Date());
        System.out.println("Today: " + formattedDate);
    }
}
