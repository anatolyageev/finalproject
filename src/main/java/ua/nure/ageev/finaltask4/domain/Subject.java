package ua.nure.ageev.finaltask4.domain;

import java.util.ArrayList;
import java.util.List;

public class Subject extends LongID {

    private static final long serialVersionUID = 1L;

    private String subjectName;

    private List<Test> testList = new ArrayList<>();

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                ", testList=" + testList +
                '}';
    }
}
