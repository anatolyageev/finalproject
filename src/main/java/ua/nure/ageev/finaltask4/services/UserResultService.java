package ua.nure.ageev.finaltask4.services;

import ua.nure.ageev.finaltask4.domain.UserResult;
import ua.nure.ageev.finaltask4.services.base.BasicServiceNoLocale;
import ua.nure.ageev.finaltask4.services.base.ChildServiceNoLocale;

import java.util.List;

public interface UserResultService extends BasicServiceNoLocale<Long, UserResult>, ChildServiceNoLocale<Long,UserResult> {
    UserResult insert(Long parentId, UserResult entity, String locale);
    public List<UserResult> findAllByParent(Long userId, String locale);
}
