package Resources.twt;

import Resources.signup.SignupVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class MemoDAO {
    private Connection con;
    private PreparedStatement pstmt;
    public String text;
    public String team_id;
    public String posX;
    public String posY;
    public String qur;

    public MemoDAO(String text, String team_id, String posX, String posY) {
        this.text=text;
        this.team_id=team_id;
        this.posX=posX;
        this.posY=posY;
    }

    public void insertMemo() {
        try {
            connDB();

            String query_Insert="insert into memo_table values('" + text + "','" + team_id + "','" + posX + "','" + posY + "')";
            pstmt = con.prepareStatement(query_Insert);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void connDB(){
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
