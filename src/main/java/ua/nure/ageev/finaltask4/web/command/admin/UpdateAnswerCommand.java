package ua.nure.ageev.finaltask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Answer;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.AnswerRepositoryImpl;
import ua.nure.ageev.finaltask4.services.AnswerService;
import ua.nure.ageev.finaltask4.services.impl.AnswerServiceImpl;
import ua.nure.ageev.finaltask4.web.command.Command;
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class UpdateAnswerCommand extends Command {

    private static final long serialVersionUID = -1573446785459573283L;

    private static final Logger LOG = Logger.getLogger(UpdateAnswerCommand.class);

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("UpdateAnswerCommand start");
        AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryImpl());
        List<String> correctAnswers = Arrays.asList(request.getParameterValues("isCorrect"));
        String local = DataHelper.getLanguage(request);
        setCorrectAnswers(correctAnswers);

        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()){
            String paramName = (String) e.nextElement();
            if(paramName.matches("\\d+_\\w{2}")){
                Answer tempAnswer = new Answer();
                String value = request.getParameter(paramName);
                LOG.debug("UpdateAnswerCommand value: " + value);
                String[] param = paramName.split("_");
                LOG.debug("UpdateAnswerCommand param Id--> " + param[0]);
                LOG.debug("UpdateAnswerCommand param Id--> " + param[1]);
                tempAnswer.setId(Long.parseLong(param[0]));
                tempAnswer.setAnswerText(value);
                LOG.debug("UpdateAnswerCommand tempAnswer" + tempAnswer);
                String locale = param[1];
                LOG.debug("UpdateAnswerCommand locale" + locale);
                answerService.updateName(tempAnswer, locale);
            }
        }
        DataHelper.updateQuestionList(request, local);
        return Path.PAGE_ADMIN_QUESTIONS;
    }



    public void setCorrectAnswers(List<String> correctAnswers) {
        AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryImpl()); ;
        for (String s: correctAnswers) {
            Answer tempAnswer = new Answer();
            tempAnswer.setId(Long.parseLong(s));
            tempAnswer.setCorrectAnswer(true);
            LOG.debug("Parse correct answer" + tempAnswer);
            answerService.update(tempAnswer);
        }
    }
}
