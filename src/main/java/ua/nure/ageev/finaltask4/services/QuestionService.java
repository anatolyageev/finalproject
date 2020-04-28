package ua.nure.ageev.finaltask4.services;

import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.services.base.BasicService;
import ua.nure.ageev.finaltask4.services.base.ChildService;

public interface QuestionService extends BasicService<Long, Question, String>, ChildService<Long,Question,String> {
    public Question insert(Long parentId, Question question);
    public Question insertName(Question question, String locale);
    public Question updateName(Question question, String locale);
}
