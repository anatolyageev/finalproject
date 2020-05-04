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

public class NewTestCommand extends Command {

    private static final long serialVersionUID = -1573445790199573283L;

    private static final Logger LOG = Logger.getLogger(NewTestCommand.class);

    /**
     * Execution method for command new test creation.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        TestService testService = new TestServiceImpl(new TestRepositoryImpl());
        String local = DataHelper.getLanguage(request);
        Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        String nameEn = request.getParameter("nameEn");
        String nameRu = request.getParameter("nameRu");
        Integer difficultyLevel = Integer.parseInt(request.getParameter("difficulty-level"));
        Integer minComplete = Integer.parseInt(request.getParameter("min-complete"));

        List testListEn = testService.findAll(ConstantsForCommands.LANGUAGE_EN);
        LOG.debug("testListEn: " + testListEn);
        List testListRu = testService.findAll(ConstantsForCommands.LANGUAGE_RU);
        LOG.debug("testListRu: " + testListRu);

        LOG.debug("DataHelper.findPersonByName(testListEn,nameEn) " + DataHelper.findPersonByName(testListEn, nameEn));

        if (DataHelper.findPersonByName(testListEn, nameEn) || (DataHelper.findPersonByName(testListRu, nameRu))) {
            LOG.debug("Duplicated test found.");
            throw new AppException("Test with such name already exist");
        }

        Test test = new Test();
        test.setMinutesToComplite(minComplete);
        test.setDifficultyLevel(difficultyLevel);
        test = testService.insert(subjectId, test);
        test.setTestName(nameEn);
        testService.insertName(test, ConstantsForCommands.LANGUAGE_EN);
        test.setTestName(nameRu);
        testService.insertName(test, ConstantsForCommands.LANGUAGE_RU);

        List<Test> testList = testService.findAllByParent(subjectId, local);
        request.setAttribute("test", testList);
        request.setAttribute("subjectId", subjectId);

        return Path.PAGE_ADMIN_TEST_LIST;
    }
}
