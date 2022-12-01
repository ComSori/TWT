package Resources.tm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TeamJoinServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String r_tname=request.getParameter("team_name");
        String r_tid=request.getParameter("team_id");
        //session check
        HttpSession session = request.getSession(true);
        String s_uid=(String)session.getAttribute("t_id");
        if(s_uid.equals("")){
            PrintWriter out=response.getWriter();
            out.println("<script>alert('login이 필요합니다.'); location.href='login_page.html';</script>");
        }
    }
}
