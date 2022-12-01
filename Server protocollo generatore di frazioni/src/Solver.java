import java.util.Collections;

//http://www.risolviespressioni.it/lezioni/frazione-generatrice-numero-periodico/
public class Solver {
    String number;
    public Solver(String number){
        this.number=number;
    }
    public Fraction solve(){
        String temp = this.number.replace(".",";");
        String[] splitted = temp.split(";");
        String intPart = splitted[0];
        String fractionPart="";
        if (splitted.length>1)
            fractionPart = splitted[1];
        temp = fractionPart.replace("(", ";");
        temp = temp.replace(")","");
        String[] splitted2 = temp.split(";");
        String aperiodicPart = splitted2[0];
        String periodicPart="";
        if (splitted2.length>1)
            periodicPart = splitted2[1];
        int numerator = Integer.parseInt(intPart + aperiodicPart + periodicPart) - Integer.parseInt(intPart + aperiodicPart);
        int denominator =  Integer.parseInt(String.join("",Collections.nCopies(periodicPart.length(), "9")) + String.join("",Collections.nCopies(aperiodicPart.length(), "0")));
        return new Fraction(numerator, denominator, "g");
    }
}
