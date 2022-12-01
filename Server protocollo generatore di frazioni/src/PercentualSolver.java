public class PercentualSolver extends Solver{
    
    public PercentualSolver(String number) {
        super(number);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Fraction solve(){
        Fraction generator = super.solve();
        return new Fraction((int)(100.0 * generator.getNumerator()/generator.getDenominator()), 1, "%");
    }
}
