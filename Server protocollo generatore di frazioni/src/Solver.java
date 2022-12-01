import java.util.Collections;

//http://www.risolviespressioni.it/lezioni/frazione-generatrice-numero-periodico/
public class Solver {
    String number;
    public Solver(String number){
        this.number=number;
    }
    public Fraction solve(){
        String[] splitted = this.number.split(".");
        String intPart = splitted[0];
        String fractionPart="";
        if (splitted.length>1)
            fractionPart = splitted[1];
        splitted = fractionPart.split("(");
        String aperiodicPart = splitted[0];
        String periodicPart="";
        if (splitted.length>1)
            periodicPart = splitted[1].replaceAll(")", "");
        int numerator = Integer.parseInt(intPart + aperiodicPart + periodicPart) - Integer.parseInt(intPart + aperiodicPart);
        int denominator =  Integer.parseInt(String.join("",Collections.nCopies(periodicPart.length(), "9")) + String.join("",Collections.nCopies(aperiodicPart.length(), "0")));
        return new Fraction(numerator, denominator, "g");
    }
}
