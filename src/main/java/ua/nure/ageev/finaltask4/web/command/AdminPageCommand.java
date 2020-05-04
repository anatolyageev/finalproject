package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminPageCommand extends Command {

    private static final long serialVersionUID = 34L;
    private static final Logger LOG = Logger.getLogger(AdminPageCommand.class);

    /**
     * Execution method for AdminPageCommand command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command  AdminPageCommand starts");
        List<User> userList = DataHelper.getUsersList();
        // put user list to request
        request.setAttribute("userList", userList);
        LOG.debug("Set the request attribute: userList --> " + userList);
        LOG.debug("Command AdminPageCommand finished");
        return Path.PAGE_USER_USER_LIST;
    }
}
