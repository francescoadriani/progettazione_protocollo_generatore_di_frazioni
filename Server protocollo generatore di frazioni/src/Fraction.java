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
        return numerator + "/" + denominator + ";" + command;
    }

    public int getNumerator(){
        return numerator;
    }

    public int getDenominator(){
        return denominator;
    }
}
