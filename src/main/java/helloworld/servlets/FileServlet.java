/**
 * FileServlet.java
 */
package helloworld.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileServlet extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 3228823049637053533L;

String contentType = "text/xml";
  
  private String filePath;
  protected final Log logger = LogFactory.getLog(getClass());

  // Actions ------------------------------------------------------------------------------------

  public void init() throws ServletException {

      // Define base path somehow. You can define it as init-param of the servlet.
      this.filePath = getServletContext().getRealPath("/xml");

      // In a Windows environment with the Applicationserver running on the
      // c: volume, the above path is exactly the same as "c:\files".
      // In UNIX, it is just straightforward "/files".
      // If you have stored files in the WebContent of a WAR, for example in the
      // "/WEB-INF/files" folder, then you can retrieve the absolute path by:
      // this.filePath = getServletContext().getRealPath("/WEB-INF/files");
  }
  
  public void doGet (HttpServletRequest req, HttpServletResponse res)
  throws ServletException, IOException
  {
    PrintWriter out = res.getWriter();
    try {
      ServletContext application = getServletContext();

      // Get requested file by path info.
      String requestedFile = req.getParameter("filename");
      
      logger.info("FileServletLong: Retrieving file " + filePath + "\\" + requestedFile);
      
      // Check if file is actually supplied to the request URI.
      if (requestedFile == null) {
          // Do your thing if the file is not supplied to the request URI.
          // Throw an exception, or send 404, or show default/warning page, or just ignore it.
          res.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
          return;
      }

      // Decode the file name (might contain spaces and on) and prepare file object.
      File file = new File(filePath, URLDecoder.decode(requestedFile, "UTF-8"));
      
      // Check if file actually exists in filesystem.
      if (!file.exists()) {
    	logger.info("FileServletLong: File does not exists.");
         
      	// Do your thing if the file appears to be non-existing.
          // Throw an exception, or send 404, or show default/warning page, or just ignore it.
          res.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
          return;
      }

      logger.info("FileServletLong: File does exists.");
      
      BufferedReader reader = new BufferedReader(new FileReader(file));
      while(reader.ready()) out.println(reader.readLine());
    } catch (Exception e){
      out.println(e.toString());
    }
  }

  public String getServletInfo() {
    return "FileServlet";
  }
}
