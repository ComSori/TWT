package Resources.tm;


import java.util.ArrayList;
import java.sql.*;
public class TeamDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private boolean check;
    public Boolean chk_team(String rid){
        try{
            connDB();
            String chk_query="select t_id from team_table where t_id='"+rid+"'";
            pstmt=con.prepareStatement(chk_query);
            ResultSet rs =pstmt.executeQuery();
            check=rs.isBeforeFirst();
        }catch(Exception e){
            e.printStackTrace();
        }

        return check;
    }
    public void buildTeam(String t_id,String t_name,String t_leader){
        try{
            connDB();
            String query="INSERT INTO team_table VALUES('"+t_id+"','"+t_name+"','"+t_leader+"')";
            String query2="INSERT INTO team_table VALUES('"+t_id+"','"+t_leader+"')";
            pstmt=con.prepareStatement(query);
            pstmt.executeUpdate();

            pstmt=con.prepareStatement(query2);
            pstmt.executeUpdate();

            pstmt.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void joinTeam(String t_id,String s_id){
        try{
            connDB();
            String query="INSERT INTO t_associate VALUES('"+s_id+"','"+t_id+")";
            pstmt=con.prepareStatement(query);
            pstmt.executeUpdate();

            pstmt.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public String teamlist(String u_id) {
        String result="";
        try {
            connDB();
            String query = "SELECT GROUP_CONCAT(t_id SEPARATOR ':') AS result FROM t_associate WHERE u_id='" + u_id + "'";
            pstmt=con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                result=rs.getString("result");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public String selectTeam(String uid,String tid){
        String rs_tid="";
        try{
            connDB();
            String query="SELECT t_id from t_associate where u_id='"+uid+"' AND t_id='"+tid+"'";
            pstmt=con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                rs_tid=rs.getString("t_id");
            }
            pstmt.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return rs_tid;
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
