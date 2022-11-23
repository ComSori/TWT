package Resources.login;

import Resources.dbcon.MemberVO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private DataSource dataFactory;
    private String Id;

    public LoginDAO(String id){
        this.Id=id;
    }

    public LoginVO LoginQuery() {
        LoginVO vo=new LoginVO();

        try {
            connDB();
            con=dataFactory.getConnection();
            String query="select * from user_table where u_id="+Id;
            pstmt=con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            String id=rs.getString("u_id");
            String pwd=rs.getString("password");
            vo.setId(id);
            vo.setPwd(pwd);
            rs.close();
            pstmt.close();
            con.close();

        }catch(Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
    private void connDB() {
        try {
            Class.forName("com.mysql.driver");
            System.out.println("드라이버 로딩 성공");
            con= DriverManager.getConnection("dbc:oracle:thin:@localhost:59550","root","");
            System.out.println("Connection 생성 성공");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
