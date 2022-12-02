package Resources.login;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();

        String s_uid=(String)session.getAttribute("id");
        if(s_uid.equals("")){
            PrintWriter out=response.getWriter();
            out.println("<script>alert('login이 필요합니다.'); location.href='login_page.html';</script>");
        }

        Cookie cookie=new Cookie("id","");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        session.invalidate();
        response.sendRedirect("Main.html");
    }
}
