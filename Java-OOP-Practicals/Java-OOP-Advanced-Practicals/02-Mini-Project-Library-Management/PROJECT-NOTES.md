# Library Management System — Project Notes

## Aim
To create a simple console-based library application using Java object-oriented programming concepts.

## Concepts demonstrated
- **Abstraction:** `LibraryItem` is an abstract class.
- **Inheritance:** `Book` extends `LibraryItem`.
- **Interface:** `Book` implements `Borrowable`.
- **Polymorphism:** `LibraryItem` declares `displayDetails()` and `Book` overrides it.
- **Encapsulation:** fields are private and accessed through methods.
- **Collections:** `ArrayList` stores the books.
- **Exception handling:** `LibraryException` handles invalid borrowing/returning operations; input parsing is protected.
- **Multithreading:** a separate welcome thread runs at startup.

## Features
1. Display available and borrowed books.
2. Borrow a book by ID.
3. Return a borrowed book by ID.
4. Handle invalid IDs and invalid menu input.

## How to run
```bash
javac LibraryManagementSystem.java
java LibraryManagementSystem
```

## Scope / limitations
This is a beginner-friendly demonstration project. Data is stored in memory and resets when the program exits. It does not include a database, login system, due dates, or persistent storage.
