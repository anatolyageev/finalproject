package ua.nure.ageev.finaltask4.repository;


import ua.nure.ageev.finaltask4.domain.User;

public interface UserRepository {
    User getOne(String login);
}
