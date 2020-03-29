package ua.nure.ageev.finaltask4.repository;

public interface AdminRepository {

    /**
     * Change user role to Admin
     * @param User id
     */
    Integer setUserRoleAdmin(Long id);
    Integer setUserRoleUser(Long id);
}
