package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Answer;
import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.AnswerRepositoryImpl;
import ua.nure.ageev.finaltask4.repository.impl.QuestionRepositoryImpl;
import ua.nure.ageev.finaltask4.services.AnswerService;
import ua.nure.ageev.finaltask4.services.QuestionService;
import ua.nure.ageev.finaltask4.services.impl.AnswerServiceImpl;
import ua.nure.ageev.finaltask4.services.impl.QuestionServiceImpl;
import ua.nure.ageev.finaltask4.web.command.Command;
import ua.nure.ageev.finaltask4.web.utils.ConstantsForCommands;
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class NewQuestionCommand extends Command {

    private static final long serialVersionUID = -1573435785199573283L;

    private static final Logger LOG = Logger.getLogger(NewQuestionCommand.class);

    /**
     * Execution method for create new question.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("NewQuestionCommand start");

        QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryImpl());
        AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryImpl());
        String questionEn = request.getParameter("newQuestionEn");
        String questionRu = request.getParameter("newQuestionRu");
        Long testId = Long.parseLong(request.getParameter("testId"));
        Question question = new Question();
        question = questionService.insert(testId, question);
        LOG.debug("NewQuestionCommand question" + question);

        question.setQuestionText(questionEn);
        questionService.insertName(question,ConstantsForCommands.LANGUAGE_EN);
        LOG.debug("NewQuestionCommand questionEn" + question);

        question.setQuestionText(questionRu);
        questionService.insertName(question,ConstantsForCommands.LANGUAGE_RU);
        LOG.debug("NewQuestionCommand questionRu" + question);
        List<Answer> answerList = DataHelper.answersCreation(question);
        Long questionId = question.getId();
        LOG.debug("NewQuestionCommand answerList" + answerList);

//        List<Answer> answerListEn = answerService.findAllByParent(questionId,ConstantsForCommands.LANGUAGE_EN);
//        List<Answer> answerListRu = answerService.findAllByParent(questionId,ConstantsForCommands.LANGUAGE_RU);
        request.setAttribute("questionId",questionId);
        request.setAttribute("answerList",answerList);


        LOG.debug("NewQuestionCommand end");
        return Path.PAGE_ADMIN_CREATE_ANSWER;
    }


}