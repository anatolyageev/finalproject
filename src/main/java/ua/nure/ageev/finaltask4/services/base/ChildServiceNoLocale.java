package ua.nure.ageev.finaltask4.services.base;

import java.util.List;

public interface ChildServiceNoLocale<PID, ENTITY>  {
    ENTITY insert(PID parentId, ENTITY entity);
    List<ENTITY> findAllByParent(PID parentId);
    void deleteAllByParentId(PID parentId);
}

