public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        // Создаем задачи
        Task task1 = new Task(1, "Задача 1", "Описание 1");
        Task task2 = new Task(2, "Задача 2", "Описание 2");
        Task task3 = new Task(3, "Задача 3", "Описание 3");

        // Добавляем задачи
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        // Получаем задачи по id, чтобы добавить их в историю
        taskManager.getTaskById(1);
        taskManager.getTaskById(3);
        taskManager.getTaskById(2);
        taskManager.getTaskById(1);

        // Вывод истории
        System.out.println("История просмотренных задач:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }
    }
    }
