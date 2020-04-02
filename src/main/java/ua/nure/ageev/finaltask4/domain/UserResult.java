package ua.nure.ageev.finaltask4.domain;

import java.util.Date;

public class UserResult extends LongID {

    private static final long serialVersionUID = 1L;

    private Test test;

    private Integer evaluation;

    private Date evaluationDate;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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

    @Override
    public String toString() {
        return "UserResult{" +
                "test=" + test +
                ", evaluation=" + evaluation +
                ", evaluationDate=" + evaluationDate +
                '}';
    }
}
