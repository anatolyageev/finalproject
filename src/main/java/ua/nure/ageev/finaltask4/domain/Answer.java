package ua.nure.ageev.finaltask4.domain;

public class Answer extends LongID{

    private static final long serialVersionUID = 1L;

    private Boolean isCorrectAnswer;

    private String answerText;

    public Boolean getCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "isCorrectAnswer=" + isCorrectAnswer +
                ", answerText='" + answerText + '\'' +
                '}';
    }
}
