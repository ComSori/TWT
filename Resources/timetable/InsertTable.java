package Resources.timetable;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class InsertTable extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InsertTable() {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //request id, pwd
//        HttpSession session = request.getSession(true);
//        String s_tid=(String)session.getAttribute("team");
//        String s_tid = new String("twt");
//        if(s_tid.equals("")){
//            PrintWriter out=response.getWriter();
//            out.println("<script>alert('login이 필요합니다.'); location.href='login_page.html';</script>");
//        }
        String lecture = request.getParameter("team_name");
        String week = request.getParameter("week");
        String start = request.getParameter("start");
        String end = request.getParameter("end");

        Class_list list = new Class_list();
        list.setLecture(lecture);
        list.setWeek(Integer.parseInt(week));
        list.setEnd(end);
        list.setStart(start);
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            if(c.getName().equals("name")){
                list.setUser_name(c.getValue());
                break;
            }
        }
//        list.setUser_name(cookie.getValue());
//        TimetableDAO  dao= new TimetableDAO(s_tid);//session teamid받은 dao객체
//        TimetableVO_list tabVO_list = dao.Load_member();//같은 팀원 id 저장된 timetablevo arraylist 반환
//
//        request.setAttribute("u_id_cnt",+tabVO_list.size());
//        request.setAttribute("vo_list",tabVO_list);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("TimeTable.jsp");
//        requestDispatcher.forward(request, response);
    }
}
