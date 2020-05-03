package ua.nure.ageev.finaltask4.repository;


import ua.nure.ageev.finaltask4.domain.User;

import java.util.List;

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

    List<User> getAllUsers();

    /**
     * Add User to database and returns a user.
     *
     * @param User entity.
     * @return User entity.
     */
    User createUser(User user) ;

    /**
     * Returns a user salt for password.
     *
     * @param id
     *            User identifier.
     * @return String .
     */
    String getSalt(Long id);

    /**
     * Returns a user hash algorithm for password.
     *
     * @param id
     *            User identifier.
     * @return String .
     */
    String getHashAlgorithm(Long id);
}
