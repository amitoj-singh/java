/*Battle Ship game is played between a user and computer.
*User will deploy 5 ships on the 10 X 10 grid and then computer will deploy its 5 ships.
* Computer don't know where are the other player's ships and the player don't know about the computer's ships.
* Player and computer will guess coordinates of each other's ships to sunk their ship.
* The game ends when either the user or the computer loses all the ships.
* Player's ships are represented by '@' symbol.
* Player's missed coordinates are represented by '-' symbol.
* Player's sunk ships are represented by 'x' symbol.
* Computer's sunk ships are represented by '!' symbol.*/

import java.util.*;

public class battleship {
    static char[][] oceanMap = new char[10][10]; // declaring global 2-D array to create ocean grid

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("\nRight now the sea is empty");
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                oceanMap[i][j] = ' '; // initializing the ocean space with empty spaces
        ;
        displayOceanMap();
        getShips();

        int userShips = 5, compShips = 5, x, y;
        while (userShips!=0 && compShips!=0) {
            System.out.println("\nYOUR TURN");
            do {
                System.out.print("Enter X coordinate: ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate: ");
                y = input.nextInt();
                if (x<0 || x>9 || y<0 || y> 9) {
                    System.out.println("Invalid coordinates, try again.");
                } else if (oceanMap[x][y]=='-') {
                    System.out.println("You have already selected this coordinate, try again.");
                } else if (oceanMap[x][y]=='x') {
                    System.out.println("Your ship had sunk here, try again.");
                } else if (oceanMap[x][y]=='!') {
                    System.out.println("You have sunk computer's ship here, try again.");
                }
            } while (x<0 || x>9 || y<0 || y> 9 || oceanMap[x][y]=='-' || oceanMap[x][y]=='x' || oceanMap[x][y]=='!');

            if (oceanMap[x][y] == '2') {
                System.out.println("Boom! You sunk the ship!");
                oceanMap[x][y] = '!';
                compShips--;
            } else if (oceanMap[x][y] == '1') {
                System.out.println("Oh no, you sunk your own ship :(");
                oceanMap[x][y] = 'x';
                userShips--;
            } else {
                System.out.println("Sorry, you missed");
                oceanMap[x][y] = '-';
            }

            System.out.println("\nCOMPUTER'S TURN");
            do {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
            } while (oceanMap[x][y] == '#' || oceanMap[x][y] == '!' || oceanMap[x][y] == 'x');

            if (oceanMap[x][y]=='1') {
                System.out.println("The Computer sunk one of your ships!");
                userShips--;
                oceanMap[x][y] = 'x';
            } else if (oceanMap[x][y] == '2') {
                System.out.println("The Computer sunk one of its own ships");
                compShips--;
                oceanMap[x][y] = '!';
            } else {
                System.out.println("Computer missed");
                oceanMap[x][y] = '#'; // if computer misses the guess
            }
            displayOceanMap();
            System.out.println("Your ships: " + userShips + " | Computer ships: " + compShips);
        }
        if (userShips == 0)
            System.out.println("You lose");
        else
            System.out.println("Hooray! You win the battle :)");
        System.out.println("----------------------------------------");
    }
    // Method to display the current state of the ocean
    public static void displayOceanMap() {
        System.out.println("\n   0-1-2-3-4-5-6-7-8-9   ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < 10; j++) {
                if (oceanMap[i][j] == '1')
                    System.out.print("@ "); // displaying user's ships with '@' symbol.
                else if (oceanMap[i][j] == '2')
                    System.out.print("  "); // hiding computer's ships.
                else if (oceanMap[i][j] == '#')
                    System.out.print("  "); // hiding computer's missed guesses.
                else
                    System.out.print(oceanMap[i][j] + " ");
            }
            System.out.print("|" + i + "\n");
        }
        System.out.println("   0-1-2-3-4-5-6-7-8-9   ");
    }
    // Method to deploy User's and computer's ships
    public static void getShips() {
        //User deploying ships
        Scanner input = new Scanner(System.in);
        System.out.println("\nDeploy your ships:");
        int x, y;
        boolean isXAndYCorrect; // flag to keep track of user's input coordinates

        for (int i = 0; i < 5; i++) {
            do {
                isXAndYCorrect = true;
                System.out.print("Enter X coordinate of ship " + (i + 1) + ": ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate of ship: " + (i + 1) + ": ");
                y = input.nextInt();
                if (x < 0 || x > 9 || y < 0 || y > 9) {
                    System.out.println("Choose coordinates between 0 and 9");
                    isXAndYCorrect = false;
                } else if (oceanMap[x][y] == '1') {
                    System.out.println("You cannot place two ships at one place");
                    isXAndYCorrect = false;
                }
            } while (!isXAndYCorrect);
            oceanMap[x][y] = '1';
        }
        //computer deploying ships
        Random rand = new Random();
        System.out.println("\nComputer is deploying ships:");

        for (int i = 0; i < 5; i++) {
            do {
                x = rand.nextInt(10); // generates random number
                y = rand.nextInt(10); // from 0 to 9
            } while (oceanMap[x][y] == 1);
            oceanMap[x][y] = '2';
            System.out.println((i + 1) + ". ship DEPLOYED");
        }
        System.out.println("----------------------------------------");
        displayOceanMap();
    }
}
