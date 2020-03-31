package ua.nure.ageev.finaltask4.repository;

public interface AdminRepository {

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
    /**
     * Change user status active
     * @param User id
     */
    Integer setUserStatusActive(Long id);

    /**
     * Change user status not active
     * @param User id
     */
    Integer setUserStatusNotActive(Long id);
}
