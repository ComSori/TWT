package Resources.timetable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TimetableServlet extends HttpServlet {
    public TimetableServlet() {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //request id, pwd
        HttpSession session = request.getSession(true);
//        String s_tid = new String("twt");
        PrintWriter out=response.getWriter();
        if(session.getAttribute("id")==null){
            out.println("<script>alert('로그인이 필요합니다.'); location.href='Main.html';</script>");
            out.flush();
            destroy();
        }
        if(session.getAttribute("team")==null){
            out.println("<script>alert('팀선택이 필요합니다.'); location.href='Main.html';</script>");
            out.flush();
            destroy();
        }else{
            String s_tid=(String)session.getAttribute("team");

            TimetableDAO  dao= new TimetableDAO(s_tid);//session teamid받은 dao객체
            TimetableVO_list tabVO_list = dao.Load_member();//같은 팀원 id 저장된 timetablevo arraylist 반환

            request.setAttribute("u_id_cnt",+tabVO_list.size());
            request.setAttribute("vo_list",tabVO_list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("TimeTable.jsp");
            requestDispatcher.forward(request, response);
        }

    }
}
