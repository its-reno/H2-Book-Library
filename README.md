## Library Manager Application

This is a **Java-based library management system** designed for managing books, users, and borrowing records. The application features a **console-based interface** for interacting with the library database, allowing users to perform various administrative and operational tasks such as adding books, managing users, and tracking borrowing history. It demonstrates skills in Java programming, database integration, and object-oriented design.

## Features

### Core Functionalities

1. **Book Management**:
    
    - Add new books to the library database.
    - Remove books from the library.
    - Update book availability (e.g., mark as available/unavailable).
    - Query the books database to view all or specific entries.
    - List all currently available books.
    
2. **User Management**:
    
    - Add new users to the system.
    - Query and list all registered users.
    
3. **Borrowing System**:
    
    - Record when a user borrows a book.
    - Record when a user returns a book.
    - View active borrowing records.
    - Fetch borrowing history for all users.
    
4. **Menu-Driven Console Interface**:
    
    - A user-friendly menu allows users to interact with the system by selecting options such as querying books, adding/removing entities, or viewing borrowing records.
    

### Technical Features

- **Database Integration**:
    
    - Uses an H2 in-memory database for storing books, users, and borrowing records.
    - SQL-based operations are abstracted into service classes (e.g., `BookService`, `UserService`, `BorrowService`) for modularity and maintainability.
    
- **Object-Oriented Design**:
    
    - POJO classes (`Book`, `User`, `Borrow`) represent core entities with attributes and methods.
    - Service classes handle CRUD operations for each entity.
    
- **Error Handling**:
    
    - Handles invalid inputs gracefully (e.g., incorrect menu choices or malformed data).
    - Provides feedback to the user for successful or failed operations.
    

## Code Structure

### Main Entry Point

- **`MainApp.java`**:
    
    - Initializes the application by connecting to the H2 database and setting up required services (`BookService`, `UserService`, `BorrowService`).
    - Starts the main menu loop using `LibraryServices`.
    

### Library Services

- **`LibraryServices.java`**:
    
    - Provides the main menu interface for interacting with the application.
    - Handles user input and delegates tasks to appropriate service classes based on menu choices.
    - Implements core workflows such as adding/removing books, updating availability, recording borrows/returns, etc.
    

### Database Services

- **`BookService.java`**:
    
    - Handles all book-related database operations (e.g., adding, removing, querying books).
    - Includes methods for fetching available books and updating book availability status.
    
- **`UserService.java`**:
    
    - Manages user-related operations such as adding new users or retrieving user lists.
    
- **`BorrowService.java`**:
    
    - Tracks borrowing activities (e.g., recording borrows/returns) and provides access to active borrow records and history.
    

### Supporting Classes

- **POJO Classes**:
    
    - `Book`: Represents a book with attributes like title, author, year of publication, genre, ISBN, and availability status.
    - `User`: Represents a library user with attributes like name and email address.
    - `Borrow`: Tracks borrow/return activities with attributes like user ID, book ID, and timestamps.
    

### Database Connection

- **`DatabaseConnection.java`**:
    
    - Establishes a connection to the H2 database.
    - Creates necessary tables (`books`, `users`, `borrows`) if they do not already exist.
    

## How It Works

1. **Setup**:
    
    - The application connects to an H2 in-memory database upon startup.
    - If tables do not exist, they are automatically created during initialization.
    
2. **Main Menu**:
    
    - Users interact with the system through a console-based menu that provides options for managing books, users, and borrowing records.
    
3. **Operations**:
    
    - Based on user input, tasks such as adding books/users or recording borrows/returns are performed by invoking methods in service classes.
    
4. **Data Persistence**:
    
    - All data is stored in an H2 database during runtime. Since it is an in-memory database, data is lost when the program exits unless configured otherwise.
    

## Limitations

- Data persistence is limited to runtime due to the use of an in-memory H2 database (can be easily extended to persistent storage if needed).
- No GUI; interaction is limited to console input/output.
- Limited validation for certain inputs (e.g., ensuring valid IDs exist before performing operations).

## How to Run

1. Clone or download this repository.
2. Compile and run the application using your preferred IDE or via command line:

    
    ```
   javac MainApp.java
    java MainApp
    ```
    
- Follow the on-screen instructions to interact with the library system.
