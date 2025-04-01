# Minesweeper - Developed By NIKHIL PAVAN



## Description
This is a simple implementation of the Minesweeper game in Java using Spring Boot. The game allows the user to specify the size of the grid and the number of mines. The objective is to reveal all the squares that do not contain mines.

## Design
- SOLID principles are applied in the Minesweeper game design:
	#Single Responsibility Principle (SRP)
		Board Class: This class is responsible for the game logic, including initializing the board, placing mines, calculating adjacent mine counts, revealing cells, and checking win conditions.
		Minesweeper Class: This class handles user interaction and game flow, such as starting the game, taking user input, and displaying the game state.
	#Open/Closed Principle (OCP)
		The classes are designed to be open for extension but closed for modification. For example, if we want to add new features or change the way the board is initialized, we can extend the Board class without modifying its existing code.
	#Liskov Substitution Principle (LSP)
		The design ensures that any future subclasses of Board or Minesweeper can be used interchangeably without altering the correctness of the program.
	#Dependency Inversion Principle (DIP)
		The Minesweeper class depends on the Board class through composition, not on low-level details. This allows for easier testing and future modifications.
- The game uses a console-based interface for user input and output.
- The maximum number of mines is limited to 35% of the total squares to ensure a playable game.

## Environment
- Java 17
- Spring Boot 2.7.5
- Maven
- Operating System: Windows

## Instructions to Run the Application
1. Ensure you have Java 17 and Maven installed on your system.
2. Clone the repository:   git clone <repository-url>
3. Navigate to the project directory:
4. cd minesweeper
5. Build the project using Maven:
6. mvn clean install
7. Run the application:
8. mvn spring-boot:run


