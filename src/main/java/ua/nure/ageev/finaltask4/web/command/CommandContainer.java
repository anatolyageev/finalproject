package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
//        commands.put("logout", new LogoutCommand());
//        commands.put("viewSettings", new ViewSettingsCommand());
//        commands.put("noCommand", new NoCommand());
//
//        // client commands
//        commands.put("listMenu", new ListMenuCommand());
//
//        // admin commands
//        commands.put("listOrders", new ListOrdersCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param request
     *            HttpServletRequest.
     * @return Command object.
     */
    public static Command get(HttpServletRequest request) {
        String action = request.getParameter("action");
        if (action == null || !commands.containsKey(action)) {
            LOG.trace("Command not found, name --> " + action);
            return commands.get("noCommand");
        }
        return commands.get(action);
    }
}
