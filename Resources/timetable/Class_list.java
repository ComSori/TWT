package Resources.timetable;

public class Class_list {

    private String user_name;

    private int week;

    private String start;

    private String end;

    private String lecture;

    private String lec_code;
    public Class_list(String name, int week, String start, String end, String lecture, String lec_code){
        this.user_name = name;
        this.week = week;
        this.start = start;
        this.end = end;
        this.lecture = lecture;
        this.lec_code = lec_code;
    }
//    public void setUser_name(String name){
//        this.user_name = name;
//    }
//
//    public void setWeek(int week){
//        this.week = week;
//    }
//    public void setStart(String start){
//        this.start = start;
//    }
//    public void setEnd(String end){
//        this.end = end;
//    }
//
//    public void setLecture(String lecture){
//        this.lecture = lecture;
//    }
//
//    public void setLec_code(String lec_code){
//        this.lec_code = lec_code;
//    }
}
