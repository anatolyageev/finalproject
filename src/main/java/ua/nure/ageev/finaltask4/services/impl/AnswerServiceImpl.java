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
        LOG.trace("Service method findAllByParent for Answer " );
        return repository.findAllByParent(parentId,locale);
    }

    @Override
    public void deleteAllByParentId(Long parentId) {

    }

    @Override
    public Answer insert(Long parentId, Answer answer) {
        LOG.trace("Service method insert for Answer " );
        return repository.insert(parentId,answer);
    }

    @Override
    public Answer insertName(Answer answer, String locale) {
        LOG.trace("Service method insert for Answer " );
        return repository.insertName(answer,locale);
    }

    @Override
    public Answer updateName(Answer answer, String locale) {
        LOG.trace("Service method updateName for Answer " );
        return repository.updateName(answer,locale);
    }

    @Override
    public Answer update(Answer answer) {
        LOG.trace("Service method update for Answer " );
        return repository.update(answer);
    }
}
