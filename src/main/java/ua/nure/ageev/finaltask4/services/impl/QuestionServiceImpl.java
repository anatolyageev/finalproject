package ua.nure.ageev.finaltask4.services.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.repository.QuestionRepository;
import ua.nure.ageev.finaltask4.services.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private static final Logger LOG = Logger.getLogger(QuestionServiceImpl.class);

    QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository){
        this.repository = repository;
    }


    @Override
    public Question getOne(Long id, String locale) {
        LOG.trace("Service method getOne for Question " );
        return repository.getOne(id,locale);
    }

    @Override
    public Question update(Question question, String s) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Question> findAll(String s) {
        return null;
    }

    @Override
    public Question insert(Long parentId, Question question, String s) {
        return null;
    }

    @Override
    public List<Question> findAllByParent(Long parentId, String locale) {
        LOG.trace("Service method findAllByParent for Question " );
        return repository.findAllByParent(parentId,locale);
    }

    @Override
    public void deleteAllByParentId(Long parentId) {

    }

    @Override
    public Question insert(Long parentId, Question question) {
        LOG.trace("Service method insert parentId for Question " );
        return repository.insert(parentId,question);
    }

    @Override
    public Question insertName(Question question, String locale) {
        LOG.trace("Service method insert parentId for Question " );
        return repository.insertName(question,locale);
    }

    @Override
    public Question updateName(Question question, String locale) {
        return null;
    }
}
