package ua.nure.ageev.finaltask4.repository;

import ua.nure.ageev.finaltask4.domain.Answer;
import ua.nure.ageev.finaltask4.repository.base.BasicRepository;
import ua.nure.ageev.finaltask4.repository.base.ChildRepository;

public interface AnswerRepository extends BasicRepository<Long, Answer, String>, ChildRepository<Long, Answer,String> {
    public Answer insert(Long parentId, Answer answer);
    public Answer insertName(Answer answer, String locale);
    public Answer updateName(Answer answer, String locale);
}
