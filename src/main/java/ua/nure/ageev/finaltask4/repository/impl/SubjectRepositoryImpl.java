package ua.nure.ageev.finaltask4.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Subject;
import ua.nure.ageev.finaltask4.exception.DBException;
import ua.nure.ageev.finaltask4.exception.Messages;
import ua.nure.ageev.finaltask4.repository.SubjectRepository;
import ua.nure.ageev.finaltask4.repository.base.AbstractRepository;
import ua.nure.ageev.finaltask4.repository.db.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubjectRepositoryImpl extends AbstractRepository implements SubjectRepository {

    protected static final Logger LOG = Logger.getLogger(SubjectRepositoryImpl.class);
    private static final String SQL_FIND_ALL_SUBJECT = "SELECT * FROM subject s, subject_locale sl " +
            "WHERE s.id = sl.subject_id AND sl.lang_ind = ?";

    private static final String SQL_FIND_SUBJECT_BY_ID = "SELECT * FROM subject s, subject_locale sl " +
            "WHERE s.id = sl.subject_id " +
            "AND sl.lang_ind = ? AND s.id = ?";

    private static final String SQL_INSERT_NEW_SUBJECT = "INSERT INTO subject " +
            "(default_name) " +
            "VALUES(?)";

    private static final String SQL_INSERT_NEW_SUBJECT_NAME ="INSERT  INTO subject_locale" +
            "(subject_id, lang_ind, subject_name) " +
            "VALUES(?,?,?)";

    private static final String SQL_DELETE_SUBJECT ="DELETE FROM subject " +
            "WHERE id = ?";

    private static final String SQL_DELETE_SUBJECT_LOCALES ="DELETE FROM subject_locale " +
            "WHERE subject_locale.subject_id = ?";


    @Override
    public Subject getOne(Subject subject, String locale) {
        Subject resultSubject = new Subject();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne by Subject for Subject.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_SUBJECT_BY_ID);
            LOG.trace("Repository impl method getOne befor setString.");
            ps.setString(1, locale);
            ps.setLong(2, subject.getId());
            LOG.trace("Repository impl method getOne befor exequte qurey.");
            rs = ps.executeQuery();
            LOG.trace("Repository impl method getOne after exequte qurey.");
            while (rs.next()) {
                resultSubject.setId(rs.getLong(Fields.ENTITY_ID));
                resultSubject.setSubjectName(rs.getString(Fields.SUBJECT_NAME));
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getOne for Subject returned --> " + subject);
        return resultSubject;
    }

    @Override
    public Subject getOne(Integer id, String locale) {
        return null;
    }

    @Override
    public List<Subject> getAll(String locale) {
        Subject subject = null;
        List<Subject> subjectsList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getAll for Subject.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_ALL_SUBJECT);
            ps.setString(1, locale);
            rs = ps.executeQuery();
            while (rs.next()) {
                subject = extractSubject(rs);
                subjectsList.add(subject);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
            subjectsList = Collections.emptyList();
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getAll for Subject returned --> " + subjectsList);
        return subjectsList;
    }

    @Override
    public Integer deleteSubject(Long id, String locale) {
        return null;
    }


    public Integer deleteSubject(Long id) {
        Integer result = null;
        PreparedStatement ps = null;
        Connection con = null;
        LOG.trace("deleteSubject impl method createSubject --> " + id);
        try {
            con = manager.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_DELETE_SUBJECT_LOCALES);
            ps.setLong(1, id);
            if (ps.executeUpdate() > 0) {
                ps = con.prepareStatement(SQL_DELETE_SUBJECT);
                ps.setLong(1, id);
                result = ps.executeUpdate();
            }
            LOG.debug("Repository deleteSubject  --> done" );
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps);
        }
        LOG.debug("Repository method deleteSubject  --> " + result);
        return result;
    }

    @Override
    public Subject createSubject(Subject subject, String shortName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method createSubject --> " + subject);
        try {
            con = manager.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_INSERT_NEW_SUBJECT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, shortName);
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    Long subjectId = rs.getLong(1);
                    subject.setId(subjectId);
                }
            }
            LOG.debug("Repository createSubject  --> " + subject);
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.debug("Repository method createSubject  --> " + subject);
        return subject;
    }

    public Subject createSubjectLocale(Subject subject, String locale) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method createSubjectLocale --> " + subject);
        try {
            con = manager.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_INSERT_NEW_SUBJECT_NAME);
            ps.setLong(1,subject.getId());
            ps.setString(2,locale);
            ps.setString(3,subject.getSubjectName());
            if (ps.executeUpdate() > 0) {
                con.commit();
            }
            LOG.debug("Repository createSubjectLocale  --> " + subject);

        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.debug("Repository method createSubjectLocale  --> " + subject);
        return subject;
    }


    @Override
    public Subject update(Long id, Subject subject, String locale) {
        return null;
    }

    private Subject extractSubject(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getLong(Fields.ENTITY_ID));
        subject.setSubjectName(rs.getString(Fields.SUBJECT_NAME));
        return subject;
    }
}
