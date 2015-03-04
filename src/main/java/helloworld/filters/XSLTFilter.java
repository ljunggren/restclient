package helloworld.filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import helloworld.wrappers.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import helloworld.utils.FilterUtils;


public class XSLTFilter implements Filter {

	private FilterConfig filterConfig = null;
	private String urlparam;
	private String defaulttarget;
	private String[] urlparamvalues;
	private String[] urlparamtargets;

	protected final Log logger = LogFactory.getLog(getClass());
	private FilterUtils filterutils = new FilterUtils();
	
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
   throws IOException, ServletException {
    String contentType;
    String styleSheet;
    String target;
    
    logger.info("##### DeviceInfoFilter ####");
    
    // Request parameter overrides request attributes
    String type = request.getParameter(urlparam);
    if (type == null || type.equals("")) {
    	type = (String) request.getAttribute(urlparam);
    }
    
    logger.info("Type is " + type);
    
    contentType = "text/html";
    target = "";
    
    if (type == null || type.equals("")) {
    	target = defaulttarget;    	
    } 
    else 
    { 
    	for (int i = 0; i < urlparamvalues.length; i++) {
    		if	(type.equals(urlparamvalues[i]))
    			target=urlparamtargets[i];
    	}
    }
    if (target.equals(""))
    	target = defaulttarget;
    
    styleSheet = "/xsl/" + target;
    	
    logger.info("Content-type: " + contentType);
    logger.info("Style-sheet: " + styleSheet);
    
    filterutils.logHeaders(request, logger);
    
    response.setContentType(contentType);
    String stylePath = filterConfig.getServletContext().getRealPath(styleSheet);
    Source styleSource = new StreamSource(stylePath);
    PrintWriter out = response.getWriter();
    CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse)response);     
    
    chain.doFilter(request, wrapper);
    
    StringReader sr = new StringReader(wrapper.toString());
    Source xmlSource = new StreamSource((Reader)sr);
    
    logger.info("Attempting xsl transformation of content...");
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer(styleSource);
      CharArrayWriter caw = new CharArrayWriter();
      StreamResult result  = new StreamResult(caw);
      transformer.transform(xmlSource, result);
      response.setContentLength(caw.toString().length());
      out.write(caw.toString());
    } catch(Exception ex) {
      out.println(ex.toString());
      out.write(wrapper.toString());
    }
    logger.info("##### Exiting #####");
  }
  
  public void init(FilterConfig filterConfig) {
	   logger.info("XSLTFilter Configuring filter.");
	   this.filterConfig = filterConfig;
	   
	   this.urlparam = filterConfig.getInitParameter("urlparam");
	   this.urlparamtargets = filterConfig.getInitParameter("urlparamtargets").split(",");
	   this.urlparamvalues = filterConfig.getInitParameter("urlparamvalues").split(",");
	   this.defaulttarget = filterConfig.getInitParameter("defaulttarget");
	   
	   logger.info("Initializing urlparam = " + urlparam);
	   
	   logger.info("Initializing defaulttarget = " + defaulttarget);
	   
	   for (int i = 0; i < urlparamvalues.length; i++) {
		   logger.info("Initializing urlparamvalues #" + i + "=" + urlparamvalues[i]);
	   }
	   for (int i = 0; i < urlparamtargets.length; i++) {
		   logger.info("Initializing urlparamtargets #" + i + "=" + urlparamtargets[i]);
	   }
	}
  
  public void destroy(){
   this.filterConfig = null;   
  }
}


