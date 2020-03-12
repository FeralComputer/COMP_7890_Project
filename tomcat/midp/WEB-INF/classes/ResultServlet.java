import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.nio.file.*;

 
import java.io.File;
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Use Latitude, Longitude, Radius, Caption and Filename to parse Database
		//
		//
		String fileName=request.getParameter("Filename");
		String lat=request.getParameter("Latitude");
		String lng=request.getParameter("Longitude");
		String rad=request.getParameter("Radius");
		String cap=request.getParameter("Caption");
		// Pasrse Database
		
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		out.println("<div align=\"center\">");
		out.println("<img id=\"myImg\" src=\"weeb7.gif\" alt=\"HTML5 Icon\"><br/><br/>");
		out.println(" Result: <br/><br/>");
		out.println("<br/><br/><img id=\"myImg\" src=\"" + "temp\\" + fileName +" \" alt=\"HTML5 Icon\"><br/><br/>");
		
		out.println("<form action=\"menu_page.html\">");
		out.println("<input type=\"submit\" value=\"Return to menu\" />");
		out.println("</form>");
		
		out.close();
		

}
}
