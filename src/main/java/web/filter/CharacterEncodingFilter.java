package web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import config.ServletContextConfig;


@WebFilter("/*")
public class CharacterEncodingFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;


	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 전처리
		HttpServletRequest httpRequest = (HttpServletRequest) request;	// ServletRequest를 다운캐스팅을 해준것
		if(!httpRequest.getMethod().equalsIgnoreCase("get")) {	// get이 아니면은 밑에 인코딩이 되게 if문 해주면 댐
			request.setCharacterEncoding("UTF-8");
			System.out.println("인코딩됨!");
		}
		
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);	// 서블릿
		// 후처리
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		ServletContextConfig.getInstance();
	}

}
