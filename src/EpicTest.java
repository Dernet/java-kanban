import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    private TaskManager manager;

    @BeforeEach
    public void setUp() {
        manager = new InMemoryTaskManager();
    }

    @Test
    public void testCannotAddEpicAsItsOwnSubtask() {
        Epic epic = new Epic(10, "Epic", "Description");
        // Создаем подзадачу, которая ссылается на тот же ID эпика
        Subtask invalidSubtask = new Subtask(11, "Invalid Subtask", "Desc", epic.getId());

        // Предполагается, что при добавлении подзадачи вызовется проверка
        // и выбросится исключение
        assertThrows(IllegalArgumentException.class, () -> {
            manager.addSubtask(invalidSubtask);
        }, "Добавление эпика в качестве своей подзадачи должно бросать исключение");
    }
}