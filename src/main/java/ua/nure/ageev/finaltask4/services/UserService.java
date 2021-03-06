package ua.nure.ageev.finaltask4.services;

import ua.nure.ageev.finaltask4.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getOne(String login);
    User getOne(long id);
    void deleteUser(Long id);
    User createUser(User user) ;
    String getSalt(Long id);
    String getHashAlgorithm(Long id);
}
