package ua.nure.ageev.finaltask4.repository;

import ua.nure.ageev.finaltask4.domain.UserResult;
import ua.nure.ageev.finaltask4.repository.base.BasicRepositoryNoLocale;
import ua.nure.ageev.finaltask4.repository.base.ChildRepository;

public interface UserResultRepository extends BasicRepositoryNoLocale<Long, UserResult>, ChildRepository<Long, UserResult,String> {
    public UserResult insert(Long parentId, UserResult userResult) ;
}
