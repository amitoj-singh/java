/*Fraction class is the object class to perform calculations on fractions
*It provides fractions as numerator and denominators and useful functions like
* addition, subtraction, multiplication, division and checks the two fractions for equality
*/
public class Fraction {
    private int numerator, denominator; // private variables used to store numerator and denominator

    public Fraction(int numerator, int denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException("denominator cannot be zero"); //denominator can't be zero
        if ( (numerator >= 0 && denominator < 0) || (numerator < 0 && denominator < 0) ) {
            this.numerator = -numerator;
            this.denominator = -denominator;
        } else {
            this.numerator = numerator;
            if (numerator == 0) // when numerator is zero, setting denominator to be 1
                this.denominator = 1;
            else
                this.denominator = denominator;
        }
    }

    public Fraction(int numerator) { // constructor
        this(numerator, 1);
    }

    public Fraction() { //constructor
        this(0,1);
    }

    public int getNumerator() { // method to return numerator
        return numerator;
    }

    public int getDenominator() { // method to return denominator
        return denominator;
    }

    public String toString() { // overriding toString() get the fraction as a String in a desired format
        return numerator + "/" + denominator;
    }

    public Double toDouble() { // returns the fraction result as a real number
        return (double)(numerator/denominator);
    }

    public Fraction add(Fraction other) { // adds two fractions
        int resultDenominator = this.denominator * other.getDenominator();
        int resultNumerator = this.numerator*other.getDenominator() + other.getNumerator()*this.denominator;
        Fraction result = new Fraction(resultNumerator, resultDenominator);
        if (result.numerator != 0) // this is to avoid division by zero in gcd()
            result.toLowestTerms();
        return result;
    }

    public Fraction subtract(Fraction other) {
        int resultDenominator = this.denominator * other.getDenominator();
        int resultNumerator = this.numerator*other.getDenominator() - other.getNumerator()*this.denominator;
        Fraction result = new Fraction(resultNumerator, resultDenominator);
        if (result.numerator != 0) // this is to avoid division by zero in gcd()
            result.toLowestTerms();
        return result;
    }

    public Fraction multiply(Fraction other) {
        int resultDenominator = this.denominator * other.getDenominator();
        int resultNumerator = this.numerator * other.getNumerator();
        Fraction result = new Fraction(resultNumerator, resultDenominator);
        if (result.numerator != 0) // this is to avoid division by zero in gcd()
            result.toLowestTerms();
        return result;
    }

    public Fraction divide(Fraction other) {
        int resultNumerator = this.numerator * other.getDenominator();
        int resultDenominator = this.denominator * other.getNumerator();
        Fraction result = new Fraction(resultNumerator, resultDenominator);
        if (result.numerator != 0) // this is to avoid division by zero in gcd()
            result.toLowestTerms();
        return result;
    }

    public boolean equals(Object other) { // overrides the equals() to compare two fractions and return true if they are equal
        if ( !(other instanceof Fraction) )
            return false;
        Fraction f = (Fraction) other;
        if (this.numerator != 0) this.toLowestTerms();
        if (f.numerator != 0) f.toLowestTerms();
        return (Integer.compare(numerator, f.numerator) == 0 && Integer.compare(denominator, f.denominator) == 0);
    }

    public void toLowestTerms() { // simplification of the fraction
        int gcd = gcd(numerator, denominator);
        numerator = numerator/gcd;
        denominator = denominator/gcd;
    }

    public static int gcd(int num, int den) { // calculation of gcd which is required for simplification
        int rem;
        while(num!=0 && den!=0) {
            rem = num % den;
            num = den;
            den = rem;
        }
        return num;
    }
}
