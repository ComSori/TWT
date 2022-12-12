package Resources.tm;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class TeamJoinServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String r_tid=request.getParameter("join_tid");
        //session check
        HttpSession session = request.getSession(true);
        String s_uid=(String)session.getAttribute("id");
        if(s_uid==null){
            PrintWriter out=response.getWriter();
            out.println("<script>alert('login이 필요합니다.'); location.href='login_page.html';</script>");
            out.flush();
            destroy();
        }

        //null check
        if(r_tid.equals("")){
            PrintWriter out =response.getWriter();
            out.println("<script>alert('id를 공란없이 입력해주세요');window.history.back();</script>");
            out.flush();
            destroy();
        }else{
            TeamDAO dao=new TeamDAO();
            //DB zone
            if(!dao.chk_team(r_tid)){
                PrintWriter out=response.getWriter();
                out.println("<script>alert('존재하지 않는 팀입니다.');window.history.back();</script>");
            }else {
                dao.joinTeam(s_uid, r_tid);
                session.setAttribute("team", r_tid);

                Cookie cookie_tl = new Cookie("team_list", dao.teamlist(s_uid));
                response.addCookie(cookie_tl);
                response.sendRedirect("Main.html");
            }
        }


    }
}
