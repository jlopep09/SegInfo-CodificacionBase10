import Alphabet.Alphabet;
import Encoder.ArithCoder;
import utilities.Fraction;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]){

        System.out.println("---- Arithmetic coding system base 10 ----");

        ejemplo2();





    }
    private static void ejemplo1(){
        //creamos array de elementos y probabilidades relacionadas por su posicion en el arraylist
        ArrayList<String> elements = new ArrayList<>();
        ArrayList<Long> proportionList = new ArrayList<>();

        //Añadimos los distintos elementos deseados a esas listas
        elements.add("A");
        proportionList.add((long)2);

        elements.add("B");
        proportionList.add((long)1);

        elements.add("C");
        proportionList.add((long)1);

        //creamos el alphabet para esa lista de elementos y probabilidades
        Alphabet alphabet = new Alphabet(3);
        alphabet.setAlphabetElements(elements);
        alphabet.setAlphabetProportion(proportionList);

        //creamos arithCoder, el constructor crea automaticamente las cotas iniciales
        ArithCoder arithCoder = new ArithCoder(alphabet);
        System.out.println(arithCoder.toString());

        System.out.println(arithCoder.encode("A"));

        System.out.println(arithCoder.encode("AB"));

        System.out.println(arithCoder.encode("AAA"));
        System.out.println(arithCoder.encode("ABB"));
        System.out.println(arithCoder.encode("ABC"));
        System.out.println(arithCoder.encode("AACCCCCC"));

    }
    private static void ejemplo2(){

        //creamos array de elementos y probabilidades relacionadas por su posicion en el arraylist
        ArrayList<String> elements = new ArrayList<>();
        ArrayList<Long> proportionList = new ArrayList<>();

        //Añadimos los distintos elementos deseados a esas listas
        elements.add("P");
        proportionList.add((long)1);
        elements.add("A");
        proportionList.add((long)2);
        elements.add("S");
        proportionList.add((long)1);
        elements.add("E");
        proportionList.add((long)2);
        elements.add("L");
        proportionList.add((long)1);

        //creamos el alphabet para esa lista de elementos y probabilidades
        Alphabet alphabet = new Alphabet(5);
        alphabet.setAlphabetElements(elements);
        alphabet.setAlphabetProportion(proportionList);

        //creamos arithCoder, el constructor crea automaticamente las cotas iniciales
        ArithCoder arithCoder = new ArithCoder(alphabet);
        System.out.println(arithCoder.toString());

        System.out.println(arithCoder.encode("ASA"));
    }
}
