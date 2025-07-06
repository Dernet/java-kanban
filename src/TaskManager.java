import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface TaskManager {
    HashMap<Integer, Task> getTasks();
    void deleteTasks();
    Task getTaskById(int id);
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTaskById(int id);

    // Методы для эпиков
    HashMap<Integer, Epic> getEpics();
    void deleteAllEpics();
    Epic getEpicById(int id);
    void addEpic(Epic epic);
    void updateEpic(Epic epic);
    void deleteEpicById(int id);

    // Методы для подзадач
    HashMap<Integer, Subtask> getSubtasks();
    void deleteAllSubtasks();
    Subtask getSubtaskById(int id);
    void addSubtask(Subtask subtask);
    void updateSubtask(Subtask subtask);
    void deleteSubtaskById(int id);
    ArrayList<Subtask> getSubtasksOfEpic(int epicId);

    public List<Task> getHistory();
}
