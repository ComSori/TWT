package Resources.twt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class MemoAddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String r_tname=request.getParameter("team_name");
        String r_tid=request.getParameter("team_id");
        //session check

        HttpSession session = request.getSession(true);
        String s_uid=(String)session.getAttribute("id");
        String s_tid=(String)session.getAttribute("team");
        PrintWriter out=response.getWriter();
/*
        if (s_uid.equals("")) {
            out.println("<script>alert('팀 id를 공란없이 입력해주세요');window.history.back();</script>");
            destroy();
        }else if(s_tid.equals("")){
            out.println("<script>alert('팀을 선택하지 않았습니다.');location.href='Main.html';</script>");
        }
*/
        String memo_text = request.getParameter("text");
        String posX = request.getParameter("posX");
        String posY = request.getParameter("posY");
        MemoDAO memoDAO = new MemoDAO(memo_text, s_tid, posX, posY);
        memoDAO.insertMemo();
        /*
        out.println(memoDAO.qur);
        out.println(memoDAO.team_id);
        out.println(memoDAO.text);
        out.println(memoDAO.posX); // 넘어옴
        out.println(memoDAO.posY); // 넘어옴
        */

        response.sendRedirect("/memoLoad");
    }
}
