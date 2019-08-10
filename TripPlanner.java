import java.util.Scanner;
public class TripPlanner {

    public static void main(String[] arg){
        intro();
        System.out.println();
        timeAndBudget();
        System.out.println();
        timeDifference();
        System.out.println();
        countryArea();
    }

    public static void intro() {
        Scanner input = new Scanner(System.in);
        String name, destination;
        System.out.println("Welcome to Vacation Planner");
        System.out.print("What is your Name? ");
        name = input.nextLine();
        System.out.print("Nice to meet you "+name+", where are you travelling to? ");
        destination = input.nextLine();
        System.out.println("Great! "+destination+" sounds like a great trip");
        System.out.println("*********");
    }

    public static void timeAndBudget() {
        Scanner input = new Scanner(System.in);
        int days, hours, minutes, moneyInUSD;
        String currencySymbol;
        double conversionMoney, totalMoney, totalMoneyPerDayUSD, totalMoneyPerDayDestination;
        System.out.print("How many days are you going to spend travelling? ");
        days = input.nextInt();
        System.out.print("How much money, in USD, are you planning to spend on your trip? ");
        moneyInUSD = input.nextInt();
        System.out.print("What is the three letter currency symbol for your travel destination? ");
        currencySymbol = input.next();
        System.out.print("How many "+currencySymbol+" are there in 1 USD? ");
        conversionMoney = input.nextDouble();

        hours = days*24;
        minutes = hours*60;
        totalMoneyPerDayUSD = (int)((moneyInUSD/days)*100)/100.0;
        totalMoney = (int)(moneyInUSD * conversionMoney * 100)/100.0;
        totalMoneyPerDayDestination = (int)((totalMoney/days)*100)/100.0;

        System.out.println("\nIf you are travelling for "+days+" days that is the same as "+hours+" hours or "+minutes+" minutes");
        System.out.println("If you are going to spend $"+moneyInUSD+" USD that means per day you can spend upto $"+totalMoneyPerDayUSD+ " USD");
        System.out.println("Your total budget in "+currencySymbol+" is "+totalMoney+" "+currencySymbol+", which per day is "+totalMoneyPerDayDestination+" "+currencySymbol);
        System.out.println("*********");
    }

    public static void timeDifference() {
        Scanner input = new Scanner(System.in);
        int timeDifference;
        System.out.print("What is the time difference in hours, between your home and your destination? ");
        timeDifference = input.nextInt();
        System.out.println("It means that when it is midnight at home it will be "+(24+timeDifference)%24+":00 in your travel destination and when it is noon at home it will be "+(12+timeDifference)%24+":00");
        System.out.println("*********");
    }

    public static void countryArea() {
        Scanner input = new Scanner(System.in);
        double areaInKM, areaInMiles;
        System.out.print("What is the square area of your destination country in KM^2? ");
        areaInKM = input.nextDouble();
        areaInMiles = (int)(areaInKM/2.59*100)/100.0;
        System.out.println("In miles^2 that is "+areaInMiles);
        System.out.println("*********");
     }
}
