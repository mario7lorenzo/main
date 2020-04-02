package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.hirelah.Interviewee;

/**
 * Panel containing the list of interviewees.
 */
public class BestIntervieweeListPanel extends IntervieweeListPanel {
    public BestIntervieweeListPanel(ObservableList<Interviewee> intervieweeList, CommandExecutor commandExecutor) {
        super(intervieweeList, commandExecutor);
        title.setText("Best Interviewees");
    }
}
