package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Name;
import seedu.address.model.team.Team;


/**
 * Edits team name identified using it's displayed index from the address book.
 */
public class EditTeamCommand extends Command {

    public static final String COMMAND_WORD = "editteam";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the team details identified by the index number of the required team.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_EDIT_TEAM_SUCCESS = "Edited Team: %1$s";

    private final Index targetIndex;
    private final Name newTeamName;

    public EditTeamCommand(Index targetIndex, Name newTeamName) {
        requireNonNull(targetIndex);
        requireNonNull(newTeamName);

        this.targetIndex = targetIndex;
        this.newTeamName = newTeamName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Team> lastShownList = model.getFilteredTeamList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
        }

        //Team teamToEdit = lastShownList.get(targetIndex.getZeroBased());
        model.setTeamName(targetIndex, newTeamName);
        return new CommandResult(String.format(MESSAGE_EDIT_TEAM_SUCCESS, newTeamName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditTeamCommand // instanceof handles nulls
                && targetIndex.equals(((EditTeamCommand) other).targetIndex)); // state check
    }
}
