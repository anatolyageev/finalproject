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

public class DeleteTestCommand extends Command {

    private static final long serialVersionUID = -1573445767134573283L;

    private static final Logger LOG = Logger.getLogger(DeleteTestCommand.class);

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("DeleteTestCommand starts");

        String local = DataHelper.getLanguage(request);
        Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        Long testId = Long.parseLong(request.getParameter("testId"));
        TestService testService = new TestServiceImpl(new TestRepositoryImpl());

        testService.delete(testId);

        List<Test> testList = testService.findAllByParent(subjectId, local);
        request.setAttribute("test", testList);
        request.setAttribute("subjectId", subjectId);

        LOG.debug("DeleteTestCommand finished");
        return Path.PAGE_ADMIN_TEST_LIST;
    }
}
