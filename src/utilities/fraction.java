package utilities;

public class fraction {
    private long _fracTop, _fracBot;
    public fraction(long fracTop,long fracBot){
        _fracTop = fracTop;
        _fracBot = fracBot;
    }
    public double getValue(){
        return _fracTop/_fracBot;
    }
    //--------------------------GET/SET------------------------------
    public long getTopValue(){
        return _fracTop;
    }
    public long getBotValue(){
        return _fracBot;
    }
    public void setTopValue(long fracTop){
        _fracTop = fracTop;
    }
    public void setBotValue(long fracBot){
        _fracBot = fracBot;
    }
}
