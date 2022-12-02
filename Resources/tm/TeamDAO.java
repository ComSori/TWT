package Resources.tm;


import java.util.ArrayList;
import java.sql.*;
public class TeamDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private String Id;
    public String chk_team(String rid){
        try{
            connDB();
            String chk_query="select t_id from team_table where t_id='"+rid+"'";
            pstmt=con.prepareStatement(chk_query);
            ResultSet rs =pstmt.executeQuery();
            while(rs.next()){
                Id=rs.getString("t_id");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return Id;
    }
    public void buildTeam(String t_id,String t_name,String t_leader){
        try{
            connDB();
            String query="INSERT INTO team_table VALUES('"+t_id+"','"+t_name+"','"+t_leader+")";
            pstmt=con.prepareStatement(query);
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