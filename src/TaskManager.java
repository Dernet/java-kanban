import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> tasks;
    HashMap<Integer, Subtask> subtasks;
    HashMap<Integer, Epic> epics;
    public static int counter;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.epics = new HashMap<>();
        counter = 1;
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    private int generateId() {
        return counter++;
    }

    public void deleteTasks() {
        tasks.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void addTask(Task task) {
        int id = generateId();
        task.setId(id);
        tasks.put(id, task);
    }

    public void updateTask(Task task) {
        int id = task.getId();
        if (tasks.containsKey(id)) {
            tasks.put(id, task);
        }
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    //Методы для эпиков
    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public void deleteAllEpics() {
        epics.clear();
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public void addEpic(Epic epic) {
        int id = generateId();
        epic.setId(id);
        epics.put(id, epic);
    }

    public void updateEpic(Epic epic) {
        int id = epic.getId();
        if (epics.containsKey(id)) {
            epics.put(id, epic);
        }
    }

    public void deleteEpicById(int id) {
        epics.remove(id);
    }

    //методы для Подзадач
    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public void addSubtask(Subtask subtask) {
        int id = generateId();
        subtask.setId(id);
        subtasks.put(id, subtask);
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            epic.addSubtask(subtask);
            updateEpicStatus(epic.getId());
        }
    }

    public void updateSubtask(Subtask subtask) {
        int id = subtask.getId();
        if (subtasks.containsKey(id)) {
            subtasks.put(id, subtask);
            updateEpicStatus(subtask.getEpicId());
        }
    }

    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            int epicId = subtask.getEpicId();
            Epic epic = epics.get(epicId);
            if (epic != null) {
                epic.getSubtasks().remove(subtask);
                updateEpicStatus(epic.getId());
            }
        }
    }

    public ArrayList<Subtask> getSubtasksOfEpic(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(epic.getSubtasks());
    }

    public void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }
        ArrayList<Subtask> subs = epic.getSubtasks();

        if (subs.isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }

        boolean allNew = true;
        boolean allDone = true;
        boolean inProgress = false;

        for (Subtask sub : subs) {
            if (sub.getStatus() != Status.NEW) {
                allNew = false;
            }
            if (sub.getStatus() != Status.DONE) {
                allDone = false;
            }
            if (sub.getStatus() == Status.IN_PROGRESS) {
                inProgress = true;
            }
        }
        if (allDone) {
            epic.setStatus(Status.DONE);
        } else if (allNew) {
            epic.setStatus(Status.NEW);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}
