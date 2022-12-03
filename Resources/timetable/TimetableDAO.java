package Resources.timetable;
import Resources.timetable.TimetableVO;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class TimetableDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private String Id;

    TimetableDAO(String id){
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
                String u_id="",t_id="";
                ArrayList<Class_list> list = new ArrayList<Class_list>();
                u_id = rs.getString("u_id");
                t_id = rs.getString("t_id");
                String query2="select * from time_table where uid_for='"+u_id+"'";
                pstmt=con.prepareStatement(query);
                ResultSet rs2=pstmt.executeQuery();
                while(rs2.next()){
                    String user_name="",start="",end="",lecture="",lec_code="";
                    int week = 0;
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

                vo_list.add(vo);
            }
            rs.close();
            pstmt.close();
            con.close();

        }catch(Exception e) {
            e.printStackTrace();
        }
        return vo_list;
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
