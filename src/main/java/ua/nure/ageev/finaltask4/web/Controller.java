package ua.nure.ageev.finaltask4.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.web.command.Command;
import ua.nure.ageev.finaltask4.web.command.CommandContainer;

/**
 * Controller servlet.
 *
 * @author A.Ageev
 *
 */

public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2423353715955164816L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req, resp);
    }

    /**
     * Method delegates all request to other services
     *
     * @throws ServletException
     * @throws IOException
     */
    private void proccessRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  CommandContainer commandFactory = CommandContainer.commandFactory();
        Command command = CommandContainer.get(req);
        String page = command.execute(req);
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

}
