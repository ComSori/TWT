package Resources.twt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class MemoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String r_tname=request.getParameter("team_name");
        String r_tid=request.getParameter("team_id");
        //session check

        HttpSession session = request.getSession(true);
        String s_uid=(String)session.getAttribute("id");
        String s_tid=(String)session.getAttribute("team");

        if (s_uid.equals("")) {
            PrintWriter out=response.getWriter();
            out.println("<script>alert('팀 id를 공란없이 입력해주세요');window.history.back();</script>");
            destroy();
        }else if(s_tid.equals("")){
            PrintWriter out=response.getWriter();
            out.println("<script>alert('팀을 선택하지 않았습니다.');location.href='Main.html';</script>");
        }
    }
}
