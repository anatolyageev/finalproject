package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Subject;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.SubjectRepositoryImpl;
import ua.nure.ageev.finaltask4.services.SubjectService;
import ua.nure.ageev.finaltask4.services.impl.SubjectServiceImpl;
import ua.nure.ageev.finaltask4.web.command.Command;
import ua.nure.ageev.finaltask4.web.utils.ConstantsForCommands;
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class NewSubjectCommand extends Command {

    private static final long serialVersionUID = -1573445785199573283L;

    private static final Logger LOG = Logger.getLogger(NewSubjectCommand.class);

    /**
     * Execution method for create new subject.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        SubjectService subjectService = new SubjectServiceImpl(new SubjectRepositoryImpl());
        String shortName = request.getParameter("nameShort");
        String local = DataHelper.getLanguage(request);
        HttpSession session = request.getSession();
        String nameEn = request.getParameter("nameEn");
        String nameRu = request.getParameter("nameRu");
        String encoding = request.getCharacterEncoding();
        LOG.debug("encoding page: " + encoding);
        Subject subject = new Subject();
        subject.setSubjectName(shortName);
        subject = subjectService.createSubject(subject,shortName);

        subject.setSubjectName(nameEn);
        subjectService.createSubjectLocale(subject, ConstantsForCommands.LANGUAGE_EN);

        subject.setSubjectName(nameRu);
        subjectService.createSubjectLocale(subject,ConstantsForCommands.LANGUAGE_RU);

        List<Subject> subjects = subjectService.getAll(local);
        session.setAttribute("subjectList", subjects);

        return Path.PAGE_ADMIN_PAGE;
    }
}
