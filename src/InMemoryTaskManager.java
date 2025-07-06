import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    HashMap<Integer, Task> tasks;
    HashMap<Integer, Subtask> subtasks;
    HashMap<Integer, Epic> epics;
    public static int counter;
    private HistoryManager historyManager;

    public InMemoryTaskManager() {
        this.tasks = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.epics = new HashMap<>();
        counter = 1;
        this.historyManager = Managers.getDefaultHistory();
    }

    @Override
    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }


    private int generateId() {
        return counter++;
    }

    @Override
    public void deleteTasks() {
        tasks.clear();
    }

    @Override
    public Task getTaskById(int id) {
        Task task = tasks.get(id);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public void addTask(Task task) {
        int id = generateId();
        task.setId(id);
        tasks.put(id, task);
    }

    @Override
    public void updateTask(Task task) {
        int id = task.getId();
        if (tasks.containsKey(id)) {
            tasks.put(id, task);
        }
    }

    @Override
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    @Override//Методы для эпиков
    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

    @Override
    public void addEpic(Epic epic) {
        int id = generateId();
        epic.setId(id);
        epics.put(id, epic);
    }

    @Override
    public void updateEpic(Epic epic) {
        int id = epic.getId();
        if (epics.containsKey(id)) {
            epics.put(id, epic);
        }
    }

    @Override
    public void deleteEpicById(int id) {
        epics.remove(id);
    }

    @Override//методы для Подзадач
    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        if (subtask != null) {
            historyManager.add(subtask);
        }
        return subtask;
    }

    @Override
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

    @Override
    public void updateSubtask(Subtask subtask) {
        int id = subtask.getId();
        if (subtasks.containsKey(id)) {
            subtasks.put(id, subtask);
            updateEpicStatus(subtask.getEpicId());
        }
    }

    @Override
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

    @Override
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

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}

