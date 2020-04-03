package ua.nure.ageev.finaltask4.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.exception.Messages;
import ua.nure.ageev.finaltask4.repository.QuestionRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;
import ua.nure.ageev.finaltask4.repository.db.Fields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl extends AbstractRepository implements QuestionRepository {

    protected static final Logger LOG = Logger.getLogger(QuestionRepositoryImpl.class);

    private static final String SQL_FIND_ALL_QUESTION = "SELECT * FROM tests s, tests_locale sl " +
            "WHERE s.id = sl.test_id AND sl.lang_ind = ?";

    private static final String SQL_FIND_QUESTION_BY_ID = "SELECT * FROM tests t, tests_locale tl " +
            "WHERE t.id = tl.test_id " +
            "AND tl.lang_ind = ? AND t.id = ?";

    private static final String SQL_FIND_ALL_QUESTION_BY_PARENT = "SELECT * FROM questions t, questions_locale tl " +
            "WHERE t.id = tl.question_id " +
            "AND tl.lang_ind = ? AND t.test_id = ?";

    private static final String SQL_DELETE_QUESTION = "DELETE FROM tests WHERE id = ?";

    private static final String SQL_DELETE_QUESTION_LOCALE = "DELETE FROM tests_locale WHERE test_id = ?";

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
    public Question update(Long aLong, Question question, String s) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Question> findAll(String locale) {
        return null;
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
