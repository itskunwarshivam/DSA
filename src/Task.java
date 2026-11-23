/**
 * Task.java - Model class representing a single To-Do task.
 * 
 * This is a simple POJO (Plain Old Java Object) that holds
 * the data for each task in our To-Do List Manager.
 * 
 * DSA Relevance: This acts as the "data" stored inside each
 * node of our Singly Linked List and each element of our ArrayList.
 */
public class Task {

    // ─── Fields ────────────────────────────────────────────────
    private String title;       // Name / description of the task
    private int priority;       // Priority level: 1 (High) to 5 (Low)
    private boolean completed;  // Whether the task has been finished

    // ─── Constructor ───────────────────────────────────────────
    /**
     * Creates a new Task with the given title and priority.
     * By default, a new task is NOT completed.
     *
     * @param title    the description of the task
     * @param priority priority from 1 (highest) to 5 (lowest)
     */
    public Task(String title, int priority) {
        this.title = title;
        this.priority = priority;
        this.completed = false; // new tasks start as incomplete
    }

    // ─── Getters & Setters ─────────────────────────────────────

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // ─── Display Helper ────────────────────────────────────────
    /**
     * Returns a nicely formatted string representation of the task.
     * Example: "[✗] Buy groceries  (Priority: 2)"
     *          "[✓] Submit homework  (Priority: 1)"
     */
    @Override
    public String toString() {
        String status = completed ? "[Done]" : "[Pending]";
        return status + " " + title + "  (Priority: " + priority + ")";
    }
}
