import java.util.List;
import java.util.Map;

public interface TaskManager {
    Map<Integer, Task> getTasks();
    void deleteTasks();
    Task getTaskById(int id);
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTaskById(int id);

    // Методы для эпиков
    Map<Integer, Epic> getEpics();
    void deleteAllEpics();
    Epic getEpicById(int id);
    void addEpic(Epic epic);
    void updateEpic(Epic epic);
    void deleteEpicById(int id);

    // Методы для подзадач
    Map<Integer, Subtask> getSubtasks();
    void deleteAllSubtasks();
    Subtask getSubtaskById(int id);
    void addSubtask(Subtask subtask);
    void updateSubtask(Subtask subtask);
    void deleteSubtaskById(int id);
    List<Subtask> getSubtasksOfEpic(int epicId);

    public List<Task> getHistory();
}
