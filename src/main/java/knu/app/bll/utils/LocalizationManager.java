package knu.app.bll.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager {
    private static ResourceBundle bundle =
            ResourceBundle.getBundle("messages", Locale.ENGLISH);

    public static String tr(String key) {
        return bundle.getString(key);
    }

    public static void setLocale(Locale locale) {
        bundle = ResourceBundle.getBundle("messages", locale);
    }
}
