package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Role;
import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.services.UserService;
import ua.nure.ageev.finaltask4.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class for login .
 *
 * @author A.Ageev
 *
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        LOG.debug("Login command starts;");

        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();

        // get login and pass from request
       // DBManager manager = DBManager.getInstance();
       // manager.printAllUsers();
        System.out.println("In LoginComang");
        // get login from requst
        String login = request.getParameter("login");
        System.out.println("Requst parametr: login --> " + login);
        LOG.trace("Requst parametr: login --> " + login);
        //get password from requst
        String password = request.getParameter("password");
        System.out.println("Requst parametr: password --> " + password);
        if (login == null || password == null || login.isEmpty() || login.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        User user = userService.getOne(login);
        LOG.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            throw new AppException("Cannot find user with such login/password");
        }

        Role userRole = Role.getRole(user);
        LOG.trace("userRole --> " + userRole);
        String forward = Path.PAGE_ERROR_PAGE;

        if (userRole == Role.ADMIN) {
            forward = Path.PAGE_ADMIN_PAGE;
        }

        if (userRole == Role.CLIENT) {
            forward = Path.PAGE_USER_PAGE;
        }

        session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: userRole --> " + userRole);

        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        LOG.debug("Command finished");
        return forward;
    }
}
