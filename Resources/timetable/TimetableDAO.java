package Resources.timetable;
import Resources.timetable.TimetableVO;
import javax.sql.DataSource;
import java.sql.*;

public class TimetableDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private String Id;

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
