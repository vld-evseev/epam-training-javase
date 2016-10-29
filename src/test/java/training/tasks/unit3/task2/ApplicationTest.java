package training.tasks.unit3.task2;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    @Test
    public void showAllQuestions() throws Exception {
        Application app = new Application();
        List<String> questions = app.showAllQuestions();

        for (String question : questions) {
            System.out.println(question);
        }

        assertEquals(questions.size(), 7);
    }

    @Test
    public void showAllAnswers() throws Exception {
        Application app = new Application();
        List<String> answers = app.showAllQuestions();

        for (String answer : answers) {
            System.out.println(answer);
        }

        assertEquals(answers.size(), 7);
    }

    @Test
    public void switchLanguage() throws Exception {
        Application app = new Application();
        List<String> answers = app.showAllQuestions();

        for (String answer : answers) {
            System.out.println(answer);
        }

        app.switchLanguage(SupportedLocales.RU);

        answers = app.showAllQuestions();
        for (String answer : answers) {
            System.out.println(answer);
        }
    }

    @Test
    public void showAnswerToTheQuestion() throws Exception {
        Application app = new Application();
        app.switchLanguage(SupportedLocales.RU);

        String answer = app.showAnswerToTheQuestion(5);

        assertEquals(answer, "ответ5");
    }

}