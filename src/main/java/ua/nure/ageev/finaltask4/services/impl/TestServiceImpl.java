package ua.nure.ageev.finaltask4.services.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.repository.TestRepository;
import ua.nure.ageev.finaltask4.services.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {
    private static final Logger LOG = Logger.getLogger(TestServiceImpl.class);

    TestRepository repository;

    public TestServiceImpl(TestRepository testRepository){
        this.repository = testRepository;
    }

    @Override
    public Test getOne(Long id, String locale) {
        LOG.trace("Service method getOne for Test " );
        return repository.getOne(id,locale);
    }

    @Override
    public Test update(Test test, String locale) {
        LOG.trace("Service method update for Test " );
        return repository.update(test,locale);
    }

    @Override
    public void delete(Long id) {
        LOG.trace("Service method delete for Test " );
        repository.delete(id);
    }

    @Override
    public List<Test> findAll(String locale) {
        LOG.trace("Service method findAll for Test " );
        return repository.findAll(locale);
    }

    @Override
    public Test insert(Long parentId, Test test, String s) {
        return null;
    }

    @Override
    public List<Test> findAllByParent(Long parentId, String locale) {
        LOG.trace("Service method findAllByParent for Test " );
        return repository.findAllByParent(parentId,locale);
    }

    @Override
    public void deleteAllByParentId(Long parentId) {

    }

    @Override
    public Test insert(Long parentId, Test test) {
        LOG.trace("Service method findAllByParent for Test " );
        return repository.insert(parentId,test);
    }

    @Override
    public Test insertName(Test test, String locale) {
        LOG.trace("Service method insertName for Test " );
        return repository.insertName(test,locale);
    }

    @Override
    public Test updateName(Test test, String locale) {
        LOG.trace("Service method updateName for Test " );
        return repository.updateName(test,locale);
    }
}
