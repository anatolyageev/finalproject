package ua.nure.ageev.finaltask4.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.exception.Messages;
import ua.nure.ageev.finaltask4.repository.QuestionRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;
import ua.nure.ageev.finaltask4.repository.db.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl extends AbstractRepository implements QuestionRepository {

    protected static final Logger LOG = Logger.getLogger(QuestionRepositoryImpl.class);

    private static final String SQL_FIND_ALL_QUESTION = "SELECT * FROM tests s, tests_locale sl " +
            "WHERE s.id = sl.test_id AND sl.lang_ind = ?";

    private static final String SQL_FIND_QUESTION_BY_ID = "SELECT * FROM questions t, questions_locale tl " +
            "WHERE t.id = tl.question_id " +
            "AND tl.lang_ind = ? AND t.id = ?";

    private static final String SQL_FIND_ALL_QUESTION_BY_PARENT = "SELECT * FROM questions t, questions_locale tl " +
            "WHERE t.id = tl.question_id " +
            "AND tl.lang_ind = ? AND t.test_id = ?";

    private static final String SQL_DELETE_QUESTION = "DELETE FROM questions WHERE id = ?";

    private static final String SQL_DELETE_QUESTION_LOCALE = "DELETE FROM tests_locale WHERE test_id = ?";

    private static final String SQL_QUESTION_INSERT_BY_PARENT = "INSERT  INTO questions " +
            "(test_id) " +
            "VALUES (?)";

    private static final String SQL_QUESTION_INSERT_NAME = "INSERT  INTO questions_locale " +
            "(question_id, lang_ind, question_text) " +
            "VALUES (?,?,?)";

    private static final String SQL_UPDATE_QUESTION_TEXT = "UPDATE questions_locale " +
            "SET question_text = ? " +
            "WHERE question_id = ? " +
            "AND lang_ind = ?";

    @Override
    public Question getOne(Long id, String locale) {
        Question question = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne by id for Question.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_QUESTION_BY_ID);
            ps.setString(1,locale);
            ps.setLong(2,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                question = extractQuestion(rs);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getOne for Question returned --> " + question);
        return question;
    }

    @Override
    public Question update(Question question, String locale) {
        return null;
    }

    @Override
    public void delete(Long id) {
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("Repository impl method delete for Question.");

        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_DELETE_QUESTION);
            ps.setLong(1, id);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps);
        }
        LOG.trace("Repository method delete for Question finished;");
    }

    @Override
    public List<Question> findAll(String locale) {
        return null;
    }

    @Override
    public Question insert(Long parentId, Question question) {
        Question resultQuestion = new Question();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method insert by parentId for Question.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_QUESTION_INSERT_BY_PARENT, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,parentId);
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    resultQuestion.setId(rs.getLong(1));
                }
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method insert by parentId for Question returned --> " + resultQuestion);
        return resultQuestion;
    }

    @Override
    public Question insertName(Question question, String locale) {
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("Repository impl method insertName for Question.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_QUESTION_INSERT_NAME);
            ps.setLong(1,question.getId());
            ps.setString(2,locale);
            ps.setString(3,question.getQuestionText());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps);
        }
        LOG.trace("Repository method insertName for Question returned --> " + question);
        return question;
    }

    @Override
    public Question updateName(Question question, String locale) {
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("Repository impl method updateName for Question.");
        try {
            con = manager.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_UPDATE_QUESTION_TEXT);
            ps.setString(1, question.getQuestionText());
            ps.setLong(2, question.getId());
            ps.setString(3, locale);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps);
        }
        LOG.trace("Repository method updateName for Question finished;");
        return question;
    }

    @Override
    public Question insert(Long parentId, Question question, String s) {
        return null;
    }

    @Override
    public List<Question> findAllByParent(Long parentId, String locale) {
        List<Question> questionList = new ArrayList<>();
        Question question = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method findAllByParent by id for Question.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_ALL_QUESTION_BY_PARENT);
            ps.setString(1,locale);
            ps.setLong(2,parentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                question = extractQuestion(rs);
                questionList.add(question);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method findAllByParent for Question returned --> " + question);
        return questionList;
    }

    @Override
    public void deleteAllByParentId(Long parentId) {

    }

    private Question extractQuestion(ResultSet rs) throws SQLException {
        Question question = new Question();
        question.setId(rs.getLong(Fields.ENTITY_ID));
        question.setQuestionText(rs.getString(Fields.QUESTION_TEXT));
        return question;
    }
}
