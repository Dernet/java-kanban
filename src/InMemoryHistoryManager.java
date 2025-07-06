import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final LinkedList<Task> history = new LinkedList<>();
    private static final int MAX_SIZE = 10;

    @Override
    public void add(Task task) {
        history.addFirst(task.clone());
        if (history.size() > MAX_SIZE) {
            history.removeLast();
        }
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}