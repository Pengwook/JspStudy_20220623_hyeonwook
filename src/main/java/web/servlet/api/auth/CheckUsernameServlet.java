package web.servlet.api.auth;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.ServletContextConfig;
import service.UserService;
import service.UserServiceImpl;

@WebServlet("/check/username")
public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final UserService userService;
	
	public CheckUsernameServlet() {
		userService = ServletContextConfig.getInstance().getUserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
				
		response.setContentType("text/plain;charset=UTF-8");	// plain -> 일반 문자열로 봄
		try {
			response.getWriter().print(userService.checkUsername(username));	// getwriter()는 데이터 응답용 println이랑 append랑 비슷 print랑 writer랑 비슷
//			PrintWriter out = response.getWriter();
//			out.print("<h1>테스트</h1>");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
