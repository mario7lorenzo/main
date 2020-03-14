package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.Attribute;

public class AddAttributeCommand extends Command {
    public static final String COMMAND_WORD = "attribute";
    public static final String MESSAGE_DUPLICATE_ATTRIBUTE = "The attribute already exists.";
    public static final String MESSAGE_SUCCESS = "New attribute added: %1$s";
    public static final String MESSAGE_USAGE = "new " + COMMAND_WORD + ": Adds an attribute to the Attribute list. "
            + "Parameters: "
            + "NAME "
            + "Example: new " + COMMAND_WORD + " leadership";


    private final Attribute toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddAttributeCommand(Attribute attribute) {
        requireNonNull(attribute);
        toAdd = attribute;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Attribute> attributes = model.getAttributeList();
        if (attributes.contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRIBUTE);
        }

        attributes.add(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAttributeCommand // instanceof handles nulls
                && toAdd.equals(((AddAttributeCommand) other).toAdd));
    }
}
