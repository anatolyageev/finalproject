package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Subject;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminQuizzeCommand extends Command {

    private static final long serialVersionUID = -1573481569877573283L;

    private static final Logger LOG = Logger.getLogger(AdminQuizzeCommand.class);

    /**
     * Execution method for initiate admin quizzes page.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command  AdminQuizzeCommand starts");

        HttpSession session = request.getSession();

        String local = DataHelper.getLanguage(request);
        LOG.debug("AdminQuizzeCommand get locale after if: " + local);

        List<Subject> subjects = DataHelper.getSubjects(local);
        LOG.debug("Found in DB: subjects --> " + subjects);

        List<Test> test = DataHelper.getTests(local);
        LOG.debug("Found in DB: list of tests --> " + subjects);

        // put user order beans list to session
        session.setAttribute("subjectList", subjects);
        session.setAttribute("testList", test);
        LOG.debug("Set the request attribute: subjectList --> " + subjects);
        LOG.debug("AdminQuizzeCommand finished");

        return Path.PAGE_ADMIN_PAGE;
    }
}
