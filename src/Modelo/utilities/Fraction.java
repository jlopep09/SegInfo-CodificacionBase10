package Modelo.utilities;

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
        this.simplify();
        second.simplify();
        System.out.println("Multiplicando "+this.toString()+second.toString());
        Fraction newFract = new Fraction(this._fracTop*second._fracTop, this._fracBot* second._fracBot);
        newFract.simplify();
        return newFract;
    }
    public Fraction diference(Fraction second) {
        this.simplify();
        second.simplify();
        long commonDenominator = this._fracBot * second._fracBot;
        long newTop = (this._fracTop * second._fracBot) - (second._fracTop * this._fracBot);

        Fraction newFract = new Fraction(newTop, commonDenominator);
        newFract.simplify();
        return newFract;
    }

    public Fraction addition(Fraction second) {
        this.simplify();
        second.simplify();
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
    /**
     * Must be a number from range [0,1]
     * **/
    public Fraction makeItFraction(double num){
        if(num>1){
            throw new IllegalArgumentException("cant make fraction numbers greater than 1");
        }
        Fraction result = new Fraction(0,1);
        if(num==0){
            return result;
        }else if(num==1){
            return new Fraction(1,1);
        }

        String number = num+"";
        number=number.substring(2);
        long top = Long.valueOf(number);
        StringBuffer bot = new StringBuffer();
        bot.append("1");
        for(int i = 0; i<number.length();i++){
            bot.append("0");
        }
        result = new Fraction(top,Long.valueOf(bot.toString()));
        result.simplify();
        return result;
    }
    public String toString(){
        return "["+this._fracTop+"/"+this._fracBot+"]";
    }

}
