//package filter;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.security.auth.message.callback.PrivateKeyCallback.Request;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//@WebFilter(urlPatterns = {"/admin/*"})
//
//public class AdminLogFilter implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) //arg0 = request; arg1 = response; arg3 = chain
//			throws IOException, ServletException {
//		
//		HttpServletResponse resp = (HttpServletResponse) response;
//		
//		String username = request.getParameter("username");
//		
//		if(username.equals("admin")) {
//			HttpServletRequest req = (HttpServletRequest) request; //luu du lieu session
//			HttpSession session = req.getSession(); //mac dinh moi session duoc tao ra doi ta 30'
//			
//			session.setAttribute("username", username); // tao doi tuong
//			System.out.println(session.getId());
//			
//			
//			chain.doFilter(request, response);
//		} else {
//			
//			resp.sendRedirect("/qlsvWeb/menu-course");
//		}
//
//	}
//
//}
