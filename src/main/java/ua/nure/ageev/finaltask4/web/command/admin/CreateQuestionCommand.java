package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateQuestionCommand extends Command {

    private static final long serialVersionUID = -1573456787599573283L;

    private static final Logger LOG = Logger.getLogger(CreateQuestionCommand.class);

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("CreateQuestionCommand starts");


        LOG.debug("CreateQuestionCommand finished");
        return Path.PAGE_ADMIN_CREATE_QUESTION;
    }
}
