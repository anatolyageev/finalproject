package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminPageChange extends Command {

    private static final long serialVersionUID = -1555447866799343283L;

    private static final Logger LOG = Logger.getLogger(AdminPageChange.class);

    /**
     * Execution method for change page for admin question command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("AdminPageChange starts");
        Integer pgeNo;
        HttpSession session = request.getSession();

        if (session.getAttribute("pageId") == null) {
            session.setAttribute("pageId", 0);
            pgeNo = 0;
        } else {
            pgeNo = Integer.parseInt(request.getParameter("page"));
        }
        LOG.debug("AdminPageChange get pageNo: " + pgeNo);
        session.setAttribute("pageId", pgeNo);
        LOG.debug("AdminPageChange get pageNo: " + pgeNo);
        return Path.PAGE_ADMIN_QUESTIONS;
    }
}
