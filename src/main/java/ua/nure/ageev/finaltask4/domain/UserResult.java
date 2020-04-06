package ua.nure.ageev.finaltask4.domain;

import java.util.Date;

public class UserResult extends LongID {

    private static final long serialVersionUID = 1L;

    private Long testId;

    private Integer evaluation;

    private Date evaluationDate;

    private Long userId;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserResult{" +
                "testId=" + testId +
                ", evaluation=" + evaluation +
                ", evaluationDate=" + evaluationDate +
                ", userId=" + userId +
                '}';
    }
}
