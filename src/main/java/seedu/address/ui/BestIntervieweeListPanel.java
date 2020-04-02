package seedu.address.ui;

import javafx.collections.ObservableList;
import seedu.address.model.hirelah.Interviewee;

/**
 * Panel containing the list of best N interviewees.
 */
public class BestIntervieweeListPanel extends IntervieweeListPanel {
    public BestIntervieweeListPanel(ObservableList<Interviewee> intervieweeList, CommandExecutor commandExecutor) {
        super(intervieweeList, commandExecutor);
        title.setText("Best Interviewees");
    }
}
