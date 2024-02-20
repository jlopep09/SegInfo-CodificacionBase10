package Encoder;

import Alphabet.Alphabet;
import utilities.Fraction;

import java.util.ArrayList;

public class ArithCoder {
    private Alphabet _alphabet;
    private ArrayList<Fraction> lowRanges;
    private ArrayList<Fraction> hightRanges;

    public ArithCoder(Alphabet alphabet){
        _alphabet = alphabet;
        lowRanges = new ArrayList<>();
        hightRanges = new ArrayList<>();
        setUpRanges();
    }
    private void setUpRanges(){
        long currentValue = 0;
        for(int i = 0; i < _alphabet.size(); i++){
            Fraction lowTemp = new Fraction(currentValue,_alphabet.getElem(i).getFracBot());
            lowRanges.add(lowTemp);
            currentValue += _alphabet.getElem(i).getFracTop();
            Fraction hightTemp = new Fraction(currentValue,_alphabet.getElem(i).getFracBot());
            hightRanges.add(hightTemp);
        }
    }
    public String toString(){
        StringBuffer _sb = new StringBuffer();
        _sb.append("---Content Table---\n");
        _sb.append(" Element | Prob | Range \n");
        for(int i = 0; i < _alphabet.size(); i++){
            _sb.append(_alphabet.getElem(i).getElement() + " | " +
                    _alphabet.getElem(i).getFracTop() + "/" +_alphabet.getElem(i).getFracBot() + " | " +
                    "["+lowRanges.get(i).getTopValue() + "/" +lowRanges.get(i).getBotValue() +","+
                    hightRanges.get(i).getTopValue() + "/" +hightRanges.get(i).getBotValue()+") \n");
        }
        _sb.append("----------------\n");
        return _sb.toString();
    }
}
