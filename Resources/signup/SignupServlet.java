package Resources.signup;


import Resources.login.LoginDAO;
import Resources.login.LoginVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

//@WebServlet("/member")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SignupServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //request id, pwd
        String r_id = request.getParameter("id");
        String r_pwd = request.getParameter("password");
        String r_name = request.getParameter("name");

        SignupDAO dao = new SignupDAO(r_id, r_pwd, r_name);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        response.sendRedirect("login_page.html");

        if(dao.chk_id_duplicate(r_id)) {
            out.println("<script>alert(" + dao.Signupquery() + ");</script>");
        } else {
            out.println("<script>alert('회원가입 실패'); location.href='login_page.html#signup_pos'; </script>");
        }
        out.close();
    }

}
