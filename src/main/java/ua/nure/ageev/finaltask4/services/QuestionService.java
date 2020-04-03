package ua.nure.ageev.finaltask4.services;

import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.services.base.BasicService;
import ua.nure.ageev.finaltask4.services.base.ChildService;

public interface QuestionService extends BasicService<Long, Question, String>, ChildService<Long,Question,String> {
}
