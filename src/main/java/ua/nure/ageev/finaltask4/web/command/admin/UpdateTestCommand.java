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
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateTestCommand extends Command {

    private static final long serialVersionUID = -1573445756459573283L;

    private static final Logger LOG = Logger.getLogger(UpdateTestCommand.class);

    /**
     * Execution method for update test command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("UpdateTestCommand start");

        TestService testService = new TestServiceImpl(new TestRepositoryImpl());
        String local = DataHelper.getLanguage(request);
        Long testId = Long.parseLong(request.getParameter("testId"));
        Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        String nameEn = request.getParameter("nameEn");
        String nameRu = request.getParameter("nameRu");
        Integer difficultyLevel = Integer.parseInt(request.getParameter("difficulty-level"));
        Integer minComplete = Integer.parseInt(request.getParameter("min-complete"));

        Test test = new Test();
        test.setId(testId);
        test.setMinutesToComplite(minComplete);
        test.setDifficultyLevel(difficultyLevel);
        test.setTestName(nameEn);
        LOG.debug("UpdateTestCommand updated test" + test);

        testService.update(test,local);
        testService.updateName(test,ConstantsForCommands.LANGUAGE_EN);
        test.setTestName(nameRu);
        testService.updateName(test,ConstantsForCommands.LANGUAGE_RU);

        List<Test> testList = testService.findAllByParent(subjectId, local);
        request.setAttribute("test", testList);
        request.setAttribute("subjectId", subjectId);

        LOG.debug("UpdateTestCommand finished");
        return Path.PAGE_ADMIN_TEST_LIST;
    }
}
