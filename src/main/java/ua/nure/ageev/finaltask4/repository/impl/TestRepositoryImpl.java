package ua.nure.ageev.finaltask4.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.exception.Messages;
import ua.nure.ageev.finaltask4.repository.TestRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;
import ua.nure.ageev.finaltask4.repository.db.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestRepositoryImpl extends AbstractRepository implements TestRepository {

    protected static final Logger LOG = Logger.getLogger(TestRepositoryImpl.class);

    private static final String SQL_FIND_ALL_TEST = "SELECT t.id,tl.test_name, " +
            "(SELECT  count(*) FROM questions q where q.test_id = t.id) question_quantity, t.difficulty, min_to_complete, subject_id" +
            " FROM tests t, tests_locale tl " +
            "WHERE t.id = tl.test_id AND tl.lang_ind = ?";

    private static final String SQL_FIND_TEST_BY_ID = "SELECT t.id,tl.test_name, " +
            "(SELECT  count(*) FROM questions q where q.test_id = t.id) question_quantity, t.difficulty, min_to_complete, subject_id" +
            " FROM tests t, tests_locale tl " +
            "WHERE t.id = tl.test_id " +
            "AND tl.lang_ind = ? AND t.id = ?";

    private static final String SQL_FIND_ALL_TEST_BY_PARENT = "SELECT t.id,tl.test_name, " +
            "(SELECT  count(*) FROM questions q where q.test_id = t.id) question_quantity, t.difficulty, min_to_complete, subject_id " +
            "FROM tests t, tests_locale tl " +
            "WHERE t.id = tl.test_id " +
            "AND tl.lang_ind = ? AND t.subject_id = ?";

    private static final String SQL_INSERT_TEST = "INSERT  INTO tests " +
            "(difficulty, min_to_complete, subject_id) " +
            "VALUES (?,?,?)";

    private static final String SQL_INSERT_TEST_NAME = "INSERT  INTO tests_locale " +
            "(test_id, lang_ind, test_name) " +
            "VALUES (?,?,?)";

    private static final String SQL_DELETE_TEST = "DELETE FROM tests WHERE id = ?";

    private static final String SQL_DELETE_TEST_LOCALE = "DELETE FROM tests_locale WHERE test_id = ?";

    private static final String SQL_UPDATE_TEST = "UPDATE tests " +
            "SET difficulty = ?, " +
            "min_to_complete = ? " +
            "WHERE id = ?";
    private static final String SQL_UPDATE_TEST_NAME = "UPDATE tests_locale " +
            "SET test_name = ? " +
            "WHERE test_id = ? " +
            "AND lang_ind = ?";


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
            ps.setString(1, locale);
            ps.setLong(2, id);
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
    public Test update(Test test, String locale) {
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("Repository impl method update for Test.");
        try {
            con = manager.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_UPDATE_TEST);
            ps.setInt(1, test.getDifficultyLevel());
            ps.setInt(2, test.getMinutesToComplite());
            ps.setLong(3,test.getId());
            LOG.trace("TestId: " + test.getId());
            LOG.trace("Test: " + test);
            int result = ps.executeUpdate();
            LOG.trace("Rows affected: " + result);
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps);
        }
        LOG.trace("Repository method update for Test finished;");
        return test;
    }

    public Test updateName(Test test, String locale) {
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("Repository impl method updateName for Test.");
        try {
            con = manager.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_UPDATE_TEST_NAME);
            ps.setString(1, test.getTestName());
            ps.setLong(2, test.getId());
            ps.setString(3, locale);
            ps.executeUpdate();
            LOG.trace("TestId: " + test.getId());
            LOG.trace("Test: " + test);
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps);
        }
        LOG.trace("Repository method updateName for Test finished;");
        return test;
    }

    @Override
    public void delete(Long id) {
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("Repository impl method delete for Test.");

        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_DELETE_TEST_LOCALE);
            ps.setLong(1, id);
            ps.executeUpdate();
            ps = con.prepareStatement(SQL_DELETE_TEST);
            ps.setLong(1, id);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps);
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
            ps.setString(1, locale);
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
    public Test insert(Long parentId, Test test) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method insert for Test.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_INSERT_TEST, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, test.getDifficultyLevel());
            ps.setInt(2, test.getMinutesToComplite());
            ps.setLong(3, parentId);
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    Long testId = rs.getLong(1);
                    test.setId(testId);
                }
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method insert for Test finished;");
        return test;
    }

    public Test insertName(Test test, String locale) {
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("Repository impl method insert for Test.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_INSERT_TEST_NAME);
            ps.setLong(1, test.getId());
            ps.setString(2, locale);
            ps.setString(3, test.getTestName());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps);
        }
        LOG.trace("Repository method delete for insert finished;");
        return test;
    }

    @Override
    public Test insert(Long parentId, Test test, String s) {
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
            LOG.trace("Repository impl method findAllByParent for Test locale: " + locale);
            LOG.trace("Repository impl method findAllByParent for Test parentId: " + parentId);
            ps.setString(1, locale);
            ps.setLong(2, parentId);
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
