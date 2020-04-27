package ua.nure.ageev.finaltask4.services.base;

import java.util.List;

public interface BasicServiceNoLocale<ID, ENTITY> {
    ENTITY getOne(ID id);
    ENTITY update(ENTITY entity);
    void delete(ID id);
    List<ENTITY> findAll();
}
