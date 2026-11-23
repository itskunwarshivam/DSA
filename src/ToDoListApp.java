import java.util.Scanner;

/**
 * ToDoListApp.java - Main entry point with console-based menu.
 *
 * ╔═══════════════════════════════════════════════════════════════╗
 * ║          TO-DO LIST MANAGER — DSA College Project            ║
 * ╠═══════════════════════════════════════════════════════════════╣
 * ║                                                               ║
 * ║  DSA Concepts Demonstrated:                                   ║
 * ║  ─────────────────────────                                    ║
 * ║  1. Singly Linked List (Custom) — add, delete, traverse      ║
 * ║  2. Java ArrayList              — dynamic array storage      ║
 * ║  3. Bubble Sort                 — sort alphabetically         ║
 * ║  4. Selection Sort              — sort by priority            ║
 * ║  5. Linear Search               — find tasks by title         ║
 * ║                                                               ║
 * ║  Built with Core Java only — no frameworks or libraries.      ║
 * ╚═══════════════════════════════════════════════════════════════╝
 */
public class ToDoListApp {

    // ─── Main Method ───────────────────────────────────────────
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        System.out.println();
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║     WELCOME TO THE TO-DO LIST MANAGER        ║");
        System.out.println("║         DSA Project — Core Java              ║");
        System.out.println("╚═══════════════════════════════════════════════╝");

        boolean running = true;

        while (running) {
            // ── Display Menu ───────────────────────────────────
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│               MAIN MENU                     │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│  1. Add a Task                              │");
            System.out.println("│  2. View All Tasks                          │");
            System.out.println("│  3. Delete a Task                           │");
            System.out.println("│  4. Search for a Task (Linear Search)       │");
            System.out.println("│  5. Mark Task as Completed                  │");
            System.out.println("│  6. Sort Alphabetically (Bubble Sort)       │");
            System.out.println("│  7. Sort by Priority (Selection Sort)       │");
            System.out.println("│  8. Show Task Count                         │");
            System.out.println("│  9. Exit                                    │");
            System.out.println("└─────────────────────────────────────────────┘");
            System.out.print("  Enter your choice (1-9): ");

            // ── Read User Input ────────────────────────────────
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  ✗ Invalid input! Please enter a number (1-9).");
                continue;
            }

            // ── Process Choice ─────────────────────────────────
            switch (choice) {

                case 1: // ── ADD TASK ───────────────────────────
                    System.out.println("\n  ── Add a New Task ──");
                    System.out.print("  Enter task title: ");
                    String title = scanner.nextLine().trim();

                    if (title.isEmpty()) {
                        System.out.println("  ✗ Task title cannot be empty!");
                        break;
                    }

                    int priority = 0;
                    while (priority < 1 || priority > 5) {
                        System.out.print("  Enter priority (1=Highest to 5=Lowest): ");
                        try {
                            priority = Integer.parseInt(scanner.nextLine().trim());
                            if (priority < 1 || priority > 5) {
                                System.out.println("  ✗ Priority must be between 1 and 5!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("  ✗ Please enter a valid number!");
                        }
                    }

                    manager.addTask(title, priority);
                    System.out.println("  ✓ Task added successfully!");
                    break;

                case 2: // ── VIEW TASKS ─────────────────────────
                    manager.viewAllTasks();
                    break;

                case 3: // ── DELETE TASK ────────────────────────
                    System.out.println("\n  ── Delete a Task ──");
                    System.out.print("  Enter the title of the task to delete: ");
                    String deleteTitle = scanner.nextLine().trim();

                    if (deleteTitle.isEmpty()) {
                        System.out.println("  ✗ Title cannot be empty!");
                        break;
                    }

                    manager.deleteTask(deleteTitle);
                    break;

                case 4: // ── SEARCH TASK (Linear Search) ───────
                    System.out.println("\n  ── Search for a Task ──");
                    System.out.print("  Enter the title to search: ");
                    String searchTitle = scanner.nextLine().trim();

                    if (searchTitle.isEmpty()) {
                        System.out.println("  ✗ Title cannot be empty!");
                        break;
                    }

                    manager.searchTask(searchTitle);
                    break;

                case 5: // ── MARK COMPLETED ────────────────────
                    System.out.println("\n  ── Mark Task as Completed ──");
                    System.out.print("  Enter the title of the task to complete: ");
                    String completeTitle = scanner.nextLine().trim();

                    if (completeTitle.isEmpty()) {
                        System.out.println("  ✗ Title cannot be empty!");
                        break;
                    }

                    manager.completeTask(completeTitle);
                    break;

                case 6: // ── BUBBLE SORT (Alphabetical) ────────
                    manager.sortByTitleBubbleSort();
                    break;

                case 7: // ── SELECTION SORT (By Priority) ──────
                    manager.sortByPrioritySelectionSort();
                    break;

                case 8: // ── SHOW COUNT ─────────────────────────
                    System.out.println("\n  ── Task Count ──");
                    manager.showTaskCount();
                    break;

                case 9: // ── EXIT ───────────────────────────────
                    running = false;
                    System.out.println("\n╔═══════════════════════════════════════════════╗");
                    System.out.println("║      Thank you for using ToDo Manager!        ║");
                    System.out.println("║              Goodbye! :)                      ║");
                    System.out.println("╚═══════════════════════════════════════════════╝");
                    break;

                default:
                    System.out.println("  ✗ Invalid choice! Please select 1-9.");
            }
        }

        scanner.close();
    }
}
