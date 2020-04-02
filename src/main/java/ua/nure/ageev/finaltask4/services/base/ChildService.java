package ua.nure.ageev.finaltask4.services.base;

import java.util.List;

public interface ChildService <PID, ENTITY, LOCALE> {
    ENTITY insert(PID parentId, ENTITY entity, LOCALE locale);
    List<ENTITY> findAllByParent(PID parentId, LOCALE locale);
    void deleteAllByParentId(PID parentId);
}
