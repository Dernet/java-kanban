import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    public void testTaskEqualityById() {
        Task task1 = new Task(1, "Task1", "Description1");
        Task task2 = new Task(1, "Task2", "Description2");
        assertEquals(task1, task2, "Задачи с одинаковым id должны быть равны");
    }

    @Test
    public void testInheritanceTaskEqualityById() {
        Epic epic1 = new Epic(2, "Epic1", "Description");
        Epic epic2 = new Epic(2, "Epic2", "Description");
        assertEquals(epic1, epic2, "Эпики с одинаковым id должны быть равны");

        Subtask subtask1 = new Subtask(3, "Subtask1", "Description", epic1.getId());
        Subtask subtask2 = new Subtask(3, "Subtask2", "Description", epic2.getId());
        assertEquals(subtask1, subtask2, "Подзадачи с одинаковым id должны быть равны");
    }
}