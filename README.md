# 📝 To-Do List Manager (DSA Console Application)

A console-based Task and To-Do List Manager implemented in **Core Java** demonstrating fundamental **Data Structures and Algorithms (DSA)** concepts without any external frameworks or libraries.

---

## 📌 Features & DSA Concepts Demonstrated

| Feature / Concept | Implementation Details | Time Complexity |
|---|---|---|
| **Custom Singly Linked List** | Hand-crafted node-based list with `addFirst`, `addLast`, delete, traverse, and search operations | Insert: $O(1)$ / $O(n)$, Delete: $O(n)$, Traverse: $O(n)$ |
| **Java ArrayList** | Dynamic array used as dual/secondary storage for direct comparison | Insert: Amortized $O(1)$, Access: $O(1)$ |
| **Linear Search** | Search tasks by title across nodes | $O(n)$ |
| **Bubble Sort** | Sort tasks alphabetically by title | $O(n^2)$ |
| **Selection Sort** | Sort tasks by priority ($1 = \text{Highest}$ to $5 = \text{Lowest}$) | $O(n^2)$ |
| **Status Management** | Mark tasks as completed/pending | $O(n)$ |

---

## 📂 Project Structure

```text
todo list/
├── src/
│   ├── Task.java               # Task model (title, priority, completion status)
│   ├── SinglyLinkedList.java   # Custom Singly Linked List data structure
│   ├── TaskManager.java        # Controller managing both LinkedList & ArrayList
│   └── ToDoListApp.java        # Console UI and main application entry point
├── out/                        # Compiled .class bytecode files
└── README.md                   # Project documentation & run guide
```

---

## ⚙️ Prerequisites

- **Java Development Kit (JDK)**: JDK 8 or higher (JDK 17, 21, or 25 recommended)
- **Terminal / Shell**: Command Prompt, PowerShell, or Git Bash

To verify if Java is installed on your computer, run:
```sh
java -version
javac -version
```

---

## 🚀 How to Run the Project

### Option 1: Quick Run (Using Existing Compiled Files)

Compiled `.class` files are already located in the `out/` directory:

1. Open your terminal (PowerShell or Command Prompt).
2. Navigate to the project root folder:
   ```powershell
   cd "c:\Users\SHIVAM\Downloads\todo list"
   ```
3. Run the application:
   ```powershell
   java -cp out ToDoListApp
   ```

---

### Option 2: Compile & Run from Source (`src/`)

If you modify any `.java` files, recompile and run:

1. Navigate to the project directory:
   ```powershell
   cd "c:\Users\SHIVAM\Downloads\todo list"
   ```

2. Compile all source files into the `out` folder:
   ```powershell
   javac -d out src/*.java
   ```

3. Run the compiled application:
   ```powershell
   java -cp out ToDoListApp
   ```

---

### Option 3: Run Directly with Java 11+ (Single Command)

If using modern Java, you can compile and run directly from the `src` directory:

```powershell
cd "c:\Users\SHIVAM\Downloads\todo list\src"
javac *.java
java ToDoListApp
```

---

## 🎮 How to Use (Menu Navigation)

When you run the program, the following interactive menu will be displayed:

```text
┌─────────────────────────────────────────────┐
│               MAIN MENU                     │
├─────────────────────────────────────────────┤
│  1. Add a Task                              │
│  2. View All Tasks                          │
│  3. Delete a Task                           │
│  4. Search for a Task (Linear Search)       │
│  5. Mark Task as Completed                  │
│  6. Sort Alphabetically (Bubble Sort)       │
│  7. Sort by Priority (Selection Sort)       │
│  8. Show Task Count                         │
│  9. Exit                                    │
└─────────────────────────────────────────────┘
```

### Steps:
1. **Add Task (`1`)**: Enter task title and select a priority level from `1` (Highest) to `5` (Lowest).
2. **View Tasks (`2`)**: Displays tasks stored in both the Custom Linked List and the Java `ArrayList` side-by-side.
3. **Delete Task (`3`)**: Provide the exact task title to remove it from storage.
4. **Search Task (`4`)**: Uses Linear Search to locate a task by title.
5. **Mark Completed (`5`)**: Toggle task state to completed `[✓]`.
6. **Sort Alphabetically (`6`)**: Applies Bubble Sort on task titles ($A \rightarrow Z$).
7. **Sort by Priority (`7`)**: Applies Selection Sort based on priority ($1 \rightarrow 5$).
8. **Show Count (`8`)**: Shows total active tasks.
9. **Exit (`9`)**: Gracefully terminates the application.