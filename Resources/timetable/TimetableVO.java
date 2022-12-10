package Resources.timetable;

import java.util.ArrayList;

public class TimetableVO {
    private String u_id;
    private String t_id;
    private String u_name;

    private ArrayList<Class_list> list;

    public TimetableVO() {
    }

    public void setUid(String id){
        this.u_id = id;
    }
    public void setTid(String id){
        this.t_id = id;
    }
    public void setU_name(String name){
        this.u_name = name;
    }

    public void setList(ArrayList<Class_list> list){ this.list = list;}

    public String getUid(){ return u_id;}
    public String getTid(){ return t_id;}
    public String getU_name(){ return u_name;}
    public ArrayList<Class_list> getList(){ return this.list;}
}