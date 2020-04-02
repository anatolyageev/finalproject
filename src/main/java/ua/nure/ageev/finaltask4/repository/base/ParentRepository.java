package ua.nure.ageev.finaltask4.repository.base;

public interface ParentRepository<ENTITY, LOCALE> {
    ENTITY insert(ENTITY entity, LOCALE locale);
}
