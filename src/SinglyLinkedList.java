/**
 * SinglyLinkedList.java - Custom Singly Linked List implementation.
 *
 * A Singly Linked List is a linear data structure where each element
 * (called a "node") contains:
 *   1. DATA  – the actual value (here, a Task object)
 *   2. NEXT  – a reference (pointer) to the next node in the list
 *
 * The list maintains a HEAD pointer that points to the first node.
 *
 * Visual representation:
 *   HEAD → [Task|•]→ [Task|•]→ [Task|•]→ null
 *
 * ═══════════════════════════════════════════════════════════════
 *  TIME COMPLEXITY SUMMARY  (n = number of nodes in the list)
 * ═══════════════════════════════════════════════════════════════
 *  addFirst()       → O(1)  — always inserts at head
 *  addLast()        → O(n)  — must traverse to the end
 *  deleteByTitle()  → O(n)  — may need to scan entire list
 *  search()         → O(n)  — linear search through nodes
 *  display()        → O(n)  — visits every node once
 *  getSize()        → O(n)  — counts by traversal
 *  toArray()        → O(n)  — copies all nodes to an array
 * ═══════════════════════════════════════════════════════════════
 *  SPACE COMPLEXITY: O(n) for storing n nodes
 * ═══════════════════════════════════════════════════════════════
 */
public class SinglyLinkedList {

    // ─── Inner class: Node ─────────────────────────────────────
    /**
     * Each Node holds one Task and a pointer to the next Node.
     * This is the fundamental building block of the linked list.
     */
    private class Node {
        Task data;   // the task stored in this node
        Node next;   // reference to the next node (null if last)

        Node(Task data) {
            this.data = data;
            this.next = null;
        }
    }

    // ─── Fields ────────────────────────────────────────────────
    private Node head; // points to the first node in the list

    // ─── Constructor ───────────────────────────────────────────
    public SinglyLinkedList() {
        this.head = null; // empty list
    }

    // ═══════════════════════════════════════════════════════════
    //  ADD AT BEGINNING — O(1)
    // ═══════════════════════════════════════════════════════════
    /**
     * Inserts a new task at the BEGINNING of the list.
     *
     * Steps:
     *   1. Create a new node with the given task.
     *   2. Point the new node's "next" to the current head.
     *   3. Update head to point to the new node.
     *
     * Time Complexity: O(1) — no traversal needed.
     */
    public void addFirst(Task task) {
        Node newNode = new Node(task);
        newNode.next = head;  // new node points to old head
        head = newNode;       // head now points to new node
        System.out.println("  → Task added at the beginning: " + task.getTitle());
    }

    // ═══════════════════════════════════════════════════════════
    //  ADD AT END — O(n)
    // ═══════════════════════════════════════════════════════════
    /**
     * Inserts a new task at the END of the list.
     *
     * Steps:
     *   1. Create a new node.
     *   2. If the list is empty, make it the head.
     *   3. Otherwise, traverse to the last node and link it.
     *
     * Time Complexity: O(n) — must walk through all n nodes.
     */
    public void addLast(Task task) {
        Node newNode = new Node(task);

        // Case 1: List is empty
        if (head == null) {
            head = newNode;
        } else {
            // Case 2: Traverse to the last node
            Node current = head;
            while (current.next != null) {
                current = current.next; // move to next node
            }
            current.next = newNode; // link the last node to the new node
        }
        System.out.println("  → Task added at the end: " + task.getTitle());
    }

    // ═══════════════════════════════════════════════════════════
    //  DELETE BY TITLE — O(n)
    // ═══════════════════════════════════════════════════════════
    /**
     * Removes the FIRST task whose title matches (case-insensitive).
     *
     * Steps:
     *   1. If the head matches, move head to head.next.
     *   2. Otherwise, traverse with two pointers (prev & current).
     *   3. When found, bypass the node: prev.next = current.next.
     *
     * Time Complexity: O(n) — may need to check every node.
     */
    public boolean deleteByTitle(String title) {
        if (head == null) {
            System.out.println("  → List is empty. Nothing to delete.");
            return false;
        }

        // Case 1: The head node itself is the target
        if (head.data.getTitle().equalsIgnoreCase(title)) {
            System.out.println("  → Deleted task: " + head.data.getTitle());
            head = head.next; // move head forward
            return true;
        }

        // Case 2: Search for the target in the rest of the list
        Node prev = head;
        Node current = head.next;

        while (current != null) {
            if (current.data.getTitle().equalsIgnoreCase(title)) {
                System.out.println("  → Deleted task: " + current.data.getTitle());
                prev.next = current.next; // bypass the node
                return true;
            }
            prev = current;
            current = current.next;
        }

        System.out.println("  → Task \"" + title + "\" not found.");
        return false;
    }

    // ═══════════════════════════════════════════════════════════
    //  LINEAR SEARCH — O(n)
    // ═══════════════════════════════════════════════════════════
    /**
     * Searches for a task by title using LINEAR SEARCH.
     *
     * Linear Search: Check each element one-by-one from the start
     * until we find a match or reach the end.
     *
     * Time Complexity:
     *   Best Case:  O(1) — found at the head
     *   Worst Case: O(n) — found at the end or not found
     *   Average:    O(n)
     */
    public Task search(String title) {
        Node current = head;
        int position = 1;

        while (current != null) {
            if (current.data.getTitle().equalsIgnoreCase(title)) {
                System.out.println("  → Task found at position " + position + ": " + current.data);
                return current.data;
            }
            current = current.next;
            position++;
        }

        System.out.println("  → Task \"" + title + "\" not found in the list.");
        return null;
    }

    // ═══════════════════════════════════════════════════════════
    //  MARK TASK AS COMPLETED — O(n)
    // ═══════════════════════════════════════════════════════════
    /**
     * Finds a task by title and marks it as completed.
     *
     * Time Complexity: O(n) — linear search through the list.
     */
    public boolean markCompleted(String title) {
        Node current = head;

        while (current != null) {
            if (current.data.getTitle().equalsIgnoreCase(title)) {
                current.data.setCompleted(true);
                System.out.println("  → Task marked as completed: " + current.data);
                return true;
            }
            current = current.next;
        }

        System.out.println("  → Task \"" + title + "\" not found.");
        return false;
    }

    // ═══════════════════════════════════════════════════════════
    //  DISPLAY ALL TASKS — O(n)
    // ═══════════════════════════════════════════════════════════
    /**
     * Prints every task in the list from head to tail.
     *
     * Time Complexity: O(n) — visits each node exactly once.
     */
    public void display() {
        if (head == null) {
            System.out.println("  → The task list is empty.");
            return;
        }

        System.out.println("  ┌─────────────────────────────────────────────┐");
        System.out.println("  │          TASKS (Linked List View)           │");
        System.out.println("  ├─────────────────────────────────────────────┤");

        Node current = head;
        int index = 1;
        while (current != null) {
            System.out.println("  │ " + index + ". " + current.data);
            current = current.next;
            index++;
        }

        System.out.println("  └─────────────────────────────────────────────┘");
    }

    // ═══════════════════════════════════════════════════════════
    //  GET SIZE — O(n)
    // ═══════════════════════════════════════════════════════════
    /**
     * Counts and returns the number of nodes in the list.
     *
     * Time Complexity: O(n) — traverses the full list.
     */
    public int getSize() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // ═══════════════════════════════════════════════════════════
    //  CONVERT TO ARRAY — O(n)
    // ═══════════════════════════════════════════════════════════
    /**
     * Copies all Task objects from the linked list into a Task array.
     * This is useful for sorting (we sort the array, then display it).
     *
     * Time Complexity: O(n) — visits each node once.
     */
    public Task[] toArray() {
        int size = getSize();
        Task[] array = new Task[size];
        Node current = head;
        int i = 0;
        while (current != null) {
            array[i] = current.data;
            current = current.next;
            i++;
        }
        return array;
    }

    /**
     * Checks whether the linked list is empty.
     *
     * Time Complexity: O(1)
     */
    public boolean isEmpty() {
        return head == null;
    }
}
