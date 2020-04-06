package ua.nure.ageev.finaltask4.repository.base;

import java.util.List;

public interface ChildRepositoryNoLocale<PID, ENTITY> {
    ENTITY insert(PID parentId, ENTITY entity);
    List<ENTITY> findAllByParent(PID parentId);
    void deleteAllByParentId(PID parentId);
}
