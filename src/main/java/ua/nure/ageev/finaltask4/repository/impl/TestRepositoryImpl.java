package ua.nure.ageev.finaltask4.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.exception.Messages;
import ua.nure.ageev.finaltask4.repository.TestRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;
import ua.nure.ageev.finaltask4.repository.db.Fields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestRepositoryImpl extends AbstractRepository implements TestRepository {

    protected static final Logger LOG = Logger.getLogger(TestRepositoryImpl.class);

    private static final String SQL_FIND_ALL_TEST = "SELECT t.id,tl.test_name, question_quantity, t.difficulty, min_to_complete, subject_id" +
            " FROM tests t, tests_locale tl, " +
            "(SELECT " +
            "ts.id id " +
            ",count(q.id) question_quantity " +
            "FROM tests ts, questions q " +
            "WHERE ts.id = q.test_id " +
            "group by ts.id) qq " +
            "WHERE t.id = tl.test_id AND tl.lang_ind = ? " +
            "AND t.id = qq.id";

    private static final String SQL_FIND_TEST_BY_ID = "SELECT t.id,tl.test_name, question_quantity, t.difficulty, min_to_complete, subject_id" +
            " FROM tests t, tests_locale tl, " +
            "(SELECT " +
            "ts.id id " +
            ",count(q.id) question_quantity " +
            "FROM tests ts, questions q " +
            "WHERE ts.id = q.test_id " +
            "group by ts.id) qq " +
            "WHERE t.id = tl.test_id " +
            "AND tl.lang_ind = ? AND t.id = ? AND t.id = qq.id";

    private static final String SQL_FIND_ALL_TEST_BY_PARENT = "SELECT t.id,tl.test_name, question_quantity, t.difficulty, min_to_complete, subject_id " +
            "FROM tests t, tests_locale tl, " +
            "(SELECT " +
            "t.id id " +
            ",count(q.id) question_quantity " +
            "FROM tests t, questions q " +
            "where t.id = q.test_id " +
            "group by t.id) qq " +
            "WHERE t.id = tl.test_id " +
            "AND t.id = qq.id "+
            "AND tl.lang_ind = ? AND t.subject_id = ?";

    private static final String SQL_DELETE_TEST = "DELETE FROM tests WHERE id = ?";

    private static final String SQL_DELETE_TEST_LOCALE = "DELETE FROM tests_locale WHERE test_id = ?";


    @Override
    public Test getOne(Long id, String locale) {
        Test test = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne by Subject for Subject.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_TEST_BY_ID);
            ps.setString(1,locale);
            ps.setLong(2,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                test = extractTest(rs);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getOne for Test returned --> " + test);
        return test;
    }



    @Override
    public Test update( Test test, String locale) {
        return null;
    }

    @Override
    public void delete(Long id) {
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("Repository impl method delete for Test.");

        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_DELETE_TEST_LOCALE);
            ps.setLong(1,id);
            ps.executeUpdate();
            ps = con.prepareStatement(SQL_DELETE_TEST);
            ps.setLong(1,id);
            ps.executeUpdate();
            con.commit();
        }catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con,ps);
        }
        LOG.trace("Repository method delete for Test finished;");
    }

    @Override
    public List<Test> findAll(String locale) {
        Test test = null;
        List<Test> testList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method findAll for Test.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_ALL_TEST);
            ps.setString(1,locale);
            rs = ps.executeQuery();
            while (rs.next()) {
                test = extractTest(rs);
                testList.add(test);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getAll for Subject returned --> " + test);
        return testList;
    }

    @Override
    public Test insert(Long parentId, Test test, String locale) {
        return null;
    }

    @Override
    public List<Test> findAllByParent(Long parentId, String locale) {
        Test test = null;
        List<Test> testList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method findAllByParent for Test.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_ALL_TEST_BY_PARENT);
            ps.setString(1,locale);
            ps.setLong(2,parentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                test = extractTest(rs);
                testList.add(test);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method findAllByParent for Test returned --> " + test);
        return testList;
    }

    @Override
    public void deleteAllByParentId(Long parentId) {

    }

    private Test extractTest(ResultSet rs) throws SQLException {
        Test test = new Test();
        test.setId(rs.getLong(Fields.ENTITY_ID));
        test.setDifficultyLevel(rs.getInt(Fields.TEST_DIFFICULTY_LEVEL));
        test.setTestName(rs.getString(Fields.TEST_NAME));
        test.setMinutesToComplite(rs.getInt(Fields.TEST_MINUTES_TO_COMPLETE));
        test.setQuestionQuantity(rs.getInt(Fields.TEST_QUESTION_QUANTITY));
        return test;
    }
}
