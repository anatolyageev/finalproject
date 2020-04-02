package ua.nure.ageev.finaltask4.repository;

import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.repository.base.BasicRepository;
import ua.nure.ageev.finaltask4.repository.base.ChildRepository;

public interface TestRepository extends BasicRepository<Long, Test, String> , ChildRepository<Long,Test,String> {
}
