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
        int numerator = 1;
        int denominator = 1;
        if (!periodicPart.isEmpty() && !periodicPart.isBlank())
        {
            numerator = Integer.parseInt(intPart + aperiodicPart + periodicPart) - Integer.parseInt(intPart + aperiodicPart);
            denominator =  Integer.parseInt(String.join("",Collections.nCopies(periodicPart.length(), "9")) + 
                String.join("",Collections.nCopies(aperiodicPart.length(), "0")));
        }
        else
        {
            numerator = Integer.parseInt(intPart + aperiodicPart);
            denominator =  Integer.parseInt("1" + 
                String.join("",Collections.nCopies(intPart.length() + aperiodicPart.length() - 1, "0")));
        }
        return new Fraction(numerator, denominator, "g");
    }
}
