package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.services.UserService;
import ua.nure.ageev.finaltask4.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends Command{

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);



    /**
     * Execution method for registration command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("RegistrationCommand starts");
        UserService userService = new UserServiceImpl();
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");
        User user = userService.getOne(login);

        if(userService.getOne(login).getLogin()!=null){
            String errorMsg = "User alredy exist!";
            request.setAttribute("reg_error", errorMsg);
            LOG.debug(errorMsg);
            return Path.PAGE_LOGIN;
        }

        if (login == null || password == null || login.isEmpty() || login.isEmpty()) {
            String errorMsg = "Login/password cannot be empty";
            request.setAttribute("reg_error", errorMsg);
            LOG.debug(errorMsg);
            return Path.PAGE_LOGIN;
        }

        if (name == null || lastName == null || name.isEmpty() || lastName.isEmpty()) {
            String errorMsg = "Name/Last name cannot be empty";
            request.setAttribute("reg_error", errorMsg);
            LOG.debug(errorMsg);
            return Path.PAGE_LOGIN;
        }

        if (!name.matches( "(^[А-ЯЁ][а-яё]+)|(^[A-Z][a-z]+)") || !lastName.matches( "(^[А-ЯЁ][а-яё]+)|(^[A-Z][a-z]+)")) {
            String errorMsg = "Name/Last name should begin with capital letter and not contains digit";
            request.setAttribute("reg_error", errorMsg);
            LOG.debug(errorMsg);
            return Path.PAGE_LOGIN;
        }


        LOG.debug(login);
        LOG.debug(name);
        LOG.debug(lastName);
        LOG.debug(password);

        user =new User();
        user.setLogin(login);
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setPassword(password);

        userService.createUser(user);

        LOG.debug("RegistrationCommand finished");
        return Path.PAGE_USER_PAGE;
    }
}
