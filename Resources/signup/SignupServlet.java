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
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        //request id, pwd

        String r_id = request.getParameter("user_ID");
        String r_pwd = request.getParameter("user_PW");
        String r_name = request.getParameter("user_name");

        SignupDAO dao = new SignupDAO(r_id, r_pwd, r_name);

        String result = dao.chk_id_duplicate(r_id);

        if("".equals(r_id)){
            out.println("<script> alert('아이디를입력해주세요.'); window.history.back(); </script>");
        } else if("".equals(r_pwd)) {
            out.println("<script> alert('비밀번호를입력해주세요.'); window.history.back(); </script>");
        } else if("".equals(r_name)) {
            out.println("<script> alert('이름을입력해주세요.'); window.history.back(); </script>");
        } else {
            if (result == null) {
                try {
                    dao.SignupQuery();
                    out.println("<script> alert('회원가입 성공!'); location.href='login_page.html';</script>");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                out.println("<script> alert('중복된 ID 입니다.'); window.history.back(); </script>");
            }
        }
        out.close();
    }

}
