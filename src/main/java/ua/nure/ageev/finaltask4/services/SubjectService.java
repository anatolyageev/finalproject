package ua.nure.ageev.finaltask4.services;

import ua.nure.ageev.finaltask4.domain.Subject;

import java.util.List;

public interface SubjectService {

    Subject getOne(Subject subject, String locale);

    Subject getOne(Integer id, String locale);

    List<Subject> getAll(String locale);

    Integer deleteSubject(Long id, String locale);

    public Subject createSubject(Subject subject, String shortName);

    public Subject createSubjectLocale(Subject subject, String locale);

    public Integer deleteSubject(Long id);

    Subject update (Long id, Subject subject, String locale);
}
