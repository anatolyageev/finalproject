package ua.nure.ageev.finaltask4.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Answer;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.exception.Messages;
import ua.nure.ageev.finaltask4.repository.AnswerRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;
import ua.nure.ageev.finaltask4.repository.db.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepositoryImpl extends AbstractRepository implements AnswerRepository {

    protected static final Logger LOG = Logger.getLogger(AnswerRepositoryImpl.class);

    private static final String SQL_FIND_ANSWER_BY_ID = "SELECT * FROM answers t, answers_locale tl " +
            "WHERE t.id = tl.answer_id " +
            "AND tl.lang_ind = ? AND t.id = ?";

    private static final String SQL_FIND_ALL_ANSWER_BY_PARENT = "SELECT * FROM answers t, answers_locale tl " +
            "WHERE t.id = tl.answer_id " +
            "AND tl.lang_ind = ? AND t.question_id = ?";


    private static final String SQL_ANSWER_INSERT_BY_PARENT = "INSERT  INTO answers " +
            "(question_id) " +
            "VALUES (?)";
    private static final String SQL_ANSWER_INSERT_TEXT = "INSERT  INTO answers_locale " +
            "(answer_id, lang_ind, answer_text) " +
            "VALUES (?,?,?)";

    private static final String SQL_UPDATE_ANSWER = "UPDATE answers " +
            "SET is_correct = ? " +
            "WHERE id = ?";

    private static final String SQL_UPDATE_ANSWER_LOCALE = "UPDATE answers_locale " +
            "SET answer_text = ? " +
            "WHERE answer_id = ? " +
            "AND lang_ind = ?";

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
    public Answer update(Answer answer, String locale) {
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
        LOG.trace("Repository impl method findAllByParent by id for Answer.");
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
        LOG.trace("Repository method findAllByParent for Answer returned --> " + answerList);
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

    @Override
    public Answer insert(Long parentId, Answer answer) {
        Answer resultAnswer = new Answer();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method insert by parentId for Answer.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_ANSWER_INSERT_BY_PARENT, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,parentId);
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    resultAnswer.setId(rs.getLong(1));
                }
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method insert by parentId for Answer returned --> " + resultAnswer);
        return resultAnswer;
    }

    @Override
    public Answer insertName(Answer answer, String locale) {
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("Repository impl method insertName by parentId for Answer.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_ANSWER_INSERT_TEXT);
            ps.setLong(1,answer.getId());
            ps.setString(2,locale);
            ps.setString(3,answer.getAnswerText());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps);
        }
        LOG.trace("Repository method insert by parentId for Question returned --> " + answer);
        return answer;
    }

    @Override
    public Answer updateName(Answer answer, String locale) {
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("Repository impl method updateName for Answer.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_UPDATE_ANSWER_LOCALE);
            ps.setString(1,answer.getAnswerText());
            ps.setLong(2,answer.getId());
            ps.setString(3,locale);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps);
        }
        LOG.trace("Repository method updateName for Answer returned --> " + answer);
        return answer;
    }

    @Override
    public Answer update(Answer answer) {
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("Repository impl method update for Answer.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_UPDATE_ANSWER);
            ps.setBoolean(1,answer.getCorrectAnswer());
            ps.setLong(2,answer.getId());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps);
        }
        LOG.trace("Repository method update for Answer returned --> " + answer);
        return answer;
    }
}
