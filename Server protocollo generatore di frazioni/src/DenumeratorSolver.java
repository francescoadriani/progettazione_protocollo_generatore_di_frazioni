public class DenumeratorSolver extends Solver{

    public DenumeratorSolver(String number) {
        super(number);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Fraction solve(){
        Fraction generator = super.solve();
        int denumeratorRequired = Integer.parseInt(super.number.split("/")[1]);
        return new Fraction((int)(generator.getNumerator()/generator.getDenominator() * denumeratorRequired), denumeratorRequired, "/" + denum);
    }
}
