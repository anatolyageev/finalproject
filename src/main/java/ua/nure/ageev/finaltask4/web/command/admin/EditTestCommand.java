package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.TestRepositoryImpl;
import ua.nure.ageev.finaltask4.services.TestService;
import ua.nure.ageev.finaltask4.services.impl.TestServiceImpl;
import ua.nure.ageev.finaltask4.web.command.Command;
import ua.nure.ageev.finaltask4.web.utils.ConstantsForCommands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTestCommand extends Command {

    private static final long serialVersionUID = -1573445777199573283L;

    private static final Logger LOG = Logger.getLogger(EditTestCommand.class);

    /**
     * Execution method for edit test command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("EditTestCommand finished");
        TestService testService = new TestServiceImpl(new TestRepositoryImpl());

        Long testId = Long.parseLong(request.getParameter("testId"));
        Long subjectId = Long.parseLong(request.getParameter("subjectId"));

        Test testEn = testService.getOne(testId, ConstantsForCommands.LANGUAGE_EN);
        Test testRu = testService.getOne(testId, ConstantsForCommands.LANGUAGE_RU);

        request.setAttribute("testEn", testEn);
        LOG.debug("Set attribute testEn --> " + testEn);
        request.setAttribute("testRu", testRu);
        LOG.debug("Set attribute testRu --> " + testRu);
        request.setAttribute("subjectId", subjectId);
        LOG.debug("EditTestCommand subjectId" + subjectId);
        return Path.PAGE_ADMIN_EDIT_TEST;
    }
}
