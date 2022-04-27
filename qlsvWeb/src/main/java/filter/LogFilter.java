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
//
//import com.mysql.cj.x.protobuf.MysqlxResultset.FetchSuspended;
//@WebFilter(urlPatterns = {"/*"})
//
//public class LogFilter implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) //arg0 = request; arg1 = response; arg2 = chain
//			throws IOException, ServletException {
//		
//		System.out.println("IP:" + request.getRemoteAddr());
//		// cho di qua
//		chain.doFilter(request, response);
//		
//		
//		HttpServletRequest req = (HttpServletRequest) request; //luu du lieu session
//		HttpSession session = req.getSession(); //mac dinh moi session duoc tao ra doi ta 30'
//		
//		String s = (String) session.getAttribute("username");
//		System.out.println("SESSION: " + session.getId());
//		System.out.println(s);
//		
//		
//		//khong cho di qua 
//		//dung lam bao mat cho tk admin
////		HttpServletResponse resp = (HttpServletResponse) response;
////		PrintWriter pw = resp.getWriter();
////		pw.println("STOP !!!");
////		pw.close();
//		
//	}
//
//}
