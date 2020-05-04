package ua.nure.ageev.finaltask4.repository.base;

import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.repository.db.DBManager;

/**
 * AbstractRepository.
 *
 * @author A.Ageev
 */
public abstract class AbstractRepository {

    protected DBManager manager;

    {
        try {
            manager = DBManager.getInstance();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
