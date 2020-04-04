package ua.nure.ageev.finaltask4.domain;

public class Answer extends LongID{

    private static final long serialVersionUID = 1L;

    private Boolean correctAnswer;

    private String answerText;

    public Boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
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
                "isCorrectAnswer=" + correctAnswer +
                ", answerText='" + answerText + '\'' +
                '}';
    }
}
