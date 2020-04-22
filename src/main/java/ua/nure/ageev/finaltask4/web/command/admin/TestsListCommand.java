package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.TestRepositoryImpl;
import ua.nure.ageev.finaltask4.services.TestService;
import ua.nure.ageev.finaltask4.services.impl.TestServiceImpl;
import ua.nure.ageev.finaltask4.web.command.Command;
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TestsListCommand extends Command {

    private static final long serialVersionUID = -1573445784559573283L;

    private static final Logger LOG = Logger.getLogger(TestsListCommand.class);

    /**
     * Execution method for command which give access to list of tests.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("TestsListCommand starts");

        String local = DataHelper.getLanguage(request);
        TestService testService = new TestServiceImpl(new TestRepositoryImpl());

        Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        List<Test> test = testService.findAllByParent(subjectId,local);

        request.setAttribute("test",test);
        request.setAttribute("subjectId",subjectId);
        LOG.debug("TestsListCommand finished");
        return Path.PAGE_ADMIN_TEST_LIST;
    }
}
