package ua.nure.ageev.finaltask4.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Main class for the Command pattern implementation.
 *
 * @author A.Ageev
 *
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 8879403039606311780L;

    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    public abstract String execute(HttpServletRequest request);

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
