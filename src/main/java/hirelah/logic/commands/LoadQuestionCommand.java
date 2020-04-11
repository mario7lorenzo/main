package hirelah.logic.commands;

import hirelah.commons.exceptions.DataConversionException;
import hirelah.commons.exceptions.IllegalValueException;
import hirelah.commons.util.ModelUtil;
import hirelah.logic.commands.exceptions.CommandException;
import hirelah.model.Model;
import javafx.collections.ObservableList;
import hirelah.storage.Storage;
import hirelah.model.hirelah.Question;
import hirelah.model.hirelah.QuestionList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class LoadQuestionCommand extends Command {
    public static final String COMMAND_WORD = "question";
    public static final boolean DESIRED_MODEL_FINALIZED_STATE = false;
    public static final String MESSAGE_FORMAT = "load " + COMMAND_WORD + " <session>";
    public static final String MESSAGE_FUNCTION = ": Loads the questions from another session\n";
    public static final String MESSAGE_SESSION_NOT_EXIST = "The session does not exist";
    public static final String MESSAGE_NOT_ABLE_TO_CONVERT = "The question list is failed to be converted";
    public static final String MESSAGE_USAGE = MESSAGE_FORMAT
            + MESSAGE_FUNCTION
            + "Example: load " + COMMAND_WORD + " sugardaddy";

    public static final String MESSAGE_LOAD_QUESTION_SUCCESS = "Loaded questions from %s";

    private final String session;

    public LoadQuestionCommand(String session) {
        this.session = session;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);
        ModelUtil.validateFinalisation(model, DESIRED_MODEL_FINALIZED_STATE);

        File sessionDir = new File(model.getSessionsDirectory().toFile(), session);
        if (!sessionDir.isDirectory()) {
            throw new CommandException(MESSAGE_SESSION_NOT_EXIST);
        }

        Path sessionPath = sessionDir.toPath();
        try {
            Optional<QuestionList> optionalAttributes = storage.readQuestion(sessionPath);
            ObservableList<Question> attributes = optionalAttributes.orElse(new QuestionList()).getObservableList();

            QuestionList currentQuestions = model.getQuestionList();
            currentQuestions.clear();

            for (Question question : attributes) {
                currentQuestions.add(question.toString());
            }

            storage.saveQuestion(currentQuestions);
            return new ToggleCommandResult(String.format(MESSAGE_LOAD_QUESTION_SUCCESS, session), ToggleView.QUESTION);

        } catch (DataConversionException | IllegalValueException | IOException e) {
            throw new CommandException(MESSAGE_NOT_ABLE_TO_CONVERT);
        }
    }
}