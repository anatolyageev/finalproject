package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Subject;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.SubjectRepositoryImpl;
import ua.nure.ageev.finaltask4.services.SubjectService;
import ua.nure.ageev.finaltask4.services.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SubjectCommand extends Command {
    private static final long serialVersionUID = -1573481565199573283L;

    private static final Logger LOG = Logger.getLogger(SubjectCommand.class);

    /**
     * Execution method for SubjectCommand.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("SubjectCommand starts");
        HttpSession session = request.getSession();
        String getRequestURI = request.getRequestURI();
        LOG.debug("SubjectCommand get getRequestURI: " + getRequestURI);
        String local = (String) session.getAttribute("currentLocale");
        LOG.debug("SubjectCommand get locale: " + local);
        if (local == null) {
            local = "en";
        }
        LOG.debug("SubjectCommand get locale after if: " + local);
        SubjectService subjectService = new SubjectServiceImpl(new SubjectRepositoryImpl());
        List<Subject> subjects = subjectService.getAll(local);
        LOG.debug("Found in DB: userList --> " + subjects);

        request.setAttribute("subjectList", subjects);
        LOG.debug("Set the request attribute: subjectList --> " + subjects);
        LOG.debug("SubjectCommand finished");
        return Path.PAGE_QUIZZE_LIST;
    }
}
