package web.servlet.api.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/logout")
public class LogoutSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate(); // 객체를 강제로 만료시키는거
		response.sendRedirect("/index"); // index로 다시 보내버리기, sendRedirect -> forward처럼 요청을 날리는것, 서버측에서 get요청하는것
	}

}
