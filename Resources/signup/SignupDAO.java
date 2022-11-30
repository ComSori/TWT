package Resources.signup;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.rmi.server.LogStream.log;

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

    public void SignupQuery() {
        SignupVO vo=new SignupVO();

        try {
            connDB();
            String query_Insert="insert into user_table values('" + id + "','" + name + "','" + pwd + "','0')";
            pstmt = con.prepareStatement(query_Insert);
            pstmt.executeUpdate();

            vo.setId(id);
            vo.setPwd(pwd);
            vo.setName(name);

            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String chk_id_duplicate(String id) {
        String r_id = null;
        try {
            connDB();
            String query="select u_id from user_table where u_id='"+id+"'";
            pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if(pstmt != null) {
                while(rs.next()) {
                    r_id = rs.getString("u_id");
                }
            }
            rs.close();
            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return r_id;
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
