package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.domain.UserResult;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.UserResultRepositoryImpl;
import ua.nure.ageev.finaltask4.services.UserResultService;
import ua.nure.ageev.finaltask4.services.UserService;
import ua.nure.ageev.finaltask4.services.impl.UserResultServiceImpl;
import ua.nure.ageev.finaltask4.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToUserPageCommand extends Command {
    private static final long serialVersionUID = -3071536563627692473L;
    private static final Logger LOG = Logger.getLogger(GoToUserPageCommand.class);


    /**
     * Execution method for GoToUserPageCommand command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command GoToUserPageCommand starts");

        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();
        UserResult userResult = new UserResult();
        UserResultService userResultService = new UserResultServiceImpl(new UserResultRepositoryImpl());
        String local = (String) session.getAttribute("currentLocale");
        User user = (User)session.getAttribute("user");
        List<UserResult> userResultList = userResultService.findAllByParent(user.getId(),local);
        LOG.debug("UserTestFinishCommand get questionList : " + userResultList);
        session.setAttribute("userResultList",userResultList);

        LOG.debug("Command  GoToUserPageCommand finished");
        return Path.PAGE_USER_PAGE;
    }
}
