package ua.nure.ageev.finaltask4.repository.base;

import java.util.List;

public interface BasicRepositoryNoLocale<ID, ENTITY> {
    ENTITY getOne(ID id);
    ENTITY update(ID id, ENTITY entity);
    void delete(ID id);
    List<ENTITY> findAll();
}
