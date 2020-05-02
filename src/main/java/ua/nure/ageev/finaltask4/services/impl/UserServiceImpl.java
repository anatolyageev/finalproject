package ua.nure.ageev.finaltask4.services.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.repository.UserRepository;
import ua.nure.ageev.finaltask4.repository.impl.UserRepositoryImpl;
import ua.nure.ageev.finaltask4.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    UserRepository repository = new UserRepositoryImpl();

    @Override
    public List<User> getAll() {
        LOG.trace("Service method get all users");
        return repository.getAllUsers();
    }

    @Override
    public User getOne(String login) {
        LOG.trace("Service method getOne --> " + login);
        return repository.getOne(login);
    }

    @Override
    public User getOne(long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User createUser(User user)  {
        return repository.createUser(user);
    }
}
