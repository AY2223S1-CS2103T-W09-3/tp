package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.model.team.TeamName;
import seedu.address.model.team.Team;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Team> PREDICATE_SHOW_ALL_TEAMS = unused -> true;
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);


    public boolean hasTeam(Team team);

    public void deleteTeam(Team team);

    public void addTeam(Team team);

    public void setTeamName(Index targetIndex, TeamName newTeamTeamName);

    public void addPersonToTeam(Person person, Team team);

    public void removePersonFromTeam(Person person, Team team);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    public Person getPerson(PersonName personName);

    public PersonName getPersonName(Index personIndex);

    public Person getPersonUsingIndex(Index personIndex);

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    void updateFilteredTeamList(Predicate<Team> predicate);

    void updateFilteredTaskList(Predicate<Task> predicate);

    ObservableList<Team> getFilteredTeamList();

    public Team getTeam(TeamName teamName);

    public boolean teamNameExists(TeamName teamName);

    ObservableList<Task> getFilteredTaskList();

    public TeamName getTeamName(Index teamIndex);

    public Team getTeamUsingIndex(Index teamIndex);

    public List<Team> teamsWithMember(Person p);

    public boolean teamHasMember(Index p, Index t);

    boolean teamHasTask(Index index, Task task);

    void addTask(Index index, Task task);

    void markTask(Index teamIndex, Index taskIndex);

    void unmarkTask(Index teamIndex, Index taskIndex);

    void deleteTask(Index teamIndex, Index taskIndex);

    void editTask(Index teamIndex, Index taskIndex, TaskName newTaskName, LocalDate newDeadline);

}
