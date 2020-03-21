package ua.nure.ageev.finaltask4.repository.impl;

import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.repository.UserRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;
import ua.nure.ageev.finaltask4.repository.db.Fields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl extends AbstractRepository implements UserRepository {
//    protected static final Logger LOG = Logger.getLogger(UserRepositoryImpl.class);
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT \n" +
            "ua.id as id\n" +
            ",user_name \n" +
            ",password\n" +
            ",role_id\n" +
            ",first_name\n" +
            ",last_name\n" +
            "FROM user_account ua, users u \n" +
            "where u.account_id = ua.id AND ua.user_name = ?";


    @Override
    public User getOne(String login) {
        System.out.println("UserRepositoryImpl");
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
   //     LOG.trace("Repository impl method getOne --> " + login);
        try {
            con = manager.getConnection();
            System.out.println("after get connection");
            ps = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            System.out.println("after ps");
            ps.setString(1,login);
            System.out.println("after setString");
            rs = ps.executeQuery();
            System.out.println("after rs");
            while (rs.next()) {
                user = extractUser(rs);
            }
           // con.commit();
        } catch (SQLException | DBException ex) {
            System.out.println(ex);
   //         LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);

        } finally {
            manager.close(con, ps, rs);
        }
        System.out.println(user);
        return user;
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
        System.out.println(rs.getLong(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        System.out.println(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        return user;
    }
}
