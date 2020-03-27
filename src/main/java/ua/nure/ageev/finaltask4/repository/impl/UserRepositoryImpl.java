package ua.nure.ageev.finaltask4.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.exception.Messages;
import ua.nure.ageev.finaltask4.repository.UserRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;
import ua.nure.ageev.finaltask4.repository.db.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl extends AbstractRepository implements UserRepository {
    protected static final Logger LOG = Logger.getLogger(UserRepositoryImpl.class);
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT \n" +
            "ua.id as id\n" +
            ",user_name \n" +
            ",password\n" +
            ",role_id\n" +
            ",first_name\n" +
            ",last_name\n" +
            "FROM user_account ua, users u \n" +
            "where u.account_id = ua.id AND ua.user_name = ?";

    private static final String SQL_FIND_USER_BY_ID = "SELECT \n" +
            "ua.id as id\n" +
            ",user_name \n" +
            ",password\n" +
            ",role_id\n" +
            ",first_name\n" +
            ",last_name\n" +
            "FROM user_account ua, users u \n" +
            "where u.account_id = ua.id AND ua.id = ?";

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM user_account ua, users u WHERE u.account_id = ua.id";

    @Override
    public User getOne(String login) {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne --> " + login);
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            ps.setString(1,login);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            System.out.println(ex);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);

        } finally {
            manager.close(con, ps, rs);
        }
        System.out.println(user);
        return user;
    }

    /**
     * Returns a user with the given identifier.
     *
     * @param id User identifier.
     * @return User entity.
     */
    @Override
    public User getOne(long id) {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne --> " + id);
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_USER_BY_ID);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            System.out.println(ex);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);

        } finally {
            manager.close(con, ps, rs);
        }
        System.out.println(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        User user = null;
        List<User> usersList = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getAllUsers.");
        try {
            con = manager.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_FIND_ALL_USERS);
            while (rs.next()) {
                user = extractUser(rs);
                usersList.add(user);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            System.out.println(ex);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);

        } finally {
            manager.close(con, st, rs);
        }
        System.out.println(usersList);
        return usersList;
    }


    /**
     * Extracts a user entity from the result set.
     *
     * @param rs
     *            Result set from which a user entity will be extracted.
     * @return User entity
     */
    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        return user;
    }
}
