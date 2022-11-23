package Resources.login;

import Resources.dbcon.MemberDAO;
import Resources.dbcon.MemberVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoginServlet {
    private static final long serialVersionUID = 1L;
    public LoginServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String uid = request.getParameter("user_name");
        PrintWriter out =response.getWriter();
        LoginDAO dao=new LoginDAO(id);
        String pwd=dao.LoginQuery();


        for(int i=0;i<list.size();i++) {
            LoginVO loginVO=(LoginVO) list.get(i);
            String id=loginVO.getId();
            String pwd=loginVO.getPwd();
        }
        out.print("</table></body></html>");
}
