package ua.nure.ageev.finaltask4.web.filter;


import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * Filter which to distribute access to commands depending on access level.
 *
 * @author Anatoliy Ageev
 */

//@WebFilter(
//        filterName = "CommandAccessFilter",
//        urlPatterns = {"/controller/*"}
//)
public class CommandAccessFilter  implements Filter {

    private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);

    /**
     * access for client to remove
     */
    private final Set<String> accessibleCommands;
    /**
     * accessible any logged in users
     */
    private final Set<String> commonCommands;
    /**
     * accessible commands for client
     */
    private final Set<String> clientCommands;
    /**
     * accessible commands for admin
     */
    private final Set<String> adminCommands;


    /**
     * Default constructor.
     */
    public CommandAccessFilter() {

        accessibleCommands = new HashSet<>();
        commonCommands = new HashSet<>();
        clientCommands = new HashSet<>();
        adminCommands = new HashSet<>();

        // common commands
        accessibleCommands.add("login");
        accessibleCommands.add("logout");
        accessibleCommands.add("registration");
        accessibleCommands.add("noCommand");
        accessibleCommands.add("viewSettings");
        accessibleCommands.add("changeLanguageCommand");

        // client commands
        clientCommands.add("goToUserPageCommand");
        clientCommands.add("quizzeTestCommand");
        clientCommands.add("quizzeQuestionCommand");
        clientCommands.add("quizzeAnswerCommand");
        clientCommands.add("userAnswerCommand");
        clientCommands.add("userPageChange");
        clientCommands.add("userTestFinishCommand");
        clientCommands.add("quizzeCommand");
        clientCommands.add("quizzeTestSortCommand");
        clientCommands.add("subjectCommand");
        clientCommands.add("userTestCommand");

        // admin commands
        adminCommands.add("adminQuizzeCommand");
        adminCommands.add("createSubjectCommand");
        adminCommands.add("newSubjectCommand");
        adminCommands.add("deleteSubjectCommand");
        adminCommands.add("editSubjectCommand");
        adminCommands.add("updateSubjectCommand");
        adminCommands.add("testsListCommand");
        adminCommands.add("createTestCommand");
        adminCommands.add("newTestCommand");
        adminCommands.add("deleteTestCommand");
        adminCommands.add("editTestCommand");
        adminCommands.add("updateTestCommand");
        adminCommands.add("questionCommand");
        adminCommands.add("adminPageChange");
        adminCommands.add("createQuestionCommand");
        adminCommands.add("newQuestionCommand");
        adminCommands.add("updateAnswerCommand");
        adminCommands.add("editAnswerCommand");
        adminCommands.add("deleteQuestionCommand");
        adminCommands.add("editQuestionCommand");
        adminCommands.add("updateQuestionCommand");
        adminCommands.add("adminPageCommand");
        adminCommands.add("listUsersCommand");
        adminCommands.add("changeRoleCommand");
        adminCommands.add("changeUserStatusCommand");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Start initializing filter: " + CommandAccessFilter.class.getSimpleName());

        LOG.debug("Finished initializing filter: " + CommandAccessFilter.class.getSimpleName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String command = httpRequest.getParameter("command");

        if (accessibleCommands.contains(command)) {
            LOG.debug("This command can be accessed by all users: " + command);
          chain.doFilter(httpRequest, httpResponse);

        } else {
            LOG.debug("This command can be accessed only by logged in users: " + command);
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                LOG.debug("Unauthorized access to resource. Client is not logged-in.");
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                String userRole = ((Role) session.getAttribute("userRole")).getName().toLowerCase();
                LOG.debug("Command is specific to user. Check user role.");
                LOG.debug("Check user role." + userRole);
                if ("client".equals(userRole) && clientCommands.contains(command)) {
                    LOG.debug("User is client. Command can be executed by client: " + command);
                    chain.doFilter(httpRequest, httpResponse);

                } else if ("admin".equals(userRole) && adminCommands.contains(command)) {
                    LOG.debug("User is admin. Command can be executed by admin: " + command);
                    chain.doFilter(httpRequest, httpResponse);

                } else {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }

    @Override
    public void destroy() {
        LOG.debug("Start filter destroy: " + CommandAccessFilter.class.getSimpleName());
        // do nothing
        LOG.debug("Finished filter destroy: " + CommandAccessFilter.class.getSimpleName());
    }
}
