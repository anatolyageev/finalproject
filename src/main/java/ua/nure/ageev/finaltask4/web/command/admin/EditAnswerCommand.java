package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Answer;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.AnswerRepositoryImpl;
import ua.nure.ageev.finaltask4.services.AnswerService;
import ua.nure.ageev.finaltask4.services.impl.AnswerServiceImpl;
import ua.nure.ageev.finaltask4.web.command.Command;
import ua.nure.ageev.finaltask4.web.utils.ConstantsForCommands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditAnswerCommand extends Command {

    private static final long serialVersionUID = -1573493777199573283L;

    private static final Logger LOG = Logger.getLogger(EditAnswerCommand.class);

    /**
     * Execution method for edit answer command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("EditAnswerCommand start");

        Long questionId = Long.parseLong(request.getParameter("questionId"));
        AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryImpl());
        List<Answer> answerListEn = answerService.findAllByParent(questionId, ConstantsForCommands.LANGUAGE_EN);
        List<Answer> answerListRu = answerService.findAllByParent(questionId, ConstantsForCommands.LANGUAGE_RU);

        Map<Long, String> answersRu = new HashMap<>();
        for (Answer a:answerListRu) {
            answersRu.put(a.getId(),a.getAnswerText());
        }

        request.setAttribute("answerListEn",answerListEn);
        request.setAttribute("answersRu",answersRu);

        LOG.debug("EditAnswerCommand finished");
        return Path.PAGE_ADMIN_EDIT_ANSWER;
    }
}
