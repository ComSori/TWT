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

//@WebServlet("/chk_id")
public class SignupIdCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SignupIdCheckServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //request id
        String r_id = request.getParameter("id");

        SignupDAO dao = new SignupDAO(r_id);
        boolean result = dao.chk_id_duplicate(r_id);

        response.sendRedirect("login_page.html");

        if(result) {
            out.println("<script>alert('중복되지 않은 ID 입니다.');</script>");
        } else {
            out.println("<script>alert('중복된 ID 입니다.');</script>");
        }
        out.close();

    }

}
