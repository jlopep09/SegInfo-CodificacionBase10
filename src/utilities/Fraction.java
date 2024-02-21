package utilities;

import javax.sound.midi.Soundbank;

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
    public Fraction diference(Fraction second){ //todo hacer caso base distinta
        return new Fraction(this._fracTop-second._fracTop, this._fracBot);
    }
    public Fraction multiply(Fraction second){
        return new Fraction(this._fracTop*second._fracTop, this._fracBot* second._fracBot);
    }
    public Fraction addition(Fraction second){//todo hacer caso base distinta
        return new Fraction(this._fracTop+second._fracTop, this._fracBot);
    }
}
