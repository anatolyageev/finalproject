package ua.nure.ageev.finaltask4.services.impl;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.repository.AdminRepository;
import ua.nure.ageev.finaltask4.services.AdminService;

public class AdminServiceImpl implements AdminService {

    private static final Logger LOG = Logger.getLogger(AdminServiceImpl.class);
    AdminRepository repository;
    public AdminServiceImpl(AdminRepository repository){
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer setUserRoleAdmin(Long id) {
        LOG.trace("Service method setUserRoleAdmin");
        return repository.setUserRoleAdmin(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer setUserRoleUser(Long id) {
        LOG.trace("Service method setUserRoleUser");
        return repository.setUserRoleUser(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer setUserStatusActive(Long id) {
        LOG.trace("Service method setUserStatusActive");
        return repository.setUserStatusActive(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer setUserStatusNotActive(Long id) {
        LOG.trace("Service method setUserStatusNotActive");
        return repository.setUserStatusNotActive(id);
    }
}
