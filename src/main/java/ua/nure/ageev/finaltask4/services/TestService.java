package ua.nure.ageev.finaltask4.services;

import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.services.base.BasicService;
import ua.nure.ageev.finaltask4.services.base.ChildService;

public interface TestService extends BasicService<Long, Test, String>, ChildService<Long,Test,String> {
    public Test insert(Long parentId, Test test);
    public Test insertName(Test test, String locale);
    public Test updateName( Test test, String locale);
}
