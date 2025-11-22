# ✅ Pro Java To-Do App

A professional, resume-worthy To-Do application built with **Java Swing**, **FlatLaf**, and **SQLite**.

---

## ✨ Features

- **Modern UI**: Sleek Dark Mode using [FlatLaf](https://www.formdev.com/flatlaf/).
- **Persistence**: Tasks are saved in a local SQLite database (`todo.db`).
- **Task Management**: Add, Delete, and Mark tasks as Completed.
- **Priorities**: Organize tasks by High, Medium, or Low priority.
- **Filtering**: Quickly view tasks by priority level.
- **MVC Architecture**: Clean separation of concerns (Model-View-Controller).

---

## 🛠 Tech Stack

- **Language**: Java 17+
- **UI Framework**: Swing + FlatLaf
- **Database**: SQLite
- **Build Tool**: Maven
- **Testing**: JUnit 5

---

## 🚀 How to Run

### Prerequisites
- Java JDK 17 or higher
- Maven

### Build & Run
1. **Clone the repository**:
   ```bash
   git clone https://github.com/hariPrasathK-Dev/swing-todo-app.git
   cd swing-todo-app
   ```

2. **Build the project**:
   ```bash
   mvn clean package
   ```

3. **Run the application**:
   ```bash
   java -jar target/swing-todo-app-1.0-SNAPSHOT.jar
   ```

---

## 📁 Project Structure

```
src/main/java/com/todo/
├── Main.java           # Entry point
├── model/              # Data models (Task)
├── view/               # UI Components (MainFrame, Dialogs)
├── controller/         # Business Logic
├── dao/                # Data Access Objects (Database)
└── db/                 # Database Connection
```

---

## 🙋‍♂️ Author

Made with ❤️ by **[!HARI PRASATH K](https://github.com/hariPrasathK-Dev)**