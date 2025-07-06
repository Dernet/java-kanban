import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManagersTest {

    @Test
    public void testManagersAreInitializedAndWorking() {
        // Получаем менеджеры через утилитный класс
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();

        // Проверяем, что менеджеры не null
        assertNotNull(taskManager, "TaskManager должен быть инициализирован");
        assertNotNull(historyManager, "HistoryManager должен быть инициализирован");

        // Создаём задачу и добавляем в менеджер
        Task task = new Task(1, "Test Task", "Description");
        taskManager.addTask(task);

        // Проверяем, что задача добавилась
        assertEquals(1, taskManager.getTasks().size(), "Должна быть добавлена одна задача");

        // Добавляем задачу в историю
        historyManager.add(task);

        // Проверяем, что история не пуста
        assertFalse(historyManager.getHistory().isEmpty(), "История не должна быть пустой");
        assertEquals(task, historyManager.getHistory().get(0), "Задача в истории должна совпадать с добавленной");
    }
}