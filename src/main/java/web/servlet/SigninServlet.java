package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.entity.User;
import service.UserService;
import service.UserServiceImpl;


@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final UserService userService;
    
    public SigninServlet() {
    	userService = new UserServiceImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = null;
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			user = userService.loadUser(username, password);	// user자리가 null이면 로그인 실패
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(user == null) {
				out.print(false);
			}else {
				HttpSession session = request.getSession();
				System.out.println("session id: " + session.getId());	// 브라우저가 서버에 처음 요청할때 세션을 발급
				
				System.out.println("session CreationTime: " 
						+ (new SimpleDateFormat("HH:mm:ss")).format(session.getCreationTime()));
				
				System.out.println("session LastAccessedTime: " 
						+ (new SimpleDateFormat("HH:mm:ss")).format(session.getLastAccessedTime()));
				
				//세션의 만료시간 설정
//				session.setMaxInactiveInterval(60*30);	// 기본값은 30분임. ()안은 초단위임
				
				//세션에 값 저장하는 방법
				session.setAttribute("principal", user);	// key와 value
				
				out.print(true);
		}
	}
}
	
}

