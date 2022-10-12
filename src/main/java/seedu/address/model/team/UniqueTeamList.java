package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.task.Task;
import seedu.address.model.team.exceptions.DuplicateTeamException;
import seedu.address.model.team.exceptions.TeamNotFoundException;


/**
 * A list of teams that enforces uniqueness between its elements and does not allow nulls.
 * A team is considered unique by comparing using {@code team#isSameTeam(Team)}. As such, adding and updating of
 * teams uses Team#isSameTeam(Tea,) for equality so as to ensure that the team being added or updated is
 * unique in terms of identity in the UniqueTeamList.
 *
 * Supports a minimal set of list operations.
 *
 * @see Team#isSameTeam(Team)
 */
public class UniqueTeamList implements Iterable<Team> {

    private final ObservableList<Team> internalList = FXCollections.observableArrayList();
    private final ObservableList<Team> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Team toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTeam);
    }

    /**
     * Adds a team to the team list
     * The team must not already exist in the team list.
     */
    public void add(Team toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTeamException();
        }
        internalList.add(toAdd);
    }

    /**
     * Returns the {@code Team} at the specified index.
     *
     * @return {@code Team} at the specified index.
     */
    public Team get(int index) {
        return internalList.get(index);
    }

    public Team getTeam(Name teamName) {
        requireNonNull(teamName);
        for (int i = 0; i < internalList.size(); i++) {
            Team team = internalList.get(i);
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        throw new TeamNotFoundException();
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Team toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setTeams(UniqueTeamList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTeams(List<Team> teams) {
        requireAllNonNull(teams);
        if (!teamsAreUnique(teams)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(teams);
    }

    /**
     * Adds the {@code Task} into the {@code Team} at the specified index.
     *
     */
    public void addTask(int index, Task task) {
        internalList.get(index).addTask(task);
    }

    /**
     * Marks the {@code Task} from the {@code Team} at the specified index as done.
     *
     */
    public void markTask(int teamIndex, int taskIndex) {
        internalList.get(teamIndex).markTask(taskIndex);
    }

    /**
     * Marks the {@code Task} from the {@code Team} at the specified index as not done.
     *
     */
    public void unmarkTask(int index, Task task) {
        internalList.get(index).unmarkTask(task);
    }

    /**
     * Deletes the {@code Task} from the {@code Team} at the specified index.
     *
     */
    public void deleteTask(int teamIndex, int taskIndex) {
        internalList.get(teamIndex).deleteTask(taskIndex);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Team> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Team> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTeamList // instanceof handles nulls
                && internalList.equals(((UniqueTeamList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean teamsAreUnique(List<Team> teams) {
        for (int i = 0; i < teams.size() - 1; i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                if (teams.get(i).isSameTeam(teams.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
