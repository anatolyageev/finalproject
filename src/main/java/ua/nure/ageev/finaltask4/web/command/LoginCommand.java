package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Role;
import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.domain.UserResult;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.UserResultRepositoryImpl;
import ua.nure.ageev.finaltask4.security.VerifyProvidedPassword;
import ua.nure.ageev.finaltask4.services.UserResultService;
import ua.nure.ageev.finaltask4.services.UserService;
import ua.nure.ageev.finaltask4.services.impl.UserResultServiceImpl;
import ua.nure.ageev.finaltask4.services.impl.UserServiceImpl;
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Class for login .
 *
 * @author A.Ageev
 */
public class LoginCommand extends Command {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException, ServletException, IOException {

        LOG.debug("Login command starts;");

        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();
        UserResult userResult = new UserResult();
        String login = request.getParameter("login");



        UserResultService userResultService = new UserResultServiceImpl(new UserResultRepositoryImpl());
        LOG.trace("Requst parametr: login --> " + login);


        String path = request.getContextPath();
        String password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        User user = userService.getOne(login);
        LOG.trace("Found in DB: user --> " + user);

        if (user == null || !VerifyProvidedPassword.isPasswordCorrect(password,user)) {
            throw new AppException("Cannot find user with such login/password");
        }

        if (!user.getUserStatus()) {
            throw new AppException("User was blocked by administration. Pleas send mail to admin@admin.com");
        }

        Role userRole = Role.getRole(user);
        LOG.trace("userRole --> " + userRole);
        String forward = Path.PAGE_ERROR_PAGE;

        if (userRole == Role.ADMIN) {
            List<User> userList = DataHelper.getUsersList();
            // put user list to request
            request.setAttribute("userList", userList);
            forward = path+Path.COMMAND_LIST_USERS;
        }
        String local = (String) session.getAttribute("currentLocale");
        if (userRole == Role.CLIENT) {
           // path = request.getContextPath() + "/controller?command=goToUserPageCommand";
            List<UserResult> userResultList = userResultService.findAllByParent(user.getId(), local);
            LOG.debug("UserTestFinishCommand get questionList : " + userResultList);
            session.setAttribute("userResultList", userResultList);
            forward = Path.PAGE_USER_PAGE;

        }

        response.setHeader("Request URL", path);

        LOG.trace("Set the session attribute: user --> " + path);

        session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: userRole --> " + userRole);

        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        LOG.debug("Command finished");

        return forward;
    }
}
