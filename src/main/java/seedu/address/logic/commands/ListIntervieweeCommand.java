package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.Interviewee;

public class ListIntervieweeCommand extends Command {
    public static final String COMMAND_WORD = "interviewee";
    public static final String MESSAGE_EMPTY_LIST = "The list is empty.";
    public static final String MESSAGE_SUCCESS = "Here is the list of interviewees:";
    public static final String MESSAGE_USAGE = "list " + COMMAND_WORD + ": List the interviewee from the Interviewee list. "
            + "Example: list " + COMMAND_WORD;

    /**
     * Creates a ListIntervieweeCommand to list the {@code Interviewee}
     */
    public ListIntervieweeCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Interviewee> interviewees = model.getFilteredIntervieweeList();
        if (interviewees.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }

        String message = MESSAGE_SUCCESS;

        for (int i = 0; i < interviewees.size(); i++) {
            message += String.format("\n%d.%s", i, interviewees.get(i));
        }

        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListIntervieweeCommand);
    }
}
