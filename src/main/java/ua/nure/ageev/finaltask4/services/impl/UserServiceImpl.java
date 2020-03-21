package ua.nure.ageev.finaltask4.services.impl;

import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.repository.UserRepository;
import ua.nure.ageev.finaltask4.repository.impl.UserRepositoryImpl;
import ua.nure.ageev.finaltask4.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
   // private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    UserRepository repository = new UserRepositoryImpl();

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getOne(String login) {
        System.out.println("In UserServiceImpl");
 //       LOG.trace("Service method getOne --> " + login);
        return repository.getOne(login);
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User createUser(User user) {
        return null;
    }
}
