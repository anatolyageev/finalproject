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

public class UpdateSubjectCommand extends Command{

    private static final long serialVersionUID = -1573445785459573283L;

    private static final Logger LOG = Logger.getLogger(UpdateSubjectCommand.class);

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
        LOG.debug("UpdateSubjectCommand start");
        SubjectService subjectService = new SubjectServiceImpl(new SubjectRepositoryImpl());

        String nameEn = request.getParameter("nameEn");
        String nameRu = request.getParameter("nameRu");
        Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        String encoding = request.getCharacterEncoding();
        LOG.debug("encoding page: " + encoding);
        Subject subject = new Subject();
        subject.setId(subjectId);

        subject.setSubjectName(nameEn);
        subjectService.update(subject,LANGUAGE_EN);

        subject.setSubjectName(nameRu);
        subjectService.update(subject,LANGUAGE_RU);

        LOG.debug("UpdateSubjectCommand finished");
        return Path.PAGE_ADMIN_PAGE;
    }
}
