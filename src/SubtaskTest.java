import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    private TaskManager manager;

    @BeforeEach
    public void setUp() {
        manager = new InMemoryTaskManager();
    }

    @Test
    public void testSubtaskCannotBeItsOwnEpic() {
        Epic epic = new Epic(1, "Epic", "Description");
        Subtask invalidSubtask = new Subtask(2, "Subtask", "Desc", epic.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            manager.addSubtask(invalidSubtask);
        });
    }

}