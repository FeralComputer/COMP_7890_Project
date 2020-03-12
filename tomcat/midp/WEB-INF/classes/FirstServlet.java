import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FirstServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String p=request.getParameter("userpass");
		
		if(p.equals("password")){
			RequestDispatcher rd=request.getRequestDispatcher("menu_page.html");
			rd.forward(request,response);
		}
		else{
			out.print("Username or Password Incorrect");
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request,response);
		}
		
		out.close();
	}

}

// javac --release 8 -classpath .;"D:\Program Files\Apache Software Foundation\Tomcat 9.0\lib\servlet-api.jar" FirstServlet.java
