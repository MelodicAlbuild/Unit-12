package Rational;

public class Rational {
    private int numerator;
    private int denominator;

    public Rational(int i1, int i2) {
        numerator = i1;
        denominator = i2;
    }

    public boolean isBigger(Rational r) {
        if (denominator != r.denominator) {
            int lcm = lcm(denominator, r.denominator);
            int mult1 = (lcm / denominator);
            int newDenom1 = mult1 * denominator;
            int newNum1 = mult1 * numerator;
            int mult2 = (lcm / r.denominator);
            int newDenom2 = mult2 * r.denominator;
            int newNum2 = mult2 * r.numerator;
            if (newDenom1 != newDenom2) {
                return false;
            }
            return newNum1 > newNum2;
        } else {
            return numerator > r.numerator;
        }
    }

    public void add(Rational r) {
        if (denominator != r.denominator) {
            int lcm = lcm(denominator, r.denominator);
            System.out.println("Rational Data: " + lcm);
            int mult1 = (lcm / denominator);
            int newDenom1 = mult1 * denominator;
            int newNum1 = mult1 * numerator;
            int mult2 = (lcm / r.denominator);
            int newDenom2 = mult2 * r.denominator;
            int newNum2 = mult2 * r.numerator;
            if (newDenom1 != newDenom2) {
                return;
            }
            numerator = newNum1;
            numerator += newNum2;
            denominator = newDenom1;
            int scLcm = gcd(numerator, denominator);
            System.out.println("Rational Data: " + scLcm);
            if (scLcm == 0) {
                System.out.println("Rational: " + this + " threw a lcm to 0.");
                return;
            }
            numerator = (numerator / scLcm);
            denominator = (denominator / scLcm);
        } else {
            numerator += r.numerator;
        }
    }

    public void setRational(int num, int denom) {
        numerator = num;
        denominator = denom;
    }

    @Override
    public String toString() {
        return numerator + " / " + denominator;
    }

    public static int lcm(int number1, int number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        int absNumber1 = Math.abs(number1);
        int absNumber2 = Math.abs(number2);
        int absHigherNumber = Math.max(absNumber1, absNumber2);
        int absLowerNumber = Math.min(absNumber1, absNumber2);
        int lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    private static Rational reduce(Rational r) {
        Rational rn = new Rational(0, 0);
        int scLcm = gcd(r.numerator, r.denominator);
        System.out.println("Rational Data: " + scLcm);
        if (scLcm == 0) {
            throw new ArithmeticException("Divide by Zero");
        }
        int numerator = (r.numerator / scLcm);
        int denominator = (r.denominator / scLcm);
        rn.setRational(numerator, denominator);
        return rn;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @Override
    public boolean equals(Object obj) {
        Rational reduced1 = reduce(this);
        Rational reduced2 = reduce((Rational) obj);
        return (reduced1.numerator == reduced2.numerator) && (reduced1.denominator == reduced2.denominator);
    }
}
