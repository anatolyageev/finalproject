package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.QuestionRepositoryImpl;
import ua.nure.ageev.finaltask4.services.QuestionService;
import ua.nure.ageev.finaltask4.services.impl.QuestionServiceImpl;
import ua.nure.ageev.finaltask4.web.command.Command;
import ua.nure.ageev.finaltask4.web.utils.ConstantsForCommands;
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateQuestionCommand extends Command {

    private static final long serialVersionUID = -1573449485459573283L;

    private static final Logger LOG = Logger.getLogger(UpdateQuestionCommand.class);

    /**
     * Execution method for update question command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("UpdateQuestionCommand start");

        Long questionId = Long.parseLong(request.getParameter("questionId"));
        QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryImpl());
        String local = DataHelper.getLanguage(request);
        String nameEn = request.getParameter("nameEn");
        String nameRu = request.getParameter("nameRu");

        Question question = new Question();
        question.setId(questionId);

        question.setQuestionText(nameEn);
        questionService.updateName(question, ConstantsForCommands.LANGUAGE_EN);
        question.setQuestionText(nameRu);
        questionService.updateName(question, ConstantsForCommands.LANGUAGE_RU);

        DataHelper.updateQuestionList(request, local);
        LOG.debug("UpdateQuestionCommand end.");
        return Path.PAGE_ADMIN_QUESTIONS;
    }
}
