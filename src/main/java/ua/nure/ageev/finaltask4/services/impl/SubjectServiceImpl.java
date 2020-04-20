package ua.nure.ageev.finaltask4.services.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Subject;
import ua.nure.ageev.finaltask4.repository.SubjectRepository;
import ua.nure.ageev.finaltask4.services.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private static final Logger LOG = Logger.getLogger(SubjectServiceImpl.class);
    SubjectRepository repository;

    public SubjectServiceImpl(SubjectRepository repository){
        this.repository = repository;
    }

    @Override
    public Subject getOne(Subject subject, String locale) {
        return null;
    }

    @Override
    public Subject getOne(Integer id, String locale) {
        return null;
    }

    @Override
    public List<Subject> getAll(String locale) {
        LOG.trace("Service method getAll for Subject start" );
        return repository.getAll(locale);
    }

    @Override
    public Integer deleteSubject(Long id, String locale) {
        return null;
    }

    public Subject createSubject(Subject subject, String shortName){
        LOG.trace("Service method createSubject for Subject start" );
        return repository.createSubject(subject,shortName);
    }

    @Override
    public Subject createSubjectLocale(Subject subject, String locale) {
        LOG.trace("Service method createSubjectLocale for Subject start" );
        return repository.createSubjectLocale(subject,locale);
    }

    @Override
    public Subject update(Long id, Subject subject, String locale) {
        return null;
    }
}
