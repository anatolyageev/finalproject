package ua.nure.ageev.finaltask4.domain;

import java.util.ArrayList;
import java.util.List;

public class Qustion extends LongID{
    private static final long serialVersionUID = 1L;

    private String qustionText;

    private List<Answer> aswers = new ArrayList<>();

    public String getQustionText() {
        return qustionText;
    }

    public void setQustionText(String qustionText) {
        this.qustionText = qustionText;
    }

    public List<Answer> getAswers() {
        return aswers;
    }

    public void setAswers(List<Answer> aswers) {
        this.aswers = aswers;
    }

    @Override
    public String toString() {
        return "Qustion{" +
                "qustionText='" + qustionText + '\'' +
                ", aswers=" + aswers +
                '}';
    }
}
