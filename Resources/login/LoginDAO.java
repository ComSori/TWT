package Resources.login;

import javax.sql.DataSource;
import java.sql.*;

public class LoginDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private String Id;

    public LoginDAO(String id){
        this.Id=id;
    }

    public LoginVO LoginQuery() {
        LoginVO vo=new LoginVO();

        try {
            connDB();
            String query="select * from user_table where u_id='"+Id+"'";
            pstmt=con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            String id="",pwd="",name="";
            while(rs.next()) {
                id = rs.getString("u_id");
                pwd = rs.getString("u_passwd");
                name = rs.getString("u_name");
            }
            vo.setId(id);
            vo.setPwd(pwd);
            vo.setName(name);
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
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");
            con= DriverManager.getConnection("jdbc:mysql://3.39.132.237:59550/twt","webp","0000");
            System.out.println("Connection 생성 성공");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
