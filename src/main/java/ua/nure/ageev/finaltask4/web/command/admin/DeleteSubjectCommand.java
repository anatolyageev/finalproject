package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Subject;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.SubjectRepositoryImpl;
import ua.nure.ageev.finaltask4.services.SubjectService;
import ua.nure.ageev.finaltask4.services.impl.SubjectServiceImpl;
import ua.nure.ageev.finaltask4.web.command.Command;
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteSubjectCommand extends Command {

    private static final long serialVersionUID = -1573445767199573283L;

    private static final Logger LOG = Logger.getLogger(DeleteSubjectCommand.class);

    /**
     * Execution method for delete subject.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("DeleteSubjectCommand starts");

        HttpSession session = request.getSession();

        String local = DataHelper.getLanguage(request);
        LOG.debug("AdminQuizzeCommand get locale after if: " + local);

        SubjectService subjectService = new SubjectServiceImpl(new SubjectRepositoryImpl());
        Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        subjectService.deleteSubject(subjectId);

        List<Subject> subjects = DataHelper.getSubjects(local);
        LOG.debug("Found in DB: subjects --> " + subjects);
        session.setAttribute("subjectList", subjects);

        LOG.debug("DeleteSubjectCommand finished");
        return Path.PAGE_ADMIN_PAGE;
    }
}
