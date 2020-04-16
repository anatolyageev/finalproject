package ua.nure.ageev.finaltask4.web.utils;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Subject;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.repository.impl.SubjectRepositoryImpl;
import ua.nure.ageev.finaltask4.repository.impl.TestRepositoryImpl;
import ua.nure.ageev.finaltask4.services.SubjectService;
import ua.nure.ageev.finaltask4.services.TestService;
import ua.nure.ageev.finaltask4.services.UserService;
import ua.nure.ageev.finaltask4.services.impl.SubjectServiceImpl;
import ua.nure.ageev.finaltask4.services.impl.TestServiceImpl;
import ua.nure.ageev.finaltask4.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    public static List<Subject> getSubjects(String local) {
        SubjectService subjectService = new SubjectServiceImpl(new SubjectRepositoryImpl());
        return subjectService.getAll(local);
    }

    public static List<Test> getTests(String local) {
        TestService testService = new TestServiceImpl(new TestRepositoryImpl());
        return testService.findAll(local);
    }
}

