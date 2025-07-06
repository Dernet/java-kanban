import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {

    @Test
    public void testTaskImmutabilityAfterAdding() {
        // Создаем задачу
        Task originalTask = new Task(1, "Initial Name", "Initial Description");

        // Создаем менеджер истории
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        // Добавляем задачу в историю
        historyManager.add(originalTask);

        // Изменяем исходную задачу
        originalTask.setName("Changed Name");
        originalTask.setDescription("Changed Description");

        // Получаем историю
        List<Task> history = historyManager.getHistory();

        // Проверяем, что в истории первая задача не изменилась
        Task storedTask = history.get(0);
        assertEquals("Initial Name", storedTask.getName(), "Имя задачи в истории должно остаться прежним");
        assertEquals("Initial Description", storedTask.getDescription(), "Описание задачи в истории должно остаться прежним");
    }

    @Test
    public void testTaskIsSavedAsCopyAndDoesNotChange() {
        // Создаём исходную задачу
        Task originalTask = new Task(1, "Initial Name", "Initial Description");

        // Создаём менеджер истории
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        // Добавляем задачу в историю
        historyManager.add(originalTask);

        // Изменяем исходную задачу
        originalTask.setName("Changed Name");
        originalTask.setDescription("Changed Description");
        originalTask.setStatus(Status.IN_PROGRESS);

        // Получаем историю
        List<Task> history = historyManager.getHistory();

        // Проверяем, что сохранённая задача в истории осталась неизменной
        Task savedTask = history.get(0);
        assertEquals("Initial Name", savedTask.getName(), "Имя задачи в истории должно остаться прежним");
        assertEquals("Initial Description", savedTask.getDescription(), "Описание задачи в истории должно остаться прежним");
        assertEquals(Status.NEW, savedTask.getStatus(), "Статус задачи в истории должен остаться прежним");
    }
}