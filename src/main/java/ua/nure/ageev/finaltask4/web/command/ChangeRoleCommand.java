package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.AdminRepositoryImpl;
import ua.nure.ageev.finaltask4.services.AdminService;
import ua.nure.ageev.finaltask4.services.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeRoleCommand extends Command {

    private static final long serialVersionUID = -1573481565177573283L;

    private static final Logger LOG = Logger.getLogger(ChangeRoleCommand.class);

    /**
     * Execution method for ChangeRoleCommand.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("ChangeRoleCommand starts");
        AdminService adminService = new AdminServiceImpl(new AdminRepositoryImpl());
        Long userId = Long.parseLong(request.getParameter("user_id"));
        Integer currentRole = Integer.parseInt(request.getParameter("user_current_role"));
        if (currentRole == 1) {
            adminService.setUserRoleAdmin(userId);
        } else {
            adminService.setUserRoleUser(userId);
        }

        LOG.debug("Set the requested role to user id --> " + userId);
        LOG.debug("ChangeRoleCommand finished");
        return Path.COMMAND_LIST_USERS;
    }
}
