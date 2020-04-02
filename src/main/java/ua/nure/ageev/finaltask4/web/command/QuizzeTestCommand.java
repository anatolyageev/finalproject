package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.TestRepositoryImpl;
import ua.nure.ageev.finaltask4.services.TestService;
import ua.nure.ageev.finaltask4.services.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuizzeTestCommand extends Command {

    private static final long serialVersionUID = -1573444565199573283L;

    private static final Logger LOG = Logger.getLogger(QuizzeTestCommand.class);
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("QuizzeTestCommand starts");
        HttpSession session = request.getSession();
        String getRequestURI = request.getRequestURI();
        LOG.debug("QuizzeTestCommand get getRequestURI: " +getRequestURI );
        String local = (String) session.getAttribute("currentLocale");
        LOG.debug("SubjectCommand get locale: " + local);
        if(local == null){
            local ="en";
        }
        LOG.debug("QuizzeTestCommand get locale after if: " + local);
        Long subjectId = Long.parseLong(request.getParameter("subject_id"));
        TestService testService = new TestServiceImpl(new TestRepositoryImpl());
        List<Test> test = testService.findAllByParent(subjectId,local);
        LOG.debug("Found in DB: test list --> " + test);

        //TODO sorting
      //  List<Subject> subjectList = request.getAttribute("subjectList");
        // put user order beans list to request
        request.setAttribute("testList", test);
        LOG.debug("Set the request attribute: subjectList --> " + test);
        LOG.debug("QuizzeTestCommand finished");
        return Path.PAGE_USER_PAGE;
    }
}
