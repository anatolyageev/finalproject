package ua.nure.ageev.finaltask4.services.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Answer;
import ua.nure.ageev.finaltask4.repository.AnswerRepository;
import ua.nure.ageev.finaltask4.services.AnswerService;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    private static final Logger LOG = Logger.getLogger(AnswerServiceImpl.class);

    AnswerRepository repository;

    public AnswerServiceImpl(AnswerRepository repository){
        this.repository = repository;
    }



    @Override
    public Answer getOne(Long id, String locale) {
        LOG.trace("Service method getOne for Answer " );
        return repository.getOne(id,locale);
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
        LOG.trace("Service method findAllByParent for Answer " );
        return repository.findAllByParent(parentId,locale);
    }

    @Override
    public void deleteAllByParentId(Long parentId) {

    }
}
