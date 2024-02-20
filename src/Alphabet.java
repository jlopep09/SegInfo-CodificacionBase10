import java.util.ArrayList;

public class Alphabet {
    private ArrayList<AlphaElem> alphabet;

    public Alphabet(int elementNum){
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
    public void setAlphabetProbs(ArrayList<Float> elements){
        if(elements.size() != alphabet.size()){
            System.out.printf("elements prob list and alphabet size must be the same");
            throw new IllegalArgumentException("alphabet and element probs list sizes not match");
        }
        for(int i = 0; i < alphabet.size(); i++){
            alphabet.get(i).setProb(elements.get(i));
        }
    }
    private void createVoidAlphaElements(int elementNum){
        for(int i = 0; i < elementNum; i++){
            alphabet.add(new AlphaElem());
        }
    }
}
