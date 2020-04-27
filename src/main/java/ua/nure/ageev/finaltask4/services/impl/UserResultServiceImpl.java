package ua.nure.ageev.finaltask4.services.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.UserResult;
import ua.nure.ageev.finaltask4.repository.UserResultRepository;
import ua.nure.ageev.finaltask4.services.UserResultService;

import java.util.List;

public class UserResultServiceImpl implements UserResultService {

    private static final Logger LOG = Logger.getLogger(UserResultServiceImpl.class);

    UserResultRepository repository;

    public UserResultServiceImpl(UserResultRepository repository){
        this.repository = repository;
    }


    @Override
    public UserResult getOne(Long aLong) {
        return null;
    }

    @Override
    public UserResult update(UserResult userResult) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<UserResult> findAll() {
        return null;
    }


    public UserResult insert(Long parentId, UserResult userResult) {
        LOG.trace("Service method insert for UserResult " );
        return repository.insert(parentId,userResult);
    }

    @Override
    public List<UserResult> findAllByParent(Long parentId) {
        return null;
    }

    @Override
    public UserResult insert(Long parentId, UserResult entity, String locale) {
        return null;
    }

    @Override
    public List<UserResult> findAllByParent(Long userId, String locale) {
        LOG.trace("Service method findAllByParent for UserResult " );
        return repository.findAllByParent(userId, locale);
    }

    @Override
    public void deleteAllByParentId(Long parentId) {

    }
}
