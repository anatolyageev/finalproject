package ua.nure.ageev.finaltask4.repository;

import ua.nure.ageev.finaltask4.domain.Subject;

import java.util.List;

public interface SubjectRepository {

    Subject getOne(Subject subject);

    Subject getOne(Integer id);

    List<Subject> getAll();

    Integer deleteSubject(Long id);

    Subject createUser(Subject subject);

    Subject update (Long id, Subject subject);

}
