/*
* This is a program for a game called Odds or Even
*  It is played between two players, in this version it will be you versus the computer.
*  Each player will choose either "odds" or "evens", since you’re playing against computer, you will get first pick.
*  Once you have chosen your side, you each choose a number of fingers to play- 0 to 5.
* The winner is determined by whether the sum of your fingers is odd or even (depending on what you chose).
*/
import java.util.*;
public class OddsAndEvens {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Let's play a game called \"Odds and Evens\"");
        System.out.print("What is your name? ");
        String name = input.nextLine();
        System.out.print("Hi " + name + ", which do you choose (O)dds or (E)vens? ");
        String userChoice = input.next();

        // while loop to check user doesn't enter incorrect value for odd or even
        while (!userChoice.equals("O") && !userChoice.equals("o") && !userChoice.equals("E") && !userChoice.equals("e")){
            System.out.print("Please enter correct choice 'O' for Odds and 'E' for Evens: ");
            userChoice = input.next();
        }
        // if conditions to display the side chosen by each player
        if (userChoice.equals("O") || userChoice.equals("o"))
            System.out.println(name + " has picked Odds! Computer will be evens.");
        else if (userChoice.equals("E") || userChoice.equals("e"))
            System.out.println(name + " has picked Evens! Computer will be Odds.");
        System.out.println("------------------------------------");
        System.out.println();
        play(userChoice, name); // function call to choose fingers and see the winner
    }

    // function to choose fingers and declare winner
    public static void play(String userChoice, String name) {
        Scanner input = new Scanner(System.in);
        System.out.print("How many “fingers” do you put out? ");
        int userFingers = input.nextInt();
        while (userFingers > 5 || userFingers < 0){
            System.out.print("You can choose between 0 and 5 fingers. Please retry ");
            userFingers = input.nextInt();
        }

        Random rand = new Random(); // generating a random object for computer to choose an unbiased random number
        int computer = rand.nextInt(6); // Limiting the number of choices from 0-5 for computer
        System.out.println("The Computer plays " + computer + " \"fingers\"");
        System.out.println("------------------------------------");
        System.out.println();
        int sum = userFingers + computer; // calculating the sum of numbers
        System.out.println(userFingers + " + " + computer + " = " + sum);
        boolean oddOrEven = sum % 2 == 0; // boolean to check if the sum is odd or even. true for even and false for odd.

        // condition to check the sum for odd or even and declaring the winner
        if (oddOrEven) {
            System.out.println(sum + " is ...even!");
            if (userChoice.equals("E") || userChoice.equals("e"))
                System.out.println("That means " + name + " wins! :)");
            else
                System.out.println("That means computer wins!");
        }else {
            System.out.println(sum + " is ...odd!");
            if (userChoice.equals("O") || userChoice.equals("o"))
                System.out.println("That means " + name + " wins! :)");
            else
                System.out.println("That means computer wins!");
        }
        System.out.println("------------------------------------");
    }
}
