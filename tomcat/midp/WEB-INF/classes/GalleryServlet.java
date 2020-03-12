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
public class GalleryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		PrintWriter out = response.getWriter();
		
		out.println("<div align=\"center\">");
		out.println("<img id=\"myImg\" src=\"weeb6.gif\" alt=\"HTML5 Icon\" ><br/><br/>");
		out.println("<label>GALLERY</label><br/><br/>");
		
		out.println("<form action=\"menu_page.html\">");
		out.println("<input type=\"submit\" value=\"Return to menu\" />");
		out.println("</form>");
		
		File dir = new File(s + "\\..\\webapps\\midp\\temp\\");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				out.println("<br/><br/><img id=\"myImg\" src=\"" + "temp\\" + child.getName() +" \" alt=\"HTML5 Icon\"><br/><br/>");
				// get tag and x and y data from DB and display those as well
				}
		}
		
		out.println("<form action=\"menu_page.html\">");
		out.println("<input type=\"submit\" value=\"Return to menu\" />");
		out.println("</form>");
		
		out.close();
		

}
}
