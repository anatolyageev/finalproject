package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.domain.UserResult;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.UserResultRepositoryImpl;
import ua.nure.ageev.finaltask4.services.UserResultService;
import ua.nure.ageev.finaltask4.services.impl.UserResultServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTestFinishCommand extends Command {

    private static final long serialVersionUID = -1555444566799573283L;

    private static final Logger LOG = Logger.getLogger(UserTestFinishCommand.class);

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("UserTestFinishCommand starts");
        UserResult userResult = new UserResult();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute("currentLocale");
        LOG.debug("UserAnswerCommand get locale: " + local);
        if (local == null) {
            local = "en";
        }
        LOG.debug("UserAnswerCommand get locale after if: " + local);
        Test currentTest = (Test) session.getAttribute("currentTest");
        User user = (User) session.getAttribute("user");
        Map<Long, Boolean> mapAnswer = (HashMap) session.getAttribute("mapAnswer");
        LOG.debug("UserAnswerCommand get mapAnswer : " + mapAnswer);
        UserResultService userResultService = new UserResultServiceImpl(new UserResultRepositoryImpl());

        userResult.setEvaluation(calculateUserResult(currentTest, mapAnswer));
        userResult.setUserId(user.getId());
        userResult.setTestId(currentTest.getId());

        LOG.debug("UserTestFinishCommand get userResult : " + userResult);

        userResult = userResultService.insert(user.getId(), userResult);

        LOG.debug("UserTestFinishCommand get questionList : " + userResult);

        List<UserResult> userResultList = userResultService.findAllByParent(user.getId(), local);
        LOG.debug("UserTestFinishCommand get questionList : " + userResultList);
        session.setAttribute("userResultList", userResultList);

        return Path.PAGE_USER_PAGE;
    }

    private Integer calculateUserResult(Test currentTest, Map<Long, Boolean> mapAnswer) {
        Integer count = Math.toIntExact(mapAnswer.entrySet().stream().filter(x -> x.getValue()).count());
        LOG.debug("UserTestFinishCommand get count of correct answers : " + count);
        LOG.debug("UserTestFinishCommand get count of number of questions : " + currentTest.getQuestionQuantity());
        Long evaluation = Math.round(100 * ((double) count / currentTest.getQuestionQuantity()));

        return Math.round(evaluation);
    }
}
