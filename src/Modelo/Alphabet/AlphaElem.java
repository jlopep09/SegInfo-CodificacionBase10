package Modelo.Alphabet;

import Modelo.utilities.Fraction;

import java.math.BigDecimal;
import java.math.BigInteger;

public class AlphaElem {
    private String _element;
    private Fraction _prob;
    public AlphaElem(){
        this._element = "";
        _prob = new Fraction(BigInteger.ZERO,BigInteger.ONE);
    }
    public AlphaElem(String element, long _probFracTop, long _probFracBot){
        this._element = element;
        _prob = new Fraction(new BigInteger(_probFracTop+""),new BigInteger(_probFracBot+""));
    }
    public String getElement(){
        return _element;
    }
    public void setElement(String element){
        this._element = element;
    }
    public void setProb(long _probFracTop, long _probFracBot){
        Fraction result = new Fraction(new BigInteger(String.valueOf(_probFracTop)), new BigInteger(String.valueOf(_probFracBot)));

        this._prob = result;
    }
    public void setProb(Fraction frac){
        _prob = frac;
    }
    public BigDecimal getProb(){
        return _prob.getValue();
    }
    public BigInteger getFracTop(){
        return _prob.getTopValue();
    }
    public BigInteger getFracBot(){
        return _prob.getBotValue();
    }

}
