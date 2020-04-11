package hirelah.logic.commands;

import hirelah.commons.exceptions.DataConversionException;
import hirelah.commons.exceptions.IllegalValueException;
import hirelah.commons.util.ModelUtil;
import hirelah.logic.commands.exceptions.CommandException;
import hirelah.model.Model;
import hirelah.model.hirelah.Attribute;
import hirelah.model.hirelah.AttributeList;
import hirelah.storage.Storage;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class LoadAttributeCommand extends Command {
    public static final String COMMAND_WORD = "attribute";
    public static final boolean DESIRED_MODEL_FINALIZED_STATE = false;
    public static final String MESSAGE_FORMAT = "load " + COMMAND_WORD + " <session>";
    public static final String MESSAGE_FUNCTION = ": Loads the attributes from another session\n";
    public static final String MESSAGE_SESSION_NOT_EXIST = "The session does not exist";
    public static final String MESSAGE_NOT_ABLE_TO_CONVERT = "The attribute list is failed to be converted";
    public static final String MESSAGE_USAGE = MESSAGE_FORMAT
            + MESSAGE_FUNCTION
            + "Example: load " + COMMAND_WORD + " sugardaddy";

    public static final String MESSAGE_LOAD_ATTRIBUTE_SUCCESS = "Loaded attributes from %s";

    private final String session;

    public LoadAttributeCommand(String session) {
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
            Optional<AttributeList> optionalAttributes = storage.readAttribute(sessionPath);
            ObservableList<Attribute> attributes = optionalAttributes.orElse(new AttributeList()).getObservableList();

            AttributeList currentAttributes = model.getAttributeList();
            currentAttributes.clear();

            for (Attribute attribute : attributes) {
                currentAttributes.add(attribute.toString());
            }

            storage.saveAttribute(currentAttributes);
            return new ToggleCommandResult(String.format(MESSAGE_LOAD_ATTRIBUTE_SUCCESS, session), ToggleView.ATTRIBUTE);

        } catch (DataConversionException | IllegalValueException | IOException e) {
            throw new CommandException(MESSAGE_NOT_ABLE_TO_CONVERT);
        }
    }
}