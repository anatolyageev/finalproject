package ua.nure.ageev.finaltask4.repository.base;

import java.util.List;

public interface BasicRepository<ID, ENTITY, LOCALE> {
    ENTITY getOne(ID id, LOCALE locale);
    ENTITY update(ENTITY entity, LOCALE locale);
    void delete(ID id);
    List<ENTITY> findAll(LOCALE locale);
}
