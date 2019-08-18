import java.util.*;
public class mazeRunner {

    public static void main(String [] args) {
        Maze myMap = new Maze();
        int moves = 0;
        intro();
        do {
            userMove();
            movesMessage(++moves);
        } while ( !myMap.didIWin() );

        System.out.println("Congratulations, You made it out alive");
        System.out.println("and you did it in " + moves + " moves");
    }

    public static void intro () {
        Maze myMap = new Maze();
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position: ");
        myMap.printMap();
    }

    public static void userMove() {
        Maze myMap = new Maze();
        Scanner input = new Scanner(System.in);
        System.out.print("Where would you like to move? (R, L, U, D) ");
        String move = input.next();

        // validating the user input
        while ( !move.equals("R") && !move.equals("L") && !move.equals("U") && !move.equals("D")) {
            System.out.print("Enter correct choice (R, L, U, D): ");
            move = input.next();
        }

        if ( myMap.isThereAPit(move) ) { // if pit found then go to navigatePit()
            navigatePit(move);
        }
        else { // if there is no pit simply check the move and move in the direction or give error if wall is found
            if ( move.equals("R") ) {
                if ( myMap.canIMoveRight() ) {
                    myMap.moveRight();
                    myMap.printMap();
                } else {
                    System.out.println("Sorry, you have hit a wall.");
                }
            } else if ( move.equals("L") ) {
                if ( myMap.canIMoveLeft() ) {
                    myMap.moveLeft();
                    myMap.printMap();
                } else {
                    System.out.println("Sorry, you have hit a wall.");
                }
            } else if ( move.equals("U") ) {
                if ( myMap.canIMoveUp() ) {
                    myMap.moveUp();
                    myMap.printMap();
                } else {
                    System.out.println("Sorry, you have hit a wall.");
                }
            } else { // ( move.equals("D") )
                if (myMap.canIMoveDown()) {
                    myMap.moveDown();
                    myMap.printMap();
                } else {
                    System.out.println("Sorry, you have hit a wall.");
                }
            }
        }
    }

    public static void movesMessage (int moves) {
        if (moves == 50) {
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        } else if (moves == 75) {
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        } else if (moves == 90){
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        } else if (moves == 100) {
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
            java.lang.System.exit(1);
        }
    }

    public static void navigatePit(String move) {
        Maze myMap = new Maze();
        Scanner input = new Scanner(System.in);
        System.out.println("Watch out! there's a pit ahead, jump it? ");
        String jump = input.next();
        if (jump.charAt(0) == 'y' || jump.charAt(0) == 'Y') {
            myMap.jumpOverPit(move);
            myMap.printMap();
        } else {
            userMove();
        }
    }
}
