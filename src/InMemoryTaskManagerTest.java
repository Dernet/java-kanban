import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {

    @Test
    public void testAddDifferentTaskTypesAndFindById() {
        InMemoryTaskManager manager = new InMemoryTaskManager();

        Task task = new Task(1,"Задача 1", "Описание задачи");
        Epic epic = new Epic(2,"Эпик 1", "Описание эпика");
        Subtask subtask = new Subtask(3,"Подзадача 1", "Описание подзадачи", 1);

        manager.addTask(task);
        manager.addEpic(epic);
        manager.addSubtask(subtask);

        assertTrue(manager.getTasks().containsKey(task.getId()));
        assertTrue(manager.getEpics().containsKey(epic.getId()));
        assertTrue(manager.getSubtasks().containsKey(subtask.getId()));

        assertEquals(task, manager.getTaskById(task.id));
        assertEquals(epic, manager.getEpicById(epic.id));
        assertEquals(subtask, manager.getSubtaskById(subtask.id));
    }

    @Test
    public void testIdUniqueness() {
        InMemoryTaskManager manager = new InMemoryTaskManager();

        Task task1 = new Task(1,"Task 1", "Desc");
        manager.addTask(task1);

        // Создаем задачу с явно заданным id, равным уже существующему
        Task task2 = new Task( task1.getId(), "Task 2", "Desc 2");
        manager.addTask(task2);

        // Проверяем, что id уникальны внутри менеджера
        assertNotEquals(task1.getId(), task2.getId());

        // И при этом обе задачи доступны по своим id
        assertEquals(task1, manager.getTaskById(task1.getId()));
        assertEquals(task2, manager.getTaskById(task2.getId()));
    }


}