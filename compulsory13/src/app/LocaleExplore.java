package app;



import localization.DisplayLocales;
import localization.Info;
import localization.SetLocale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    private static ResourceBundle messages;
    private static MessageFormat messageFormat;

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH); // Set the default locale to English
        messages = ResourceBundle.getBundle("res.Messages");

        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print(getMessage("prompt"));
            command = scanner.nextLine();

            if (command.equals("exit")) {
                break;
            }

            executeCommand(command);
        }

        scanner.close();
    }

    public static String getMessage(String key, Object... args) {
        String message = messages.getString(key);
        return MessageFormat.format(message, args);
    }

    private static void executeCommand(String command) {
        String[] parts = command.split(" ");
        String action = parts[0];

        switch (action) {
            case "locales":
                DisplayLocales.displayAvailableLocales();
                break;
            case "set":
                String languageTag = parts[1];
                SetLocale.setLocale(languageTag);
                break;
            case "info":
                String language = parts[1];
                Info.displayInfo(language);
                break;
            default:
                System.out.println(getMessage("invalid"));
                break;
        }
    }

    public static void updateMessagesBundle(Locale locale) {
        messages = ResourceBundle.getBundle("res.Messages", locale);
        messageFormat = new MessageFormat("", locale);
    }
}