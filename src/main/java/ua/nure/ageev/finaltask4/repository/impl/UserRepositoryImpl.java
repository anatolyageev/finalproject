package ua.nure.ageev.finaltask4.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.exception.Messages;
import ua.nure.ageev.finaltask4.repository.UserRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;
import ua.nure.ageev.finaltask4.repository.db.Fields;
import ua.nure.ageev.finaltask4.security.SecurePassword;

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
            ",user_status\n" +
            ",first_name\n" +
            ",last_name\n" +
            "FROM user_account ua, users u \n" +
            "where u.account_id = ua.id AND ua.user_name = ?";

    private static final String SQL_FIND_USER_BY_ID = "SELECT \n" +
            "ua.id as id\n" +
            ",user_name \n" +
            ",password\n" +
            ",role_id\n" +
            ",user_status\n" +
            ",first_name\n" +
            ",last_name\n" +
            "FROM user_account ua, users u \n" +
            "where u.account_id = ua.id AND ua.id = ?";

    private static final String SQL_FIND_USER_SALT = "SELECT password_salt " +
            "FROM user_account ua " +
            "where ua.id = ?";

    private static final String SQL_FIND_USER_HASH_ALGORITHM = "SELECT password_hash_algorithm " +
            "FROM user_account ua " +
            "where ua.id = ?";

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM user_account ua, users u WHERE u.account_id = ua.id";

    private static final String SQL_INSERT_NEW_USER_ACCOUNT_INFO = "INSERT INTO user_account " +
            "(user_name, password, password_salt, password_hash_algorithm,role_id) " +
            "VALUES(?, ?, ?, ? ,1)";

    private static final String SQL_INSERT_NEW_USER = "INSERT INTO users " +
            "(first_name, last_name,account_id) " +
            "VALUES(?, ? ,?)";


    @Override
    public User getOne(String login) {
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne --> " + login);
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getOne returned --> " + user);
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
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getOne by id returned --> " + user);
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
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, st, rs);
        }
        LOG.trace("Repository method getAll returned --> " + usersList);
        return usersList;
    }

    /**
     * Add User to database and returns a user.
     *
     * @param user@return User entity.
     */
    @Override
    public User createUser(User user) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method createUser --> " + user);
        String salt = SecurePassword.getSalt();
        LOG.trace("Repository impl method createUser salt --> " + salt);
        String securePassword = SecurePassword.getSecurePassword(user.getPassword(), salt);
        try {
            con = manager.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_INSERT_NEW_USER_ACCOUNT_INFO, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            ps.setString(2, securePassword);
            ps.setString(3, salt);
            ps.setString(4, SecurePassword.getHashing());
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    Long userId = rs.getLong(1);
                    user.setId(userId);
                }
            }
            LOG.debug("Repository method first update  --> " + user);
            ps = con.prepareStatement(SQL_INSERT_NEW_USER);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setLong(3, user.getId());
            if (ps.executeUpdate() > 0) {
                con.commit();
                LOG.debug("Repository method committed createUser  --> " + user);
            }
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.debug("Repository method createUser  --> " + user);
        return user;
    }

    /**
     * Returns a user salt for password.
     *
     * @param id User identifier.
     * @return String .
     */
    @Override
    public String getSalt(Long id) {
        String salt = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getSalt --> " + id);
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_USER_SALT);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                salt = rs.getString(Fields.USER_PASSWORD_SALT);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getSalt by id returned --> " + salt);
        return salt;
    }

    /**
     * Returns a user hash algorithm for password.
     *
     * @param id User identifier.
     * @return String .
     */
    @Override
    public String getHashAlgorithm(Long id) {
        String hashAlgorithm = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getHashAlgorithm --> " + id);
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_USER_HASH_ALGORITHM);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                hashAlgorithm = rs.getString(Fields.USER_PASSWORD_HASH_ALGORITHM);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getHashAlgorithm by id returned --> " + hashAlgorithm);
        return hashAlgorithm;
    }


    /**
     * Extracts a user entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return User entity
     */
    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setUserStatus(rs.getBoolean(Fields.USER_STATUS));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        return user;
    }
}
