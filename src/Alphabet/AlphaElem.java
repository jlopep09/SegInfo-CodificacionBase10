package Alphabet;

import utilities.Fraction;

public class AlphaElem {
    private String _element;
    private Fraction _prob;
    public AlphaElem(){
        this._element = "";
        _prob = new Fraction(0,1);
    }
    public AlphaElem(String element, long _probFracTop, long _probFracBot){
        this._element = element;
        _prob = new Fraction(_probFracTop,_probFracBot);
    }
    public String getElement(){
        return _element;
    }
    public void setElement(String element){
        this._element = element;
    }
    public void setProb(long _probFracTop, long _probFracBot){
        _prob.setTopValue(_probFracTop);
        _prob.setBotValue(_probFracBot);
    }
    public void setProb(Fraction frac){
        _prob = frac;
    }
    public double getProb(){
        return _prob.getValue();
    }
    public long getFracTop(){
        return _prob.getTopValue();
    }
    public long getFracBot(){
        return _prob.getBotValue();
    }

}
