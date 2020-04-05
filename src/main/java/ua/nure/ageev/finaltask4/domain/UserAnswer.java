package ua.nure.ageev.finaltask4.domain;

public class UserAnswer {
    private Long questionId;
    private Boolean correctAnswer;
    private Boolean questionAnswered;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Boolean isQuestionAnswered() {
        return questionAnswered;
    }

    public void setQuestionAnswered(Boolean questionAnswered) {
        this.questionAnswered = questionAnswered;
    }


}
