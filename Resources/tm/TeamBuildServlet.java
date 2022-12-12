package Resources.tm;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class TeamBuildServlet extends HttpServlet {
    // 세션인증->팀 생성페이지->팀생성 DB 입력-> 생성된 팀 선택설정
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String r_tname=request.getParameter("team_name");
        String r_tid=request.getParameter("team_id");
        //session check
        HttpSession session = request.getSession(true);
        String s_uid=(String)session.getAttribute("id");
        if(s_uid==null){
            PrintWriter out=response.getWriter();
            out.println("<script>alert('login이 필요합니다.'); location.href='login_page.html';</script>");
            out.flush();
            destroy();
        }else if(r_tname==null||r_tid==null) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('id혹은이름을 공란없이 입력해주세요');window.history.back();</script>");
            out.flush();
            destroy();
        }else{
            TeamDAO dao=new TeamDAO();
            if(!dao.chk_team(r_tid)){
                dao.buildTeam(r_tname,r_tid,s_uid);
                session.setAttribute("team",r_tid);
                Cookie cookie_tl=new Cookie("team_list",dao.teamlist(s_uid));
                response.addCookie(cookie_tl);
                response.sendRedirect("Main.html");
            }else{
                PrintWriter out=response.getWriter();
                out.println("<script>alert('이미 존재하는 id입니다.');window.history.back();</script>");
            }
        }
    }
}
