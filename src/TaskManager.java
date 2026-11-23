import java.util.ArrayList;

/**
 * TaskManager.java - Core logic for managing tasks.
 *
 * This class ties together ALL the DSA concepts:
 *   1. Custom Singly Linked List  → primary storage
 *   2. Java ArrayList             → secondary storage / backup view
 *   3. Bubble Sort                → sort tasks alphabetically
 *   4. Selection Sort             → sort tasks by priority
 *   5. Linear Search              → find tasks by title
 *
 * It acts as the "controller" between the UI (ToDoListApp) and
 * the data structures (SinglyLinkedList, ArrayList).
 */
public class TaskManager {

    // ─── Data Structures ───────────────────────────────────────
    private SinglyLinkedList linkedList;    // Custom linked list (primary)
    private ArrayList<Task> arrayList;     // Java ArrayList (secondary)

    // ─── Constructor ───────────────────────────────────────────
    public TaskManager() {
        linkedList = new SinglyLinkedList();
        arrayList = new ArrayList<>();
    }

    // ═══════════════════════════════════════════════════════════
    //  ADD TASK — O(n) for linked list addLast, O(1)* for ArrayList
    // ═══════════════════════════════════════════════════════════
    /**
     * Adds a task to BOTH the linked list and the ArrayList.
     *
     * - Linked List addLast(): O(n) — traverses to the end.
     * - ArrayList add():      O(1) amortized — appends at the end.
     *   * Occasionally O(n) when the internal array needs resizing.
     */
    public void addTask(String title, int priority) {
        Task task = new Task(title, priority);
        linkedList.addLast(task);  // add to custom linked list
        arrayList.add(task);       // add to ArrayList
    }

    // ═══════════════════════════════════════════════════════════
    //  VIEW ALL TASKS — O(n)
    // ═══════════════════════════════════════════════════════════
    /**
     * Displays all tasks from BOTH data structures side by side
     * so we can compare them.
     *
     * Time Complexity: O(n) for each display.
     */
    public void viewAllTasks() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║           ALL TASKS — Linked List             ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        linkedList.display();

        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║           ALL TASKS — ArrayList               ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        if (arrayList.isEmpty()) {
            System.out.println("  → The ArrayList is empty.");
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + arrayList.get(i));
            }
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  DELETE TASK — O(n)
    // ═══════════════════════════════════════════════════════════
    /**
     * Deletes a task by title from BOTH data structures.
     *
     * - Linked List deleteByTitle(): O(n) — linear scan.
     * - ArrayList removal:           O(n) — search + shift elements.
     */
    public void deleteTask(String title) {
        // Delete from linked list
        linkedList.deleteByTitle(title);

        // Delete from ArrayList (linear search + remove)
        boolean removed = false;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getTitle().equalsIgnoreCase(title)) {
                arrayList.remove(i); // O(n) due to shifting
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("  → Also removed from ArrayList.");
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  SEARCH TASK (Linear Search) — O(n)
    // ═══════════════════════════════════════════════════════════
    /**
     * Searches for a task by title using LINEAR SEARCH on
     * both the linked list and the ArrayList.
     *
     * LINEAR SEARCH ALGORITHM:
     *   1. Start from the first element.
     *   2. Compare each element with the target.
     *   3. If a match is found, return it.
     *   4. If the end is reached, the element is not present.
     *
     * Time Complexity:
     *   Best Case:    O(1) — target is the first element
     *   Worst Case:   O(n) — target is the last or not present
     *   Average Case: O(n)
     *
     * Space Complexity: O(1) — no extra space needed
     */
    public void searchTask(String title) {
        System.out.println("\n  ── Searching in Linked List ──");
        linkedList.search(title);

        System.out.println("\n  ── Searching in ArrayList (Linear Search) ──");
        boolean found = false;
        for (int i = 0; i < arrayList.size(); i++) {         // O(n)
            if (arrayList.get(i).getTitle().equalsIgnoreCase(title)) {
                System.out.println("  → Found at index " + i + ": " + arrayList.get(i));
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("  → Task \"" + title + "\" not found in ArrayList.");
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  MARK TASK AS COMPLETED — O(n)
    // ═══════════════════════════════════════════════════════════
    /**
     * Marks a task as completed in both data structures.
     * Since the same Task object is stored in both, marking it
     * in one automatically reflects in the other (reference sharing).
     *
     * Time Complexity: O(n) — linear search to find the task.
     */
    public void completeTask(String title) {
        linkedList.markCompleted(title);
        // No need to update ArrayList separately — same Task object reference!
    }

    // ═══════════════════════════════════════════════════════════
    //  BUBBLE SORT (Alphabetical) — O(n²)
    // ═══════════════════════════════════════════════════════════
    /**
     * Sorts a COPY of the tasks alphabetically by title
     * using the BUBBLE SORT algorithm.
     *
     * BUBBLE SORT ALGORITHM:
     *   1. Compare adjacent elements.
     *   2. If they are in the wrong order, swap them.
     *   3. Repeat for all elements (one pass "bubbles" the
     *      largest element to its correct position).
     *   4. Repeat n-1 times (or until no swaps are needed).
     *
     * Optimization: If no swaps occur in a pass, the array
     * is already sorted — we can stop early.
     *
     * Time Complexity:
     *   Best Case:    O(n)   — already sorted (with optimization)
     *   Worst Case:   O(n²)  — sorted in reverse order
     *   Average Case: O(n²)
     *
     * Space Complexity: O(n) for the copy, O(1) extra for sorting
     *
     * Stability: Stable — equal elements maintain relative order.
     */
    public void sortByTitleBubbleSort() {
        Task[] tasks = linkedList.toArray(); // work on a copy
        int n = tasks.length;

        if (n == 0) {
            System.out.println("  → No tasks to sort.");
            return;
        }

        System.out.println("\n  ── Bubble Sort (Alphabetical by Title) ──");
        System.out.println("  Number of tasks: " + n);

        int totalSwaps = 0;
        int totalPasses = 0;

        // Outer loop: each pass places one element in its correct position
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // optimization flag
            totalPasses++;

            // Inner loop: compare adjacent elements
            for (int j = 0; j < n - 1 - i; j++) {
                // Compare titles alphabetically (case-insensitive)
                if (tasks[j].getTitle().compareToIgnoreCase(tasks[j + 1].getTitle()) > 0) {
                    // SWAP: tasks[j] and tasks[j+1]
                    Task temp = tasks[j];
                    tasks[j] = tasks[j + 1];
                    tasks[j + 1] = temp;

                    swapped = true;
                    totalSwaps++;
                }
            }

            // Optimization: if no swaps occurred, array is sorted
            if (!swapped) {
                System.out.println("  → Early termination! No swaps in pass " + totalPasses);
                break;
            }
        }

        // Display sorted result
        System.out.println("  → Sorting complete! Passes: " + totalPasses + ", Swaps: " + totalSwaps);
        System.out.println("  ┌─────────────────────────────────────────────┐");
        System.out.println("  │     SORTED TASKS (Alphabetical — Bubble)    │");
        System.out.println("  ├─────────────────────────────────────────────┤");
        for (int i = 0; i < n; i++) {
            System.out.println("  │ " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("  └─────────────────────────────────────────────┘");
    }

    // ═══════════════════════════════════════════════════════════
    //  SELECTION SORT (By Priority) — O(n²)
    // ═══════════════════════════════════════════════════════════
    /**
     * Sorts a COPY of the tasks by priority (1 = highest first)
     * using the SELECTION SORT algorithm.
     *
     * SELECTION SORT ALGORITHM:
     *   1. Find the MINIMUM element in the unsorted portion.
     *   2. Swap it with the first element of the unsorted portion.
     *   3. Move the boundary of the sorted portion one step right.
     *   4. Repeat until the entire array is sorted.
     *
     * Time Complexity:
     *   Best Case:    O(n²) — always scans the full unsorted portion
     *   Worst Case:   O(n²)
     *   Average Case: O(n²)
     *
     * Space Complexity: O(n) for the copy, O(1) extra for sorting
     *
     * Note: Selection Sort always performs O(n²) comparisons
     * regardless of input, but it minimizes the number of SWAPS
     * (at most n-1 swaps), making it useful when writes are expensive.
     *
     * Stability: NOT stable in this implementation.
     */
    public void sortByPrioritySelectionSort() {
        Task[] tasks = linkedList.toArray(); // work on a copy
        int n = tasks.length;

        if (n == 0) {
            System.out.println("  → No tasks to sort.");
            return;
        }

        System.out.println("\n  ── Selection Sort (By Priority) ──");
        System.out.println("  Number of tasks: " + n);

        int totalSwaps = 0;
        int totalComparisons = 0;

        // Outer loop: move the boundary of sorted/unsorted portions
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // assume current position has the minimum

            // Inner loop: find the actual minimum in unsorted portion
            for (int j = i + 1; j < n; j++) {
                totalComparisons++;

                // Compare priorities (lower number = higher priority)
                if (tasks[j].getPriority() < tasks[minIndex].getPriority()) {
                    minIndex = j; // update minimum index
                }
            }

            // Swap only if we found a smaller element
            if (minIndex != i) {
                Task temp = tasks[i];
                tasks[i] = tasks[minIndex];
                tasks[minIndex] = temp;
                totalSwaps++;
            }
        }

        // Display sorted result
        System.out.println("  → Sorting complete! Comparisons: " + totalComparisons + ", Swaps: " + totalSwaps);
        System.out.println("  ┌─────────────────────────────────────────────┐");
        System.out.println("  │    SORTED TASKS (By Priority — Selection)   │");
        System.out.println("  ├─────────────────────────────────────────────┤");
        for (int i = 0; i < n; i++) {
            System.out.println("  │ " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("  └─────────────────────────────────────────────┘");
    }

    // ═══════════════════════════════════════════════════════════
    //  DISPLAY TASK COUNT — O(n) for linked list, O(1) for ArrayList
    // ═══════════════════════════════════════════════════════════
    /**
     * Shows the number of tasks in both data structures.
     *
     * - Linked List getSize(): O(n)  — counts by traversal
     * - ArrayList size():      O(1)  — maintained internally
     *
     * This demonstrates a key difference: ArrayList tracks its
     * size in a variable, while our linked list counts on demand.
     */
    public void showTaskCount() {
        System.out.println("  → Linked List size : " + linkedList.getSize() + " (counted by traversal — O(n))");
        System.out.println("  → ArrayList size   : " + arrayList.size() + " (stored internally — O(1))");
    }
}
