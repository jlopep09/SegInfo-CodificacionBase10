package Modelo.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Fraction {
    private BigInteger _fracTop, _fracBot;
    private int precision = 40;
    public Fraction(BigInteger fracTop,BigInteger fracBot){
        _fracTop = fracTop;
        _fracBot = fracBot;
    }
    public BigDecimal getValue(){
        BigDecimal fracTop = new BigDecimal(_fracTop);
        BigDecimal fracBot = new BigDecimal(_fracBot);
        return fracTop.divide(fracBot, precision, RoundingMode.HALF_UP);
    }
    //--------------------------GET/SET------------------------------
    public BigInteger getTopValue(){
        return _fracTop;
    }
    public BigInteger getBotValue(){
        return _fracBot;
    }
    public void setTopValue(BigInteger fracTop){
        _fracTop = fracTop;
    }
    public void setBotValue(BigInteger fracBot){
        if(fracBot.compareTo(BigInteger.ZERO) == 0 ){
            System.out.println("0 is not a valid fraction bottom number");
            throw new IllegalArgumentException("0 is not a valid fraction bottom number");
        }
        _fracBot = fracBot;
    }
    public Fraction multiply(Fraction second){
        Fraction newFract = new Fraction(this._fracTop.multiply(second._fracTop), this._fracBot.multiply(second._fracBot));
        return newFract;
    }
    public Fraction diference(Fraction second) {
        BigInteger commonDenominator = this._fracBot.multiply(second._fracBot);
        BigInteger newTop = (this._fracTop.multiply(second._fracBot)).subtract((second._fracTop.multiply(this._fracBot)));
        Fraction newFract = new Fraction(newTop, commonDenominator);
        return newFract;
    }

    public Fraction addition(Fraction second) {
        BigInteger commonDenominator = this._fracBot.multiply(second._fracBot);
        BigInteger newTop = (this._fracTop.multiply(second._fracBot)).add((second._fracTop.multiply(this._fracBot)));
        Fraction newFract = new Fraction(newTop, commonDenominator);
        return newFract;
    }
    /**
     * Must be a number from range [0,1]
     * **/
    public Fraction makeItFraction(BigDecimal num){
        if(num.compareTo(BigDecimal.ONE) > 0 ){
            throw new IllegalArgumentException("cant make fraction numbers greater than 1");
        }
        Fraction result = new Fraction(BigInteger.ZERO,BigInteger.ONE);
        if(num.compareTo(BigDecimal.ZERO) == 0){
            return result;
        }else if(num.compareTo(BigDecimal.ONE)==1){
            return new Fraction(BigInteger.ONE,BigInteger.ONE);
        }
        String number = num.toString();
        if(!number.contains(".")){
            number.concat(".0");
        }
        number=number.substring(2);

        BigInteger top = new BigInteger(number);
        StringBuffer bot = new StringBuffer();
        bot.append("1");
        for(int i = 0; i<number.length();i++){
            bot.append("0");
        }
        result = new Fraction(top,new BigInteger(bot.toString()));
        return result;
    }
    public String toString(){
        return "["+this._fracTop.toString()+"/"+this._fracBot.toString()+"]";
    }

}
