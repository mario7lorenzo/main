package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTRIBUTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_METRIC;
import static seedu.address.model.hirelah.BestParameter.ATTRIBUTE;
import static seedu.address.model.hirelah.BestParameter.METRIC;
import static seedu.address.model.hirelah.BestParameter.OVERALL;
import java.util.List;
import seedu.address.logic.commands.BestCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class BestParser implements Parser<BestCommand> {
    public static final String MESSAGE_MULTIPLE_PARAMETERS_PROVIDED = "Multiple parameters for comparisons provided.";

    public BestCommand parse(String arguments) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_METRIC, PREFIX_ATTRIBUTE);
        if (arguments.equals("")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BestCommand.MESSAGE_USAGE));
        } else {
            List<String> metricPrefixes = argMultimap.getAllValues(PREFIX_METRIC);
            List<String> attributePrefixes = argMultimap.getAllValues(PREFIX_ATTRIBUTE);

            int numOfParams = getNumberOfTotalParams(metricPrefixes, attributePrefixes);

            if (numOfParams > 1) {
                throw new ParseException(MESSAGE_MULTIPLE_PARAMETERS_PROVIDED);
            } else if (numOfParams == 0) {
                return new BestCommand(argMultimap.getPreamble(),OVERALL);
            } else {
                if (metricPrefixes.size() == 1) {
                    return new BestCommand(argMultimap.getPreamble(), metricPrefixes.get(0), METRIC);
                } else {
                    return new BestCommand(argMultimap.getPreamble(), attributePrefixes.get(0), ATTRIBUTE);
                }
            }
        }
    }

    private int getNumberOfTotalParams(List<String> metricPrefixes, List<String> attributePrefixes) {
        return metricPrefixes.size() + attributePrefixes.size();
    }
}
