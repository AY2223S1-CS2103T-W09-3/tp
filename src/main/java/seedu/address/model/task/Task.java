package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Task in the address book.
 */
public class Task {

    private Name name;
    private Optional<LocalDate> deadline;
    private boolean isDone;

    /**
     * Constructs an unfinished {@code Task}.
     *
     * @param name A valid name.
     * @param deadline A valid date or null.
     */
    public Task(Name name, LocalDate deadline) {
        requireAllNonNull(name);
        this.name = name;
        this.deadline = Optional.ofNullable(deadline);
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task} with specified state.
     *
     * @param name A valid name.
     * @param deadline A valid date or null.
     * @param isDone The state.
     */
    public Task(Name name, LocalDate deadline, boolean isDone) {
        requireAllNonNull(name);
        this.name = name;
        this.deadline = Optional.ofNullable(deadline);
        this.isDone = isDone;
    }

    public Name getName() {
        return this.name;
    }

    public void editName(Name newName) {
        this.name = newName;
    }

    public Optional<LocalDate> getDeadline() {
        return this.deadline;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns 'X' if done or " " otherwise.
     * @return String "X" if the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns true if both tasks have the same name and deadline.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && otherTask.getDeadline().equals(getDeadline());
    }

    /**
     * Returns true if both task have the same description, same deadline and both are done or both are not done.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && name.equals(((Task) other).getName())
                && getDeadline().equals(((Task) other).getDeadline())); // state check
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[" + getStatusIcon() + "] " + getName());

        if (!getDeadline().isEmpty()) {
            builder.append(" (by: " + getDeadline().get() + ")");
        }
        return builder.toString();
    }

}
