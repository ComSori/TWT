package Resources.tm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

public class TeamSelectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String r_tid=request.getParameter("team_id");

        //session check
        HttpSession session = request.getSession(true);
        String s_uid=(String)session.getAttribute("id");
        if(s_uid==null){
            PrintWriter out=response.getWriter();
            out.println("<script>alert('login이 필요합니다.'); location.href='login_page.html';</script>");
            out.flush();
            destroy();
        }else if(r_tid==null){
            PrintWriter out=response.getWriter();
            out.println("<script>alert('팀 id를 공란없이 입력해주세요');window.history.back();</script>");
            out.flush();
            destroy();
        }else{
            TeamDAO dao=new TeamDAO();

            if(r_tid.equals(dao.selectTeam(s_uid,r_tid))){
                session.setAttribute("team",r_tid);
                response.sendRedirect("Main.html");
            }else {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('해당 팀에 가입되지 않았거나 팀이 존재하지 않습니다.');windows.history.back();</script>");
                out.flush();
                destroy();
            }
        }
    }
}
