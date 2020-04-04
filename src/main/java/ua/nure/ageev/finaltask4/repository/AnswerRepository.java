package ua.nure.ageev.finaltask4.repository;

import ua.nure.ageev.finaltask4.domain.Answer;
import ua.nure.ageev.finaltask4.repository.base.BasicRepository;
import ua.nure.ageev.finaltask4.repository.base.ChildRepository;

public interface AnswerRepository extends BasicRepository<Long, Answer, String>, ChildRepository<Long, Answer,String> {

}
