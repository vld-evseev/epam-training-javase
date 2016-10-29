package training.tasks.unit3.task2;

import java.util.*;

public abstract class Resources implements Localizable {

    private ResourceBundle resources;

    public Resources(SupportedLocales locale, String localizedResources) {
        setupLocale(locale, localizedResources);
    }

    public List<String> showAll() {
        List<String> questions = new ArrayList<>();
        Enumeration<String> keys = resources.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            questions.add(resources.getString(key));
        }

        return questions;
    }

    public String getByNumber(int num) {
        return resources.getString("n" + num);
    }

    public void setupLocale(SupportedLocales locale, String localizedResources) {
        try {
            resources = ResourceBundle.getBundle(localizedResources, Locale.forLanguageTag(locale.name()));
        } catch (java.util.MissingResourceException ex) {
            System.out.println("Can't find locale " + locale + " for base name " + localizedResources);
            throw new RuntimeException(ex);
        }
    }

}
