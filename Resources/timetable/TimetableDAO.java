package Resources.timetable;
import Resources.timetable.TimetableVO;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class TimetableDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private String Id;
    private String name;
    private String week;
    private String start;
    private String end;
    private String lecture;
    private String lec_code;

    TimetableDAO(String id){
        this.Id = id;
    }//teamid 입력받아서 팀원id 쭉 출력

    TimetableDAO(String name, String week, String start, String end, String lecture, String lec_code, String id) {
        this.name = name;
        this.week = week;
        this.start = start;
        this.end = end;
        this.lecture = lecture;
        this.lec_code = lec_code;
        this.Id = id;
    }

    public TimetableVO_list Load_member() {
        TimetableVO_list vo_list = new TimetableVO_list();

        try {
            connDB();
            String query="select * from t_associate where t_id='"+Id+"'";
            pstmt=con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()) {
                TimetableVO vo=new TimetableVO();
                String u_id="",t_id="",u_name="";
                ArrayList<Class_list> list = new ArrayList<Class_list>();
                u_id = rs.getString("u_id");
                t_id = rs.getString("t_id");
                String query2="select * from time_table where uid_for='"+u_id+"'";
                pstmt=con.prepareStatement(query2);
                ResultSet rs2=pstmt.executeQuery();
                String user_name="",start="",end="",lecture="",lec_code="";
                int week = 0;
                while(rs2.next()){
                    user_name = rs2.getString("user_name");
                    week = rs2.getInt("week");
                    start = rs2.getString("start");
                    end = rs2.getString("end");
                    lecture = rs2.getString("lecture");
                    lec_code = rs2.getString("lec_code");
                    Class_list tmp = new Class_list(user_name,week,start,end,lecture,lec_code);
                    list.add(tmp);
                }
                vo.setUid(u_id);
                vo.setTid(t_id);
                vo.setList(list);

                query2="select u_name from user_table where u_id='"+u_id+"'";
                pstmt=con.prepareStatement(query2);
                rs2=pstmt.executeQuery();
                rs2.next();
                u_name = rs2.getString("u_name");
                vo.setU_name(u_name);

                vo_list.add(vo);
                rs2.close();
            }
            rs.close();
            pstmt.close();
            con.close();

        }catch(Exception e) {
            e.printStackTrace();
        }
        return vo_list;
    }

    public void Insert_table() {
        try {
            connDB();
            String query_Insert="insert into time_table values('" + Id + "','" + week + "','" + start + "','" + end + "','" + lecture + "','" + lec_code + "','" + Id +"')";
            pstmt = con.prepareStatement(query_Insert);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void Delete_table() {
        try {
            connDB();
            String query_Delete="delete from time_table where uid_for='" + Id + "'";
            pstmt = con.prepareStatement(query_Delete);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void connDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");
            con= DriverManager.getConnection("jdbc:mysql://3.39.132.237:59550/twt","webp","0000");
            System.out.println("Connection 생성 성공");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
