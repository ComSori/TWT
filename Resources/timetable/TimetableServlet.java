package Resources.timetable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class TimetableServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TimetableServlet() {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //request id, pwd
        HttpSession session = request.getSession(true);
//        String s_tid=(String)session.getAttribute("team");
        String s_tid = new String("twt");
//        if(s_tid.equals("")){
//            PrintWriter out=response.getWriter();
//            out.println("<script>alert('login이 필요합니다.'); location.href='login_page.html';</script>");
//        }

        TimetableDAO  dao= new TimetableDAO(s_tid);//session teamid받은 dao객체
        TimetableVO_list tabVO_list = dao.Load_member();//같은 팀원 id 저장된 timetablevo arraylist 반환

//        ArrayList<String> u_id_list = new ArrayList<String>();
//        for(int i =0;i<tabVO_list.size();i++){
//            u_id_list.add(tabVO_list.get(i).getUid());
//            System.out.println(tabVO_list.get(i).getUid());
//        }
//        session.setAttribute("u_id_list",u_id_list);
        session.setAttribute("u_id_cnt",+tabVO_list.size());
        session.setAttribute("vo_list",tabVO_list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("TimeTable.jsp");
        requestDispatcher.forward(request, response);
    }
}
