package ua.nure.ageev.finaltask4.services;

import ua.nure.ageev.finaltask4.domain.UserResult;
import ua.nure.ageev.finaltask4.services.base.BasicServiceNoLocale;
import ua.nure.ageev.finaltask4.services.base.ChildServiceNoLocale;

public interface UserResultService extends BasicServiceNoLocale<Long, UserResult>, ChildServiceNoLocale<Long,UserResult> {
}
