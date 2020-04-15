package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizzeTestSortCommand extends Command {

    private static final long serialVersionUID = -1573444785199573283L;

    private static final Logger LOG = Logger.getLogger(QuizzeTestSortCommand.class);


    /**
     * Execution method for QuizzeTestSortCommand.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("QuizzeTestSortCommand starts");
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute("currentLocale");
        LOG.debug("QuizzeTestSortCommand get locale: " + local);
        if (local == null) {
            local = "en";
        }
        LOG.debug("QuizzeTestSortCommand get locale after if: " + local);
        String sortDirection = request.getParameter("sort");
        List<Test> test = new ArrayList<>();

        test = (ArrayList)session.getAttribute("testList");

        LOG.debug("QuizzeTestSortCommand gets array test: " + test);

        switch(sortDirection) {
            case "name":
                test.sort((a, b) -> a.getTestName().compareTo(b.getTestName()));
                break;
            case "level":
                test.sort((a, b) -> a.getDifficultyLevel()-b.getDifficultyLevel());
                break;
            case "number":
                test.sort((a, b) -> a.getQuestionQuantity()-b.getQuestionQuantity());
                break;
            case "minutes":
                test.sort((a, b) -> a.getMinutesToComplite()-b.getMinutesToComplite());
                break;
            default:
                test.sort((a, b) -> a.getId().compareTo(b.getId()));
        }

        request.setAttribute("testList", test);

        LOG.debug("QuizzeTestSortCommand finished");

        return Path.PAGE_QUIZZE_LIST;
    }


}
