package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        LOG.debug("QuizzeTestCommand starts");

        List<Test> testList = (ArrayList)request.getAttribute("test");
        testList.sort((a,b)->a.getTestName().compareTo(b.getTestName()));

        return Path.PAGE_QUIZZE_LIST;
    }



}
