package seedu.address.storage;

import java.time.LocalDate;
import java.util.Optional;
import java.util.OptionalInt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    private final String name;
    private final LocalDate deadline;
    private final Boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}, {@code deadline}, and {@code isDone}
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("deadline") LocalDate deadline,
                           @JsonProperty("isDone") boolean isDone) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        this.name = source.getName().fullName;
        this.deadline = source.getDeadline().orElse(null);
        this.isDone = source.getIsDone();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Task toModelType() throws IllegalValueException {
        return new Task(new Name(name), deadline, isDone);
    }
}
