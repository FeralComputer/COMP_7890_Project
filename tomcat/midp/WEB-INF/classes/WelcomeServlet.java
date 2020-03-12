import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WelcomeServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		//response.sendRedirect("second_page.html");

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		out.println("<div align=\"center\">");
		out.println("<img id=\"myImg\" src=\"weeb.gif\" alt=\"HTML5 Icon\" style=\"width:457px;height:480px;\"><br/><br/>");
		out.println("<label>WELCOME</label><br/><br/>");
		
		out.println("<form action=\"servlet3\" method=\"post\" enctype=\"multipart/form-data\">");
		out.println("<input type=\"text\" name=\"description\" />");
		out.println("<input type=\"file\" name=\"file\" />");
		out.println("<input type=\"submit\" />");
		out.println("</form>");
		
		String n=request.getParameter("username");
		out.print("Welcome "+n);
		
		out.close();
	}

}

// <img id="myImg" src="weeb.gif" alt="HTML5 Icon" style="width:457px;height:480px;"><br/><br/>