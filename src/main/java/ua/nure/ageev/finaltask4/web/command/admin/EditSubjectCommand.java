package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Subject;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.SubjectRepositoryImpl;
import ua.nure.ageev.finaltask4.services.SubjectService;
import ua.nure.ageev.finaltask4.services.impl.SubjectServiceImpl;
import ua.nure.ageev.finaltask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditSubjectCommand extends Command {

    private static final long serialVersionUID = -1573445777199573283L;

    private static final Logger LOG = Logger.getLogger(EditSubjectCommand.class);

    private static final String LANGUAGE_EN = "en";

    private static final String LANGUAGE_RU = "ru";

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("EditSubjectCommand finished");
        SubjectService subjectService = new SubjectServiceImpl(new SubjectRepositoryImpl());
        Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        LOG.debug("Get attribute subjectId: " + subjectId);
        Subject subject = new Subject();
        subject.setId(subjectId);
        Subject subjectEn = subjectService.getOne(subject,LANGUAGE_EN);
        Subject subjectRu = subjectService.getOne(subject,LANGUAGE_RU);

        request.setAttribute("subjectEn",subjectEn);
        LOG.debug("Set attribute subjectEn --> " + subjectEn);
        request.setAttribute("subjectRu",subjectRu);
        LOG.debug("Set attribute subjectRu --> " + subjectRu);

        LOG.debug("EditSubjectCommand finished");
        return Path.PAGE_ADMIN_EDIT_SUBJECT;
    }
}
