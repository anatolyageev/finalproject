package ua.nure.ageev.finaltask4.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.exception.Messages;
import ua.nure.ageev.finaltask4.repository.AdminRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpl extends AbstractRepository implements AdminRepository {

    protected static final Logger LOG = Logger.getLogger(AdminRepositoryImpl.class);


    private static final String SQL_CHANGE_ROLE_ADMIN = "UPDATE user_account SET role_id = 0\n" +
            "WHERE id = ?";
    private static final String SQL_CHANGE_ROLE_USER = "UPDATE user_account SET role_id = 1\n" +
            "WHERE id = ?";
    private static final String SQL_CHANGE_STATUS_USER_ACTIVE = "UPDATE users SET user_status = 1\n" +
            "WHERE account_id = ?";
    private static final String SQL_CHANGE_STATUS_USER_NOT_ACTIVE = "UPDATE users SET user_status = 0\n" +
            "WHERE account_id = ?";


    /**
     * Change user role to Admin
     *
     * @param id
     */
    @Override
    public Integer setUserRoleAdmin(Long id) {
        Integer result = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection con = null;
            LOG.trace("Repository impl method getOne --> " + id);
            try {
                con = manager.getConnection();
                con.setAutoCommit(false);
                ps = con.prepareStatement(SQL_CHANGE_ROLE_ADMIN);
                ps.setLong(1,id);
                result = ps.executeUpdate();
                con.commit();
            } catch (SQLException | DBException ex) {
                LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
            } finally {
                manager.close(con, ps, rs);
            }
            LOG.trace("Repository method setUserRoleAdmin update rows --> " + result.toString());
            return result;
    }

    /**
     * Change user role to User
     *
     * @param id
     */
    @Override
    public Integer setUserRoleUser(Long id) {
        Integer result = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne --> " + id);
        try {
            con = manager.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_CHANGE_ROLE_USER);
            ps.setLong(1,id);
            result = ps.executeUpdate();
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method setUserRoleAdmin update rows --> " + result.toString());
        return result;
    }

    /**
     * Change user status active / not active
     *
     * @param id
     */
    @Override
    public Integer setUserStatusActive(Long id) {
        Integer result = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method setUserStatusActive --> " + id);
        try {
            con = manager.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_CHANGE_STATUS_USER_ACTIVE);
            ps.setLong(1,id);
            result = ps.executeUpdate();
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method setUserStatusActive update rows --> " + result.toString());
        return result;
    }

    @Override
    public Integer setUserStatusNotActive(Long id) {
        Integer result = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method setUserStatusNotActive --> " + id);
        try {
            con = manager.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_CHANGE_STATUS_USER_NOT_ACTIVE);
            ps.setLong(1,id);
            result = ps.executeUpdate();
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method setUserStatusNotActive update rows --> " + result.toString());
        return result;
    }

}
