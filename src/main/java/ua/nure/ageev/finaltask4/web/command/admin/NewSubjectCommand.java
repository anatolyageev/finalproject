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

public class NewSubjectCommand extends Command {

    private static final long serialVersionUID = -1573445785199573283L;

    private static final Logger LOG = Logger.getLogger(NewSubjectCommand.class);

    private static final String LANGUAGE_EN = "en";
    private static final String LANGUAGE_RU = "ru";

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
        String nameEn = request.getParameter("nameEn");
        String nameRu = request.getParameter("nameRu");

        Subject subject = new Subject();
        subject.setSubjectName(shortName);
        subject = subjectService.createSubject(subject,shortName);

        subject.setSubjectName(nameEn);
        subjectService.createSubjectLocale(subject,LANGUAGE_EN);

        subject.setSubjectName(nameRu);
        subjectService.createSubjectLocale(subject,LANGUAGE_RU);

        return Path.PAGE_ADMIN_CREATE_SUBJECT; // нужен переход на создание теста
    }
}
