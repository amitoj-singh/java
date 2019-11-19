/*Fraction calculator is used to perform addition, subtraction, multiplication and division on two fractions.
* FractionCalculator is the client class and the related object class is called as Fraction.
*/
import java.util.Scanner;

public class FractionCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //declaring a scanner
        System.out.println("This program is a Fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Please enter fractions int the form a/b, where a and b are integers.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        String operator = getOperation(sc); // first call to getOperation method to get the operation user want to perform
        Fraction result = new Fraction();
        boolean isFracEqual = false; // this is used if the user chooses '=' operator to check if two fractions are equal
        while (!operator.equals("q") && !operator.equals("Q")) { // the while loop runs until user quits
            Fraction frac1 = getFraction(sc); // getting first fraction
            Fraction frac2 = getFraction(sc); // getting second fraction
            switch ( operator ) {
                case "+":
                    result = frac1.add(frac2);
                    break;
                case "-":
                    result = frac1.subtract(frac2);
                    break;
                case "*":
                    result = frac1.multiply(frac2);
                    break;
                case "/":
                    result = frac1.divide(frac2);
                    break;
                case "=":
                    isFracEqual = frac1.equals(frac2);
                    break;
                default:
                    System.exit(1);
                    break;
            }
            if ( operator.equals("=") ) { // displaying output when the user chooses '=' operator
                if (isFracEqual)
                    System.out.println(frac1.toString() + " " + operator + " " + frac2.toString() + " is true");
                else
                    System.out.println(frac1.toString() + " " + operator + " " + frac2.toString() + " is false");
            } else // if user chooses any other operator except '='
                System.out.println(frac1.toString() + " " + operator + " " + frac2.toString() + " = " + result.toString());
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            operator = getOperation((sc)); // prompting user to enter operator or quit by pressing q
        }
    }
    // This function prompts the user to enter its choice of operator and validates the same for correctness
    // else prompts the user to enter the correct operator until the correct choice is chosen
    public static String getOperation(Scanner input) {
        String operator;
        System.out.print("Please enter an operation (+, -, *, /, = or Q to quit): ");
        operator = input.next();
        while (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/") && !operator.equals("=") && !operator.equals("q") && !operator.equals("Q") ) {
            System.out.print("Invalid input (+, -, *, /, = or Q to quit): ");
            operator = input.next();
        }
        return operator;
    }
    // This is used to validate the fraction entered by the user
    public static boolean validFraction(String input) {
        if ( input.indexOf('-') != 0 && input.indexOf('-') != -1 )
            return false;
        if ( input.indexOf('/') == -1 ) {
            if ( isNumber(input) )
                return true;
            else
                return false;
        }
        String[] subStrings = input.split("/", 2);
        if (isNumber(subStrings[0]) && isNumber(subStrings[1]))
            return true;
        else
            return false;
    }
    // this function checks if the provided string contains an integer
    public static boolean isNumber(String input) {
        if (input.isEmpty())
            return false;
        try {
            int num = Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    // this is used to get the fraction from the user and is validated using the result given by validFraction()
    public static Fraction getFraction(Scanner input) {
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        String sFrac = input.next();
        while (!validFraction(sFrac)) {
            System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
            sFrac = input.next();
        }
        int num, den;
         if ( sFrac.indexOf("/") != -1 ) {
            String[] arrFrac = sFrac.split("/", 2); // the input string is split from the '/' character to validate the numerator and denominator
            num = Integer.parseInt(arrFrac[0]); // extracts the number from a String
            if (!arrFrac[1].isEmpty())
                den = Integer.parseInt(arrFrac[1]);
            else
                den =1;
        } else { // when the '/' character is missing from the fraction
             num = Integer.parseInt(sFrac); // if the fraction is single number then the index 1 remains empty, this avoids arrayIndexOutOfBounds exception
             den = 1;
         }
        Fraction frac = new Fraction(num, den); // creating the fraction after all validations
        return frac;
    }
}
