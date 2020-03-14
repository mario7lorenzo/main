package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.Question;

public class AddQuestionCommand extends Command {
    public static final String COMMAND_WORD = "question";
    public static final String MESSAGE_DUPLICATE_ATTRIBUTE = "The question already exists.";
    public static final String MESSAGE_SUCCESS = "New question added: %1$s";
    public static final String MESSAGE_USAGE = "new " + COMMAND_WORD + ": Adds an question to the Question list. "
            + "Parameters: "
            + "DESCRIPTION "
            + "Example: new " + COMMAND_WORD + " what is this question?";


    private final Question toAdd;

    /**
     * Creates an AddAttributeCommand to add the specified {@code Attribute}
     */
    public AddQuestionCommand(Question question) {
        requireNonNull(question);
        toAdd = question;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Question> questions = model.getQuestionList();
        if (questions.contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRIBUTE);
        }

        questions.add(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddQuestionCommand // instanceof handles nulls
                && toAdd.equals(((AddQuestionCommand) other).toAdd));
    }
}
