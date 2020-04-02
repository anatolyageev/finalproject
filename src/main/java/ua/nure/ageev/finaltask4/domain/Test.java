package ua.nure.ageev.finaltask4.domain;

import java.util.ArrayList;
import java.util.List;

public class Test extends LongID {

    private static final long serialVersionUID = 1L;

    private Integer questionQuantity;

    private Integer difficultyLevel;

    private Integer minutesToComplite;

    private String testName;

    private List<Qustion> qustions = new ArrayList<>();

    public Integer getQuestionQuantity() {
        return questionQuantity;
    }

    public void setQuestionQuantity(Integer questionQuantity) {
        this.questionQuantity = questionQuantity;
    }

    public Integer getMinutesToComplite() {
        return minutesToComplite;
    }

    public void setMinutesToComplite(Integer minutesToComplite) {
        this.minutesToComplite = minutesToComplite;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<Qustion> getQustions() {
        return qustions;
    }

    public void setQustions(List<Qustion> qustions) {
        this.qustions = qustions;
    }

    @Override
    public String toString() {
        return "Test{" +
                "questionQuantity=" + questionQuantity +
                ", difficultyLevel=" + difficultyLevel +
                ", minutesToComplite=" + minutesToComplite +
                ", testName='" + testName + '\'' +
                ", qustions=" + qustions +
                '}';
    }
}
