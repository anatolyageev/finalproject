package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.web.command.admin.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("viewSettings", new ViewSettingsCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("adminPageCommand", new AdminPageCommand());
        commands.put("listUsersCommand", new ListUsersCommand());
        commands.put("changeRoleCommand", new ChangeRoleCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("changeUserStatusCommand", new ChangeUserStatusCommand());
        commands.put("subjectCommand", new SubjectCommand());

        //user commands
        commands.put("goToUserPageCommand", new GoToUserPageCommand());
        commands.put("quizzeTestCommand", new QuizzeTestCommand());
        commands.put("quizzeQuestionCommand", new QuizzeQuestionCommand());
        commands.put("quizzeAnswerCommand", new QuizzeAnswerCommand());
        commands.put("userTestCommand", new UserTestCommand());
        commands.put("userAnswerCommand", new UserAnswerCommand());
        commands.put("userPageChange", new UserPageChange());
        commands.put("userTestFinishCommand", new UserTestFinishCommand());
        commands.put("quizzeCommand", new QuizzeCommand());
        commands.put("quizzeTestSortCommand", new QuizzeTestSortCommand());

        //admin commands
        commands.put("adminQuizzeCommand", new AdminQuizzeCommand());
        commands.put("createSubjectCommand", new CreateSubjectCommand());
        commands.put("newSubjectCommand", new NewSubjectCommand());
        commands.put("deleteSubjectCommand", new DeleteSubjectCommand());
        commands.put("editSubjectCommand", new EditSubjectCommand());
        commands.put("updateSubjectCommand", new UpdateSubjectCommand());
        commands.put("testsListCommand", new TestsListCommand());
        commands.put("createTestCommand", new CreateTestCommand());
        commands.put("newTestCommand", new NewTestCommand());
        commands.put("deleteTestCommand", new DeleteTestCommand());
        commands.put("editTestCommand", new EditTestCommand());
        commands.put("updateTestCommand", new UpdateTestCommand());
        commands.put("questionCommand", new QuestionCommand());
        commands.put("adminPageChange", new AdminPageChange());
        commands.put("createQuestionCommand", new CreateQuestionCommand());
        commands.put("newQuestionCommand", new NewQuestionCommand());
        commands.put("updateAnswerCommand", new UpdateAnswerCommand());
        commands.put("editAnswerCommand", new EditAnswerCommand());
        commands.put("deleteQuestionCommand", new DeleteQuestionCommand());
        commands.put("editQuestionCommand", new EditQuestionCommand());
        commands.put("updateQuestionCommand", new UpdateQuestionCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {

        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
