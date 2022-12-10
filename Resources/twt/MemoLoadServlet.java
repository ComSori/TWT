package Resources.twt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class MemoLoadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
        //out.println("<script>alert('"+ memo_text +"');</script>");
        MemoDAO memoDAO = new MemoDAO(s_tid);
        MemoVO memoVO = memoDAO.findMemo();
        request.setAttribute("text", memoVO.getText());
        request.setAttribute("x", memoVO.getX());
        request.setAttribute("y", memoVO.getY());
        //out.println("<script>alert('"+ memoVO.getText() +"');</script>");
        //out.println("<script>alert('"+ memoVO.getX() +"');</script>");
        //out.println("<script>alert('"+ memoVO.getY() +"');</script>");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WorkSpace.jsp");
        requestDispatcher.forward(request, response);
    }
}
