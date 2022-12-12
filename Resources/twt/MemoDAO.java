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
    private String text;
    private String team_id;
    private String posX;
    private String posY;

    public MemoDAO(String team_id) {
        this.team_id = team_id;
    }

    public MemoDAO(String text, String team_id, String posX, String posY) {
        this.text=text;
        this.team_id=team_id;
        this.posX=posX;
        this.posY=posY;


    }

    public MemoDAO() {

    }

    public MemoVO findMemo() {
        MemoVO memoVO = new MemoVO();
        try {
            connDB();

            String query="select * from memo_table where team_id = '" + team_id + "'";
            pstmt=con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            String input_text="", x="", y="", c="";
            while(rs.next()) {
                if(rs.getString("post_text").equals("")) {
                    continue;
                }
                input_text += rs.getString(1);
                x += rs.getString("posX");
                y += rs.getString("posY");
                c += rs.getString("count").toString();

                input_text += ":";
                x += ":";
                y += ":";
                c += ":";
            }

            memoVO.setText(input_text);
            memoVO.setX(x);
            memoVO.setY(y);
            memoVO.setC(c);

            rs.close();
            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return memoVO;
    }

    public void insertMemo() {
        try {
            connDB();
            String query_Insert="insert into memo_table values('" + text + "','" + team_id + "','" + posX + "','" + posY + "',default)";
            pstmt = con.prepareStatement(query_Insert);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMemo(String count) {
        try {
            connDB();

            String query_Delete="delete from memo_table where count='" + count + "'";
            pstmt = con.prepareStatement(query_Delete);
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
