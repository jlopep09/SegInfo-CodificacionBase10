import Alphabet.Alphabet;
import Encoder.ArithCoder;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]){
        System.out.println("---- Arithmetic coding system base 10 ----");

        ArrayList<String> elements = new ArrayList<>();
        ArrayList<Long> proportionList = new ArrayList<>();

        elements.add("A");
        proportionList.add((long)2);

        elements.add("B");
        proportionList.add((long)1);

        elements.add("C");
        proportionList.add((long)1);

        Alphabet alphabet = new Alphabet(3);
        alphabet.setAlphabetElements(elements);
        alphabet.setAlphabetProportion(proportionList);

        ArithCoder arithCoder = new ArithCoder(alphabet);
        System.out.println(arithCoder.toString());


    }
}
