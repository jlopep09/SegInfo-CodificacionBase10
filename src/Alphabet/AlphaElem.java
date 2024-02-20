package Alphabet;

public class AlphaElem {
    private String _element;
    private long _probFracTop, _probFracBot;
    public AlphaElem(){
        this._element = "";
        this._probFracTop = 0;
        this._probFracBot = 0;
    }
    public AlphaElem(String element, long _probFracTop, long _probFracBot){
        this._element = element;
        this._probFracTop = _probFracTop;
        this._probFracBot = _probFracBot;
    }
    public String getElement(){
        return _element;
    }
    public void setElement(String element){
        this._element = element;
    }
    public void setProb(long _probFracTop, long _probFracBot){
        this._probFracTop = _probFracTop;
        this._probFracBot = _probFracBot;
    }
    public double getProb(){
        return _probFracTop/_probFracBot;
    }
    public long getFracTop(){
        return _probFracTop;
    }
    public long getFracBot(){
        return _probFracBot;
    }

}
