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

public class ChangeUserStatusCommand extends Command{

    private static final long serialVersionUID = -1573481555177573283L;

    private static final Logger LOG = Logger.getLogger(ChangeRoleCommand.class);

    /**
     * Execution method for ChangeUserStatusCommand.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("ChangeUserStatusCommand starts");
        AdminService adminService = new AdminServiceImpl(new AdminRepositoryImpl());
        Long userId = Long.parseLong(request.getParameter("user_id"));
        boolean currentStatus = Boolean.parseBoolean(request.getParameter("user_current_status"));
        if (!currentStatus){
            adminService.setUserStatusActive(userId);
        }else{
            adminService.setUserStatusNotActive(userId);
        }

        LOG.debug("Set the requested status to user id --> " + userId);

        LOG.debug("ChangeUserStatusCommand finished");
        return Path.COMMAND_LIST_USERS;
    }
}
