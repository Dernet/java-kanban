public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Task task1 = new Task(0, "Задача 1", "Описание");
        Task task2 = new Task(0, "Задача 2", "Описание");
        manager.addTask(task1);
        manager.addTask(task2);

        Epic epic1 = new Epic(0, "Эпик 1", "Описание");
        manager.addEpic(epic1);
        Subtask sub1 = new Subtask(0, "Подзадача 1", "Описание", epic1.getId());
        Subtask sub2 = new Subtask(0, "Подзадача 2", "Описание", epic1.getId());
        manager.addSubtask(sub1);
        manager.addSubtask(sub2);

        Epic epic2 = new Epic(0, "Эпик 2", "Описание");
        manager.addEpic(epic2);
        Subtask sub3 = new Subtask(0, "Подзадача 3", "Описание", epic2.getId());
        manager.addSubtask(sub3);

        System.out.println("Задачи:");
        for (Task t : manager.getTasks().values()) {
            System.out.println(t);
        }

        System.out.println("\nЭпики и их подзадачи:");
        for (Epic e : manager.getEpics().values()) {
            System.out.println(e);
            for (Subtask st : e.getSubtasks()) {
                System.out.println("  " + st);
            }
        }

        task1.setStatus(Status.IN_PROGRESS);
        task2.setStatus(Status.DONE);
        manager.updateTask(task1);
        manager.updateTask(task2);

        sub1.setStatus(Status.IN_PROGRESS);
        sub2.setStatus(Status.DONE);
        sub3.setStatus(Status.DONE);
        manager.updateSubtask(sub1);
        manager.updateSubtask(sub2);
        manager.updateSubtask(sub3);

        manager.updateEpic(manager.getEpicById(epic1.getId()));
        manager.updateEpic(manager.getEpicById(epic2.getId()));

        System.out.println("\nПосле обновления статусов подзадач:");
        for (Epic e : manager.getEpics().values()) {
            System.out.println(e);
            for (Subtask st : e.getSubtasks()) {
                System.out.println("  " + st);
            }
        }
    }
}
