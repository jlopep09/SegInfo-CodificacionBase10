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
    public double encode(String msg){
        //CHECK IF MSG IS VALID
        if(!checkValidMsg(msg)){
            throw new IllegalArgumentException("At least one message character is not valid with the alphabet of this encoder");
        }
        //GET INITIAL RANGES FOR CHARACTER 0
        String actualElem = msg.charAt(0)+"";
        Fraction Li = new Fraction(0,1);
        Fraction Hi = new Fraction(1,1);
        for(int i = 0; i<_alphabet.size(); i++){
            if(_alphabet.getElem(i).getElement().equals(actualElem)){
                Li = lowRanges.get(i);
                Hi = hightRanges.get(i);
            }
        }
        //CHECK IF LENGTH == 1
        if(msg.length()==1){
            // VALUE FOR THIS 1 CHAR MSG FROM THE INITIAL RANGE

            double result = getCodedValue(Li, Hi);
            return result;
        }
        //UPDATE RANGES FOR MSG LENGTH
        for(int i = 1; i<msg.length();i++){
            actualElem = msg.charAt(i)+"";
            for(int j = 0; j<_alphabet.size(); j++){
                if(_alphabet.getElem(j).getElement().equals(actualElem)){
                    //System.out.println("Li antiguo: "+Li.toString()+" Hi antiguo: "+Hi.toString());
                    Fraction distance = Hi.diference(Li);
                    //System.out.println("dist"+ Hi.addition(new Fraction(8,49)).toString());
                    Hi = Li.addition(distance.multiply(hightRanges.get(j)));
                    Li = Li.addition(distance.multiply(lowRanges.get(j)));
                    //System.out.println("Li actualizado: "+Li.toString()+" Hi actualizado: "+Hi.toString());
                }
            }
        }
        // VALUE FOR THIS MSG FROM THE RESULT RANGE

        double result = getCodedValue(Li, Hi);
        return result;
    }
    private double getCodedValue(Fraction low, Fraction high){
        double result = 0;
        String Low = low.getValue()+"";
        String High = high.getValue()+"";
        for(int i = 2; i<Low.length();i++){
            if(Integer.parseInt(Low.charAt(i)+"")<Integer.parseInt(High.charAt(i)+"")){
                if(High.length()>i+1){
                    High = High.substring(0,i+1);
                    return Double.parseDouble(High);
                }else{
                    //todo comprobar este fragmento
                    Low = Low.substring(0,i+1);
                    return Double.parseDouble(Low);
                }
            }
        }
        return Double.parseDouble(Low);
    }
    private boolean checkValidMsg(String msg){
        boolean valid = true;
        char[] message = msg.toCharArray();
        for(int i = 0; i<msg.length();i++){
            if(valid){
                valid = false;
                for(int j = 0; j<_alphabet.size();j++){
                    if((message[i]+"").equals(_alphabet.getElem(j).getElement())){
                        valid = true;
                    }
                }
            }
        }
        return valid;
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
