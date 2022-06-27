package web.servlet.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 요청의 종류
 * 1. view(HTML) 요청 -> 무조건 get 요청(주소창) 하는건 전부다 페이지요청임.
 * 2. API(Application Programming Interface) 요청(중복확인 요청) -> api는 기능을 확인하는 프로그램 
 * 	-> CRUD(post, get, put, delete):JavaScript(AJAX 동기, 비동기)
 * 
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp")
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response); // 서블릿이 받은 요청과 리퀘스트를 바로 전달해주는것
	}
	
}
