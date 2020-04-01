package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToUserPage extends Command {
    private static final long serialVersionUID = -3071536563627692473L;
    private static final Logger LOG = Logger.getLogger(GoToUserPage.class);


    /**
     * Execution method for GoToUserPage command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command GoToUserPage starts");

        LOG.debug("Command  GoToUserPage finished");
        return Path.PAGE_USER_PAGE;
    }
}
