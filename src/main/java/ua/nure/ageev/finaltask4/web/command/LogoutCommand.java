package ua.nure.ageev.finaltask4.web.command;


import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class for the Command pattern implementation.
 *
 * @author A.Ageev
 *
 */
public class LogoutCommand extends Command{

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("LogoutCommand starts");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            LOG.debug("LogoutCommand invalidate session");
        }

        LOG.debug("LogoutCommand finished");
        return Path.PAGE_LOGIN;
    }
}
