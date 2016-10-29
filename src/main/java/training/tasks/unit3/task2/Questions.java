package training.tasks.unit3.task2;

public class Questions extends Resources {

    private static final String resourcesRU = "unit3/task2/questionsRU";
    private static final String resourcesEN = "unit3/task2/questionsEN";

    public Questions() {
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
