package Alphabet;

import java.util.ArrayList;

public class Alphabet {
    private ArrayList<AlphaElem> alphabet;

    public Alphabet(int elementNum){
        alphabet = new ArrayList<>();
        createVoidAlphaElements(elementNum);
    }
    public void setAlphabetElements(ArrayList<String> elements){
        if(elements.size() != alphabet.size()){
            System.out.printf("elements list and alphabet size must be the same");
            throw new IllegalArgumentException("alphabet and element name list sizes not match");
        }
        for(int i = 0; i < alphabet.size(); i++){
            alphabet.get(i).setElement(elements.get(i));
        }
    }
    public void setAlphabetProportion(ArrayList<Long> frecList){
        if(frecList.size() != alphabet.size()){
            System.out.printf("elements prob list and alphabet size must be the same");
            throw new IllegalArgumentException("alphabet and element probs list sizes not match");
        }
        long counter = 0;
        for(int i = 0; i < alphabet.size(); i++){
            counter += frecList.get(i);
        }
        setAlphabetProbs(frecList, counter);

    }
    private void setAlphabetProbs(ArrayList<Long> fracTop, long botValue){
        if(fracTop.size() != alphabet.size()){
            System.out.printf("elements prob list and alphabet size must be the same");
            throw new IllegalArgumentException("alphabet and element probs list sizes not match");
        }
        for(int i = 0; i < alphabet.size(); i++){
            alphabet.get(i).setProb(fracTop.get(i), botValue);
        }
    }
    private void setAlphabetProbs(ArrayList<Long> fracTop, ArrayList<Long> fracBot){
        if(fracTop.size() != alphabet.size() || fracBot.size() != alphabet.size()){
            System.out.printf("elements prob list and alphabet size must be the same");
            throw new IllegalArgumentException("alphabet and element probs list sizes not match");
        }
        for(int i = 0; i < alphabet.size(); i++){
            alphabet.get(i).setProb(fracTop.get(i), fracBot.get(i));
        }
    }
    private void createVoidAlphaElements(int elementNum){
        for(int i = 0; i < elementNum; i++){
            alphabet.add(new AlphaElem());
        }
    }
    public int size(){
        return alphabet.size();
    }
    public AlphaElem getElem(int index){
        return alphabet.get(index);
    }
}
