package Resources.dbcon;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MemberDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private DataSource dataFactory;

    public List listMembers() {
        // TODO Auto-generated method stub
        List list=new ArrayList();
        try {
            connDB();
            con=dataFactory.getConnection();
            String query="select * from memo_table";
            System.out.println(query);
            pstmt=con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()) {
                String id=rs.getString("id");
                String pwd=rs.getString("pwd");
                String name=rs.getString("name");
                String email=rs.getString("email");
                Date joinDate=rs.getDate("joinDate");
                MemberVO vo=new MemberVO();
                vo.setId(id);
                vo.setPwd(pwd);
                vo.setName(name);
                vo.setEmail(email);
                vo.setJoinDate(joinDate);
                list.add(vo);
            }
            rs.close();
            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    private void connDB() {
        try {
            Class.forName("com.mysql.driver");
            System.out.println("드라이버 로딩 성공");
            con= DriverManager.getConnection("dbc:oracle:thin:@localhost:1521","alex","1592");
            System.out.println("Connection 생성 성공");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}

