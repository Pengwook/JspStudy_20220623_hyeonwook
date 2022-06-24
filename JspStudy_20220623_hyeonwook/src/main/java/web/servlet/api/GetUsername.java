package web.servlet.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/api/v1/username")
public class GetUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void init(ServletConfig config) throws ServletException {	
		System.out.println("최초 1회만 실행");
	}

	public void destroy() {
		System.out.println("서블릿 객체가 소멸될 때 1회만 실행");
	}

	
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {     	
//		System.out.println(request.getMethod()); -> 서비스를 쓰면 이렇게 해줘야댐.
//		System.out.println("서비스 호출");
//	}

	// CRUD 이렇게 나눠서 하는게 좋음. 서비스는 모든 요청을 받을수있지만 안좋음.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String t = request.getParameter("test");
		String name = request.getParameter("name");
		System.out.println("Get요청 들어옴");	//content-type -> 응답에 대한 타입을 정해줄수있음. plain -> 일반데이터(그냥 텍스트)
		System.out.println("Read");
		System.out.println("test: " + t);
		
		request.setAttribute("name", name);	// key value순
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user.jsp");
		dispatcher.forward(request, response);	//  forward -> 다른 서블릿을 다시 호출하는거 -> jsp가 열림
		
		
//		response.setContentType("text/html; charset=UTF-8");	// 한글이 잘 나오게 하는 작업
//		response.setCharacterEncoding("UTF-8");						// 한글이 잘 나오게 하는 작업
//		response.getWriter().println("<html><head></head><body>");	// jsp 나오기전 방식	jsp -> 서블릿을 html 형식으로 바꾼것
//		response.getWriter().println("<h1>안녕하세요</h1><p>" + name + "</p></body></html>");	// jsp 나오기전 방식
	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post요청 들어옴");
		System.out.println("Create");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Put요청 들어옴");
		System.out.println("Update");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Delete요청 들어옴");
		System.out.println("Delete");	
	}
}
