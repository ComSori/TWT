package Resources.twt;

public class MemoVO {

    private static String text;
    private static String x;
    private static String y;
    private static String c;
    public static String getText() {
        return text;
    }
    public void setText(String text){
        this.text=text;
    }

    public static String getX() {
        return x;
    }
    public void setX(String x) {
        this.x=x;
    }

    public static String getY(){return y;}
    public void setY(String y){
        this.y=y;
    }

    public static String getC(){return c;}
    public void setC(String c){
        this.c=c;
    }
}
