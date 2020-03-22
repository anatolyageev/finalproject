package ua.nure.ageev.finaltask4.repository;


import ua.nure.ageev.finaltask4.domain.User;

public interface UserRepository {

    /**
     * Returns a user with the given login.
     *
     * @param login
     *            User login.
     * @return User entity.
     */
    User getOne(String login);


    /**
     * Returns a user with the given identifier.
     *
     * @param id
     *            User identifier.
     * @return User entity.
     */
    User getOne(long id);


}