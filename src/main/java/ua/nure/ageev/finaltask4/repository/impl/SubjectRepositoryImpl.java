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


    @Override
    public Subject getOne(Subject subject, String locale) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne by Subject for Subject.");
        try {
            con = manager.getConnection();
            ps = con.prepareStatement(SQL_FIND_SUBJECT_BY_ID);
            ps.setString(1,locale);
            ps.setLong(2,subject.getId());
            rs = ps.executeQuery();
            subject.setSubjectName(rs.getString(Fields.SUBJECT_NAME));
            con.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            manager.close(con, ps, rs);
        }
        LOG.trace("Repository method getAll for Subject returned --> " + subject);
        return subject;
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

    @Override
    public Subject createSubject(Subject subject, String locale) {
        return null;
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
