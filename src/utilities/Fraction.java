package utilities;

public class Fraction {
    private long _fracTop, _fracBot;
    public Fraction(long fracTop,long fracBot){
        _fracTop = fracTop;
        _fracBot = fracBot;
    }
    public double getValue(){
        return (double) _fracTop /_fracBot;
    }
    //--------------------------GET/SET------------------------------
    public long getTopValue(){
        return _fracTop;
    }
    public long getBotValue(){
        return _fracBot;
    }
    public void setTopValue(long fracTop){
        _fracTop = fracTop;
    }
    public void setBotValue(long fracBot){
        if(fracBot == 0 ){
            System.out.println("0 is not a valid fraction bottom number");
            throw new IllegalArgumentException("0 is not a valid fraction bottom number");
        }
        _fracBot = fracBot;
    }
    public Fraction multiply(Fraction second){
        Fraction newFract = new Fraction(this._fracTop*second._fracTop, this._fracBot* second._fracBot);
        newFract.simplify();
        return newFract;
    }
    public Fraction diference(Fraction second) {
        long commonDenominator = this._fracBot * second._fracBot;
        long newTop = (this._fracTop * second._fracBot) - (second._fracTop * this._fracBot);

        Fraction newFract = new Fraction(newTop, commonDenominator);
        newFract.simplify();
        return newFract;
    }

    public Fraction addition(Fraction second) {
        long commonDenominator = this._fracBot * second._fracBot;
        long newTop = (this._fracTop * second._fracBot) + (second._fracTop * this._fracBot);
        Fraction newFract = new Fraction(newTop, commonDenominator);
        newFract.simplify();
        return newFract;
    }
    public void simplify() {
        long gcd = gcd(_fracTop, _fracBot);
        _fracTop /= gcd;
        _fracBot /= gcd;
    }

    private long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    public String toString(){
        return "["+this._fracTop+"/"+this._fracBot+"]";
    }

}
