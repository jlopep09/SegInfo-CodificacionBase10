package Controlador;

import Vista.MainWindow;
import Modelo.Alphabet.Alphabet;
import Modelo.Encoder.ArithCoder;

import javax.swing.*;
import java.util.ArrayList;

public class Controlador {

    private static Controlador instance;
    private Controlador(){}

    public static Controlador getInstance(){
        if (instance == null) {
            instance = new Controlador();
        }
        return instance;
    }

    public void initApp(){
        new MainWindow();
    }
    public ArithCoder prepareCoder(String elements, String probs) {
        //Check [format] and remove [ ]
        if(elements.charAt(0)!='['||elements.charAt(elements.length()-1)!=']'||probs.charAt(0)!='['||probs.charAt(probs.length()-1)!=']'){
            //Not correct format
            JOptionPane.showMessageDialog(null,"Not correct input format, check steps 1 and 2");
        }
        elements= elements.substring(1, elements.length()-1);
        probs= probs.substring(1, probs.length()-1);
        //try to create ArithCoder
        try{
            //Create alphabet and element lists
            Alphabet alphabet = new Alphabet(elements.length());
            ArrayList<String> elemList = new ArrayList<>();
            ArrayList<Long> propList = new ArrayList<>();

            //Add elements strings to the elemList
            for(int i = 0;i<elements.length();i++){
                elemList.add(elements.charAt(i)+"");
            }
            //Split prob input in strings
            String[] probArray = probs.split(" ");

            // Add probs to probList
            for (String tempProb : probArray) {
                propList.add(Long.valueOf(tempProb));
            }
            //Set up ArithCoder info
            alphabet.setAlphabetElements(elemList);
            alphabet.setAlphabetProportion(propList);
            return new ArithCoder(alphabet);
        }catch (IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null,"modelo.Alphabet and probs number of elements are different, check steps 1 and 2");
        }
        return null;
    }
    public double encode(ArithCoder arithCoder, String texto){
        return arithCoder.encode(texto);
    }
    public String encodeToString(ArithCoder arithCoder, String texto){
        return ""+arithCoder.encode(texto);
    }
    public String decode(ArithCoder arithCoder, double code, int length){
        return arithCoder.decode(code,length);
    }
}
