public class CentsSolver extends Solver{

    public CentsSolver(String number) {
        super(number);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public Fraction solve(){
        Fraction generator = super.solve();
        return new Fraction((int)((double)generator.getNumerator() * 100.0),(int)generator.getDenominator(), "c");
    }
}
