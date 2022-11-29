package Resources.signup;

import Resources.dbcon.MemberVO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SignupDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private DataSource dataFactory;
    private String id;
    private String pwd;
    private String name;

    public SignupDAO(String id){
        this.id = id;
    }
    public SignupDAO(String id, String pwd, String name){
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }

    public String Signupquery() {
        SignupVO vo=new SignupVO();

        try {
            connDB();
            con=dataFactory.getConnection();
//            ResultSet rs=pstmt.executeQuery();

            String query_Insert="insert into user_table values('" + id + "','" + name + "','" + pwd + "')";
            pstmt=con.prepareStatement(query_Insert);

            vo.setId(id);
            vo.setPwd(pwd);
            vo.setName(name);

            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "Signupquery end";
    }

    public boolean chk_id_distinct(String id) {
        try {
            connDB();
            con=dataFactory.getConnection();
            String query="select * from user_table";
            pstmt=con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            String u_id=rs.getString("u_id");
            while(rs.next()) {
                if (id == u_id) {
                    rs.close();
                    pstmt.close();
                    con.close();
                    return true;
                }
            }
            rs.close();
            pstmt.close();
            con.close();

        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
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
