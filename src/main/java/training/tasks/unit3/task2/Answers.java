package training.tasks.unit3.task2;

import java.util.ResourceBundle;

public class Answers extends Resources {

    private static final String resourcesRU = "unit3/task2/answersRU";
    private static final String resourcesEN = "unit3/task2/answersEN";

    private ResourceBundle resources;

    public Answers() {
        super(SupportedLocales.EN, resourcesEN);
    }

    @Override
    public void switchLanguage(SupportedLocales locale) {
        String currentResource = resourcesEN;
        if (locale == SupportedLocales.RU) {
            currentResource = resourcesRU;
        }

        setupLocale(locale, currentResource);
    }

}
