package ua.nure.ageev.finaltask4.repository.impl;

import ua.nure.ageev.finaltask4.domain.Subject;
import ua.nure.ageev.finaltask4.repository.SubjectRepository;

import java.util.List;

public class SubjectRepositoryImpl implements SubjectRepository {

    private static final String SQL_FIND_ALL_SUBJECT = "SELECT * FROM user_account ua, users u WHERE u.account_id = ua.id";


    @Override
    public Subject getOne(Subject subject) {
        return null;
    }

    @Override
    public Subject getOne(Integer id) {
        return null;
    }

    @Override
    public List<Subject> getAll() {
        return null;
    }

    @Override
    public Integer deleteSubject(Long id) {
        return null;
    }

    @Override
    public Subject createUser(Subject subject) {
        return null;
    }

    @Override
    public Subject update(Long id, Subject subject) {
        return null;
    }
}
