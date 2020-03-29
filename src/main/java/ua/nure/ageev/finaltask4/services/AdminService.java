package ua.nure.ageev.finaltask4.services;


public interface AdminService {

    /**
     * Change user role to Admin
     * @param User id
     */
    Integer setUserRoleAdmin(Long id);

    /**
     * Change user role to User
     * @param User id
     */
    Integer setUserRoleUser(Long id);
}
