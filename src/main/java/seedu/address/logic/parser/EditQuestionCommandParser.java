package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTRIBUTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.address.logic.commands.EditQuestionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditQuestionCommand object
 */
public class EditQuestionCommandParser {
//    private static final Pattern BASIC_EDIT_QUESTION_COMMAND_FORMAT =
//            Pattern.compile("(?<questionNumber>\\S+)(?<newQuestion>.+)");
    private static final String INDEX_NOT_A_NUMBER = "The index is not a number.";

    /**
     * Parses the given {@code String} of arguments in the context of the EditQuestionCommand
     * and returns an EditQuestionCommand object for execution.
     *
     * @param arguments the arguments to be parsed
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditQuestionCommand parse(String arguments) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_QUESTION);

        if (!argMultimap.arePrefixesPresent(PREFIX_QUESTION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditQuestionCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_QUESTION).get().equals("")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditQuestionCommand.MESSAGE_USAGE));
        }
        try {
            int index = Integer.parseInt(argMultimap.getPreamble());
            return new EditQuestionCommand(index, argMultimap.getValue(PREFIX_ATTRIBUTE).get());
        } catch (NumberFormatException e) {
            throw new ParseException(INDEX_NOT_A_NUMBER);
        }
    }
}
