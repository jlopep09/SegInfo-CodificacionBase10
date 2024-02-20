public class AlphaElem {
    private String _element;
    private float _prob;
    public AlphaElem(){
        this._element = "";
        this._prob = 0;
    }
    public AlphaElem(String element, float prob){
        this._element = element;
        this._prob= prob;
    }
    public String getElement(){
        return _element;
    }
    public void setElement(String element){
        this._element = element;
    }
    public void setProb(float prob){
        this._prob= prob;
    }
    public float getProb(){
        return this._prob;
    }

}
