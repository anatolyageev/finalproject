package ua.nure.ageev.finaltask4.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.UserResult;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.exception.Messages;
import ua.nure.ageev.finaltask4.repository.UserResultRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;
import ua.nure.ageev.finaltask4.repository.db.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserResultRepositoryImpl extends AbstractRepository implements UserResultRepository {

    protected static final Logger LOG = Logger.getLogger(UserResultRepositoryImpl.class);

    private static final String SQL_FIND_ALL_RESULT = "SELECT * FROM users_results";

    private static final String SQL_FIND_RESULT_BY_ID = "SELECT * FROM users_results t WHERE t.id = ?";

    private static final String SQL_FIND_ALL_RESULT_BY_PARENT = "SELECT * FROM users_results t, users u, tests tt, tests_locale tl  " +
            "WHERE t.user_id = u.id " +
            "AND t.test_id = tt.id " +
            "AND tt.id = tl.test_id " +
            "AND tl.lang_ind = ? "+
            "AND u.id = ?";

    private static final String SQL_INSERT_RESULT_BY_PARENT = "INSERT INTO `quizdb`.`users_results`" +
            " (`user_id`, `test_id`, `evaluation`, `date_evaluation`) VALUES (?, ?, ?, DEFAULT)";

    @Override
    public UserResult getOne(Long id) {
        return null;
    }

    @Override
    public UserResult update(Long id, UserResult userResult) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserResult> findAll() {
        return null;
    }


    public UserResult insert(Long parentId, UserResult userResult) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method insert by id for UserResult.");
        try {
            con = manager.getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            ps = con.prepareStatement(SQL_INSERT_RESULT_BY_PARENT, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,parentId);
            ps.setLong(2,userResult.getTestId());
            ps.setInt(3,userResult.getEvaluation());
            if(ps.executeUpdate()>0){
                rs=ps.getGeneratedKeys();
                if(rs.next()){
                    userResult.setId(rs.getLong(1));
                   // LOG.trace("Repository impl method insert by id getDate: " + rs.getDate("date_evaluation"));
                }
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method insert for UserResult returned --> " + userResult);
        return userResult;
    }

    @Override
    public UserResult insert(Long parentId, UserResult userResult, String s) {
        return null;
    }

    @Override
    public List<UserResult> findAllByParent(Long parentId, String locale) {
        List<UserResult> userResultList = new ArrayList<>();
        UserResult userResult = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method findAllByParent by id for UserResult.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_ALL_RESULT_BY_PARENT);
            ps.setString(1,locale);
            ps.setLong(2,parentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                userResult = extractUserResult(rs);
                userResultList.add(userResult);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method findAllByParent for UserResult returned --> " + userResultList);
        return userResultList;
    }


    @Override
    public void deleteAllByParentId(Long parentId) {

    }

    private UserResult extractUserResult(ResultSet rs) throws SQLException {
        UserResult userResult = new UserResult();
        userResult.setId(rs.getLong(Fields.ENTITY_ID));
        userResult.setUserId(rs.getLong(Fields.USER_RESULT_USER_ID));
        userResult.setTestId(rs.getLong(Fields.USER_RESULT_TEST_ID));
        userResult.setEvaluation(rs.getInt(Fields.USER_RESULT_EVALUATION));
        userResult.setEvaluationDate(rs.getDate(Fields.USER_RESULT_DATE_EVALUATION));
        userResult.setTestName(rs.getString(Fields.TEST_NAME));
        return userResult;
    }

}
