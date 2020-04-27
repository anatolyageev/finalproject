package ua.nure.ageev.finaltask4.repository;

import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.repository.base.BasicRepository;
import ua.nure.ageev.finaltask4.repository.base.ChildRepository;

public interface QuestionRepository extends BasicRepository<Long, Question, String>, ChildRepository<Long, Question,String> {
    public Question insert(Long parentId, Question question);
    public Question insertName(Question question, String locale);
    public Question updateName(Question question, String locale);
}
