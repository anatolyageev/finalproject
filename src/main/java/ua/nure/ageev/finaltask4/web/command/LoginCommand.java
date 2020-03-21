package ua.nure.ageev.finaltask4.web.command;

import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.repository.db.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        System.out.println("Fuk!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        DBManager manager = DBManager.getInstance();
        manager.printAllUsers();
        return null;
    }
}
