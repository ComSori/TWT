package Resources.login;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //request id, pwd
        String r_id = request.getParameter("id");
        String r_pwd = request.getParameter("password");

        LoginDAO dao = new LoginDAO(r_id);

        LoginVO loginVO = dao.LoginQuery();
        //String db_id = loginVO.getId();
        String db_pwd = loginVO.getPwd();
        /*향후 설정
        cookie 만료시간 설정
        취약한 비밀번호 입력 제한
        SQL injection등 시큐어코딩
        디버깅
         */
        if ("".equals(r_pwd)) {
            PrintWriter out=response.getWriter();
            out.println("<script>alert('아이디나 패스워드를 입력하세요'); location.href='login_page.html';</script>");
            out.flush();
        } else if(db_pwd.equals(r_pwd)){
            Cookie cookie = new Cookie("name", loginVO.getName());
            response.addCookie(cookie);
            response.sendRedirect("Main.html");
        }else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('올바르지 않은 ID이거나 Password입니다.'); location.href='login_page.html';</script>");
            out.flush();
        }
    }
}
