package Resources.tm;


import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TeamBuildServlet extends HttpServlet {
    /*향후 계획
     팀 실제 소속인지 검증
     */

    //설계 계획 세션인증->팀 생성페이지->팀생성 DB 입력-> 생성된 팀 선택설정
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String r_tname=request.getParameter("team_name");
        String r_tid=request.getParameter("team_id");
        //session check
        HttpSession session = request.getSession(true);
        String s_uid=(String)session.getAttribute("id");
        if(s_uid.equals("")){
            PrintWriter out=response.getWriter();
            out.println("<script>alert('login이 필요합니다.'); location.href='login_page.html';</script>");
        }

        //null check
        if(r_tname.equals("")||r_tid.equals("")){
            PrintWriter out =response.getWriter();
            out.println("<script>alert('id혹은이름을 공란없이 입력해주세요');window.history.back();</script>");
        }

        //DB
        TeamDAO dao=new TeamDAO();

        if(dao.chk_team(r_tid).equals("")){
            dao.buildTeam(r_tname,r_tid,s_uid);
            session.setAttribute("team",r_tid);
        }else{
            PrintWriter out=response.getWriter();
            out.println("<script>alert('이미 존재하는 id입니다.');window.history.back();</script>");
        }
    }
}