public class Fraction {
    private int numerator;
    private int denominator;
    private String command;

    public Fraction(int numerator, int denominator, String command){
        this.numerator = numerator;
        this.denominator = denominator;
        this.command = command;
    }
    
    @Override
    public String toString(){
        if (denominator>1)
            return numerator + "/" + denominator + ";" + command;
        else
            return numerator + ";" + command;
    }

    public int getNumerator(){
        return numerator;
    }

    public int getDenominator(){
        return denominator;
    }
}
