package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Role;
import ua.nure.ageev.finaltask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguageCommand extends Command {

    private static final long serialVersionUID = -157348456777573283L;

    private static final Logger LOG = Logger.getLogger(ChangeLanguageCommand.class);

    /**
     * Execution method for change language command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("LanguageChangeCommand starts");
        HttpSession session = ((HttpServletRequest) request).getSession();
        String lang = request.getParameter("lang");
        Role userRole = (Role) session.getAttribute("userRole");
        session.setAttribute("currentLocale", lang);
        LOG.debug("LanguageChangeCommand finished");
        if(userRole == Role.ADMIN){
            return Path.COMMAND_LIST_USERS;
        }else{
            return Path.PAGE_USER_PAGE;
        }
    }
}
