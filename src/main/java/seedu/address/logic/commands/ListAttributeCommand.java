package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.Attribute;

public class ListAttributeCommand extends Command {
    public static final String COMMAND_WORD = "attribute";
    public static final String MESSAGE_EMPTY_LIST = "The list is empty.";
    public static final String MESSAGE_SUCCESS = "Here is the list of attributes:";
    public static final String MESSAGE_USAGE = "list " + COMMAND_WORD + ": List the attribute from the Attribute list. "
            + "Example: list " + COMMAND_WORD;

    /**
     * Creates a ListAttributeCommand to list the {@code Attribute}
     */
    public ListAttributeCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Attribute> attributes = model.getAttributeList();
        if (attributes.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }

        String message = MESSAGE_SUCCESS;

        for (int i = 0; i < attributes.size(); i++) {
            message += String.format("\n%d.%s", i, attributes.get(i));
        }

        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListAttributeCommand);
    }
}
