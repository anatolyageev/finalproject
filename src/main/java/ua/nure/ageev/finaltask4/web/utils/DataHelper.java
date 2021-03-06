package ua.nure.ageev.finaltask4.web.utils;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.*;
import ua.nure.ageev.finaltask4.repository.impl.AnswerRepositoryImpl;
import ua.nure.ageev.finaltask4.repository.impl.QuestionRepositoryImpl;
import ua.nure.ageev.finaltask4.repository.impl.SubjectRepositoryImpl;
import ua.nure.ageev.finaltask4.repository.impl.TestRepositoryImpl;
import ua.nure.ageev.finaltask4.services.*;
import ua.nure.ageev.finaltask4.services.impl.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class DataHelper {

    private static final Logger LOG = Logger.getLogger(DataHelper.class);

    private DataHelper() {
    }

    /**
     * Method for getting list of users.
     *
     */
    public static List<User> getUsersList() {
        UserService userService = new UserServiceImpl();
        List<User> userList = userService.getAll();
        LOG.debug("Found in DB: userList --> " + userList);
        return userList;
    }

    /**
     * Method for getting language from session.
     */
    public static String getLanguage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute("currentLocale");
        if (local == null) {
            local = "en";
        }
        LOG.debug("DataHelper getLanguage() --> " + local);
        return local;
    }

    /**
     * Method for getting list of subject.
     *
     */
    public static List<Subject> getSubjects(String local) {
        SubjectService subjectService = new SubjectServiceImpl(new SubjectRepositoryImpl());
        return subjectService.getAll(local);
    }


    /**
     * Method for getting list of test.
     *
     */
    public static List<Test> getTests(String local) {
        TestService testService = new TestServiceImpl(new TestRepositoryImpl());
        return testService.findAll(local);
    }

    /**
     * Method to check does test name already exist.
     */
    public static Boolean findPersonByName(final List<Test> list, final String name) {
        return list.stream()
                .anyMatch(p -> p.getTestName().equals(name));
    }

    /**
     * Method which added list of question to the questions.
     */
    public static void addAnswersToQuestions(List<Question> questionList, String local){
        AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryImpl());
        for (Question q:questionList) {
            List<Answer> answerList = answerService.findAllByParent(q.getId(),local);
            q.setAnswers(answerList);
        }
    }

    /**
     * Method which added list of new empty answers for the question.
     *
     *  @param question
     */
    public static List<Answer> answersCreation(Question question) {
        AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryImpl());
        LOG.debug("DataHelper answersCreation question --> " + question);
        List<Answer> answerList = new ArrayList<>();
        for (int i=0;i<4;i++){
            LOG.debug("DataHelper answersCreation question.getId() --> " + question.getId());
            Answer tempAnswer = new Answer();
            tempAnswer = answerService.insert(question.getId(),new Answer());
            answerList.add(tempAnswer);
            tempAnswer.setAnswerText("");
            answerService.insertName(tempAnswer,ConstantsForCommands.LANGUAGE_EN);
            answerService.insertName(tempAnswer,ConstantsForCommands.LANGUAGE_RU);
        }
        LOG.debug("DataHelper answersCreation answerList --> " + answerList);
        return answerList;
    }
    public static void updateQuestionList(HttpServletRequest request, String local) {
        HttpSession session = request.getSession();
        Long testId = (Long)session.getAttribute("testId");
        QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryImpl());
        List<Question> questionList = questionService.findAllByParent(testId, local);
        LOG.debug("Question list: " + questionList);
        session.setAttribute("questionList", questionList);
    }

}

