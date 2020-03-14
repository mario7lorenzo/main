package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.Question;

public class ListQuestionCommand extends Command {
    public static final String COMMAND_WORD = "question";
    public static final String MESSAGE_EMPTY_LIST = "The list is empty.";
    public static final String MESSAGE_SUCCESS = "Here is the list of questions:";
    public static final String MESSAGE_USAGE = "list " + COMMAND_WORD + ": List the questions from the Question list. "
            + "Example: list " + COMMAND_WORD + " what is this question?";

    /**
     * Creates a ListQuestionCommand to list the  {@code Question}
     */
    public ListQuestionCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Question> questions = model.getQuestionList();
        if (questions.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }

        String message = MESSAGE_SUCCESS;

        for (int i = 0; i < questions.size(); i++) {
            message += String.format("\n%d.%s", i, questions.get(i));
        }

        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListQuestionCommand);
    }
}
