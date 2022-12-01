public class DenumeratorSolver extends Solver{
    private int denumerator=0;
    public DenumeratorSolver(String number, int denumerator) {
        super(number);
        this.denumerator=denumerator;
    }

    @Override
    public Fraction solve(){
        Fraction generator = super.solve();
        return new Fraction((int)((double)generator.getNumerator()* (double)denumerator / (double)generator.getDenominator() ), denumerator, "/" + denumerator);
    }
}
