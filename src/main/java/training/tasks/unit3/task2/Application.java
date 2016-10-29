package training.tasks.unit3.task2;

import java.util.List;

public class Application {

    private static final String QUESTION_NOT_EXISTS = "There is no question for number ";

    private Questions questions;
    private Answers answers;

    public Application() {
        questions = new Questions();
        answers = new Answers();
    }

    public List<String> showAllQuestions() {
        return questions.showAll();
    }

    public List<String> showAllAnswers() {
        return answers.showAll();
    }

    public void switchLanguage(SupportedLocales locale) {
        questions.switchLanguage(locale);
        answers.switchLanguage(locale);
    }

    public String showAnswerToTheQuestion(int num) {
        if (num > questions.showAll().size() || num <= 0) {
            return QUESTION_NOT_EXISTS + num;
        }

        return answers.getByNumber(num);
    }


}
