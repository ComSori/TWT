package Resources.tm;

import Resources.login.LoginVO;

import java.sql.*;
public class TeamDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private String Id;
    public String buildteam(String rid){
        TeamBuildVO vo=new TeamBuildVO();
        try{
            connDB();
            String chk_query="select t_id from team_table where t_id='"+rid+"'";
            pstmt=con.prepareStatement(chk_query);
            ResultSet rs =pstmt.executeQuery();

            vo.set
        }catch(Exception e){
            e.printStackTrace();
        }

        return id;
    }


}
