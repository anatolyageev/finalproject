package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.QuestionRepositoryImpl;
import ua.nure.ageev.finaltask4.services.QuestionService;
import ua.nure.ageev.finaltask4.services.impl.QuestionServiceImpl;
import ua.nure.ageev.finaltask4.web.command.Command;
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteQuestionCommand extends Command {

    private static final long serialVersionUID = -1573445767139433283L;

    private static final Logger LOG = Logger.getLogger(DeleteQuestionCommand.class);

    /**
     * Execution method for delete question command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("DeleteQuestionCommand starts");

        String local = DataHelper.getLanguage(request);

        Long questionId = Long.parseLong(request.getParameter("questionId"));

        QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryImpl());
        questionService.delete(questionId);

        DataHelper.updateQuestionList(request, local);
        LOG.debug("DeleteQuestionCommand finished");

        return Path.PAGE_ADMIN_QUESTIONS;
    }
}
