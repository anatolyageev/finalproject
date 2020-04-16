package ua.nure.ageev.finaltask4.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Answer;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.exception.Messages;
import ua.nure.ageev.finaltask4.repository.AnswerRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;
import ua.nure.ageev.finaltask4.repository.db.Fields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepositoryImpl extends AbstractRepository implements AnswerRepository {

    protected static final Logger LOG = Logger.getLogger(AnswerRepositoryImpl.class);

    private static final String SQL_FIND_ALL_QUESTION = "SELECT * FROM tests s, tests_locale sl " +
            "WHERE s.id = sl.test_id AND sl.lang_ind = ?";

    private static final String SQL_FIND_ANSWER_BY_ID = "SELECT * FROM answers t, answers_locale tl " +
            "WHERE t.id = tl.answer_id " +
            "AND tl.lang_ind = ? AND t.id = ?";

    private static final String SQL_FIND_ALL_ANSWER_BY_PARENT = "SELECT * FROM answers t, answers_locale tl " +
            "WHERE t.id = tl.answer_id " +
            "AND tl.lang_ind = ? AND t.question_id = ?";

    private static final String SQL_DELETE_QUESTION = "DELETE FROM tests WHERE id = ?";

    private static final String SQL_DELETE_QUESTION_LOCALE = "DELETE FROM tests_locale WHERE test_id = ?";

    @Override
    public Answer getOne(Long id, String locale) {
        Answer answer = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne by id for Answer.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_ANSWER_BY_ID);
            ps.setString(1, locale);
            ps.setLong(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                answer = extractAnswer(rs);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getOne for Answer returned --> " + answer);
        return answer;
    }

    @Override
    public Answer update(Long id, Answer answer, String locale) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Answer> findAll(String locale) {
        return null;
    }

    @Override
    public Answer insert(Long parentId, Answer answer, String locale) {
        return null;
    }

    @Override
    public List<Answer> findAllByParent(Long parentId, String locale) {
        List<Answer> answerList =new ArrayList<>();
        Answer answer = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne by id for Answer.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_ALL_ANSWER_BY_PARENT);
            ps.setString(1, locale);
            ps.setLong(2, parentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                answer = extractAnswer(rs);
                answerList.add(answer);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getOne for Answer returned --> " + answer);
        return answerList;
    }

    @Override
    public void deleteAllByParentId(Long parentId) {

    }

    private Answer extractAnswer(ResultSet rs) throws SQLException {
        Answer answer = new Answer();
        answer.setId(rs.getLong(Fields.ENTITY_ID));
        answer.setAnswerText(rs.getString(Fields.ANSWER_TEXT));
        answer.setCorrectAnswer(rs.getBoolean(Fields.ANSWER_IS_CORRECT));
        return answer;
    }
}
