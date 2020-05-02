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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditQuestionCommand extends Command {

    private static final long serialVersionUID = -154567777199573283L;

    private static final Logger LOG = Logger.getLogger(EditQuestionCommand.class);

    /**
     * Execution method for edit question command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("EditQuestionCommand finished");

        Long questionId = Long.parseLong(request.getParameter("questionId"));
        QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryImpl());

        Question questionEn = questionService.getOne(questionId, ConstantsForCommands.LANGUAGE_EN);
        Question questionRu = questionService.getOne(questionId, ConstantsForCommands.LANGUAGE_RU);

        request.setAttribute("questionEn", questionEn);
        LOG.debug("Set attribute questionEn --> " + questionEn);
        request.setAttribute("questionRu", questionRu);
        LOG.debug("Set attribute questionRu --> " + questionRu);
        request.setAttribute("questionId", questionId);

        return Path.PAGE_ADMIN_EDIT_QUESTION;
    }
}
