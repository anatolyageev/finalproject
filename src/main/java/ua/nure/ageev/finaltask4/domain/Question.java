package ua.nure.ageev.finaltask4.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for ID ;
 *
 * @author A.Ageev
 */
public class Question extends LongID {
    private static final long serialVersionUID = 1L;

    private String questionText;

    private List<Answer> answers = new ArrayList<>();

    private Integer numberCorrectAnswers;

    public Integer getNumberCorrectAnswers() {
        return numberCorrectAnswers;
    }

    public void setNumberCorrectAnswers(Integer numberCorrectAnswers) {
        this.numberCorrectAnswers = numberCorrectAnswers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String qustionText) {
        this.questionText = qustionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", answers=" + answers +
                ", numberCorrectAnswers=" + numberCorrectAnswers +
                '}';
    }
}
