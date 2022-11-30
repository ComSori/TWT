package Resources.tm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TeamBuildServlet extends HttpServlet {
    /*향후 계획
     팀 실제 소속인지 검증
     */

    //설계 계획 세션인증->팀 생성페이지->팀생성 DB 입력-> 생성된 팀 선택설정
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String rq_tname=request.getParameter("tname");
        String rq_tid=request.getParameter("tid");


    }
}
