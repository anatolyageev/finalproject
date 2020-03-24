package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class for the NoCommand.
 *
 * @author A.Ageev
 *
 */
public class NoCommand extends Command {

    private static final long serialVersionUID = -2785976616686657267L;

    private static final Logger LOG = Logger.getLogger(NoCommand.class);
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
            LOG.debug("NoCommand starts");

            String errorMessage = "No such command";
            request.setAttribute("errorMessage", errorMessage);
            LOG.error("Set the request attribute: errorMessage --> " + errorMessage);

            LOG.debug("NoCommand finished");
            return Path.PAGE_ERROR_PAGE;
    }
}
