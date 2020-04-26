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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionCommand extends Command {

    private static final long serialVersionUID = -1573445793499573283L;

    private static final Logger LOG = Logger.getLogger(QuestionCommand.class);

    /**
     * Execution method for enter questions and answers list command for administrator.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("UserTestCommand starts");
        QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryImpl());

        HttpSession session = request.getSession();
        String local = DataHelper.getLanguage(request);
        LOG.debug("UserTestCommand get locale after if: " + local);
        Long testId = Long.parseLong(request.getParameter("testId"));

        List<Question> questionListEn = questionService.findAllByParent(testId, ConstantsForCommands.LANGUAGE_EN);
        LOG.debug("Question list: " + questionListEn);

        DataHelper.addAnswersToQuestions(questionListEn,ConstantsForCommands.LANGUAGE_EN);
        LOG.debug("Answers added: " + questionListEn);

        List<Question> questionListRu = questionService.findAllByParent(testId, ConstantsForCommands.LANGUAGE_RU);
        LOG.debug("Question list: " + questionListRu);

        DataHelper.addAnswersToQuestions(questionListRu,ConstantsForCommands.LANGUAGE_RU);
        LOG.debug("Answers added: " + questionListRu);

        session.setAttribute("questionListEn", questionListEn);
        session.setAttribute("questionListRu", questionListRu);
        session.setAttribute("testId", testId);
        session.setAttribute("pageId",0);

        return Path.PAGE_ADMIN_QUESTIONS;
    }


}
