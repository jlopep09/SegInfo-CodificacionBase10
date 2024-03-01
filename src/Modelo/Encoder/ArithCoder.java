package Modelo.Encoder;

import Modelo.Alphabet.Alphabet;
import Modelo.utilities.Fraction;

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
    public String decode(double code, int msgLength){ //todo sustituir .getValues por operaciones con fracciones
        if(msgLength<1){
            return "msg with length 0 or negative cant have a decoded value";
        }
        //find the index of the first decode msg character to get correct interval values from the range lists
        int index = 0;
        for(int i = 0; i<lowRanges.size();i++){
            if(code>lowRanges.get(i).getValue() && code < hightRanges.get(i).getValue()){
                index = i;
                break;
            }
        }
        //get first string value
        StringBuffer result = new StringBuffer();
        result.append(_alphabet.getElem(index).getElement());
        //get the complete decoded string
        Fraction tempCodeVal = new Fraction(0,1);
        tempCodeVal = tempCodeVal.makeItFraction(code);
        System.out.println("1- "+result.toString());
        for(int i = 1; i< msgLength; i++){
            Fraction Denom = (hightRanges.get(index).diference(lowRanges.get(index)));
            tempCodeVal = (tempCodeVal.diference(lowRanges.get(index))).multiply(new Fraction(Denom.getBotValue(), Denom.getTopValue()));
            for(int j = 0; j<lowRanges.size();j++){
                if(tempCodeVal.getValue() >lowRanges.get(j).getValue() && tempCodeVal.getValue() < hightRanges.get(j).getValue()){
                    result.append(_alphabet.getElem(j).getElement());
                    index = j;
                    break;
                }
            }
        }
        return result.toString();
    }
    private double getCodedValue(Fraction low, Fraction high){

        //PREPARE STRINGS FROM FRACTION VALUES
        String Low = low.getValue()+"";
        String High = high.getValue()+"";
        Low = Low.charAt(0)+""+ Low.substring(2);
        High = High.charAt(0)+""+High.substring(2);
        //System.out.println("Low val: "+Low);
        //System.out.println("High val: "+High);
        //ALGORITHM
        //find first char that is not the same in both range values
        int i = 0;
        while( (i<(Low.length()))  &&  (i<(High.length()))  &&  (Low.charAt(i)==High.charAt(i)) ){
            i++;
        }
        //check if any string is ending at this char
        boolean noMoreLowValues = Low.length()-1==i;
        boolean noMoreHighValues = High.length()-1==i;
        if(High.equals("10")){
            noMoreHighValues=true;
        }
        String result = "";
        if(noMoreLowValues){
            result = Low;
        }else{
            if(noMoreHighValues){
                if(Integer.parseInt(Low.charAt(i)+"")+1!=Integer.parseInt(High.charAt(i)+"")){
                    int newCharVal = Integer.parseInt(Low.charAt(i)+"")+1;
                    Low = Low.substring(0, i) + newCharVal;
                    result = Low;
                }else{
                    boolean found = false;
                    i++;
                    while( (i<(Low.length()))  &&  (!found) ){
                        if(Low.charAt(i)!='9'){
                            int newCharVal = Integer.parseInt(Low.charAt(i)+"")+1;
                            Low = Low.substring(0, i) + newCharVal;
                            found = true;
                            result = Low;
                        }
                    }
                }
            }else{
                result = High.substring(0,i)+High.charAt(i);
            }
        }

        //tratar para poner de vuelta el .
        result = result.charAt(0)+"."+ result.substring(1);
        return Double.parseDouble(result);
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
