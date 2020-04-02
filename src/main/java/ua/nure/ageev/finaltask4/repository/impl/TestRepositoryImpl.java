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

    private static final String SQL_FIND_ALL_TEST = "SELECT * FROM tests s, tests_locale sl " +
            "WHERE s.id = sl.test_id AND sl.lang_ind = ?";

    private static final String SQL_FIND_TEST_BY_ID = "SELECT * FROM tests t, tests_locale tl " +
            "WHERE t.id = tl.test_id " +
            "AND tl.lang_ind = ? AND t.id = ?";

    private static final String SQL_FIND_ALL_TEST_BY_PARENT = "SELECT * FROM tests t, tests_locale tl " +
            "WHERE t.id = tl.test_id " +
            "AND tl.lang_ind = ? AND t.subject_id = ?";


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
        LOG.trace("Repository method getAll for Subject returned --> " + test);
        return test;
    }



    @Override
    public Test update(Long id, Test test, String locale) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Test> findAll(String locale) {
        Test test = null;
        List<Test> testList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method findAll for Subject.");
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
        LOG.trace("Repository impl method findAllByParent for Subject.");
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
        LOG.trace("Repository method getAll for Subject returned --> " + test);
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