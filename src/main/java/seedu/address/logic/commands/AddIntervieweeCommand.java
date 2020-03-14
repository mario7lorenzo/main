package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.Attribute;
import seedu.address.model.hirelah.Interviewee;

public class AddIntervieweeCommand extends Command {
    public static final String PREFIX_ALIAS = "-a";

    public static final String COMMAND_WORD = "interviewee";
    public static final String MESSAGE_DUPLICATE_ATTRIBUTE = "The interviewee already exists.";
    public static final String MESSAGE_SUCCESS = "New interviewee added: %1$s";
    public static final String MESSAGE_USAGE = "new " + COMMAND_WORD + ": Adds an interviewee to the Interviewee list. "
            + "Parameters: "
            + "NAME "
            + PREFIX_ALIAS + " ALIAS\n"
            + "Example: new " + COMMAND_WORD
            + "Jane Doe "
            + PREFIX_ALIAS + " Doe";

    private final Interviewee toAdd;

    /**
     * Creates an AddIntervieweeCommand to add the specified {@code Interviewee}
     */
    public AddIntervieweeCommand(Interviewee interviewee) {
        requireNonNull(interviewee);
        toAdd = interviewee;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Interviewee> interviewees = model.getFilteredIntervieweeList();
        if (interviewees.contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRIBUTE);
        }

        interviewees.add(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddIntervieweeCommand // instanceof handles nulls
                && toAdd.equals(((AddIntervieweeCommand) other).toAdd));
    }
}
