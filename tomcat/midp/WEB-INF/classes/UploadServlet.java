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
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		InputStream fileContent = filePart.getInputStream();
		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String filePath = s + "\\..\\webapps\\midp\\temp\\" + fileName;
		
		FileOutputStream outfile = new FileOutputStream(filePath);
		byte[] buffer = new byte[1024];
		int read;
		while ((read = fileContent.read(buffer)) != -1) {
			outfile.write(buffer, 0, read);
		}
		outfile.close();
		
		// Save latitude, longitude, filename and caption to database//
		//
		//
		String lat=request.getParameter("Latitude");
		String lng=request.getParameter("Longitude");
		String cap=request.getParameter("Caption");
		// SAVE TO DATABASE
		
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		out.println("<div align=\"center\">");
		out.println("<img id=\"myImg\" src=\"weeb4.gif\" alt=\"HTML5 Icon\"><br/><br/>");
		//out.println("Current relative path is: " + s +"<br/><br/>");
		out.println(" Whale Cum <br/><br/>");
		out.println("Your Image with the tag: " + cap + " has been added to our database under the filename: " + fileName);
		out.println("<br/><br/><img id=\"myImg\" src=\"" + "temp\\" + fileName +" \" alt=\"HTML5 Icon\"><br/><br/>");
		//out.println(s +"\\" + filePath);
		
		out.println("<form action=\"menu_page.html\">");
		out.println("<input type=\"submit\" value=\"Return to menu\" />");
		out.println("</form>");
		
		
		out.close();
		

}
}
