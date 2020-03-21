package ua.nure.ageev.finaltask4.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.web.command.Command;
import ua.nure.ageev.finaltask4.web.command.CommandContainer;

/**
 * Controller servlet.
 *
 * @author A.Ageev
 *
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            proccessRequest(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            proccessRequest(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method delegates all request to other services
     *
     * @throws ServletException
     * @throws IOException
     */
    private void proccessRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {

        LOG.debug("Controller starts");

        // extract command name from the request
        String commandName = req.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        //obtaine command object by its name
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtaned command: " + command);

        // execute command and get forward address
        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(req,resp);
        }catch (AppException ex) {
            req.setAttribute("errorMessage", ex.getMessage());
        }

        LOG.trace("Forward address --> " + forward);

        LOG.debug("Controller finished, now go to forward address --> " + forward);

        // go to forward
        req.getRequestDispatcher(forward).forward(req, resp);
    }

}
