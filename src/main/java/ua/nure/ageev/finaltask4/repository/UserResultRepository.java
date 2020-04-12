package ua.nure.ageev.finaltask4.repository;

import ua.nure.ageev.finaltask4.domain.UserResult;
import ua.nure.ageev.finaltask4.repository.base.BasicRepositoryNoLocale;
import ua.nure.ageev.finaltask4.repository.base.ChildRepositoryNoLocale;

public interface UserResultRepository extends BasicRepositoryNoLocale<Long, UserResult>, ChildRepositoryNoLocale<Long, UserResult> {
}
