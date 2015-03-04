package helloworld.filters;

import helloworld.wrappers.CharResponseWrapper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;


public class RedirectFilter implements Filter {

	protected final Log logger = LogFactory.getLog(getClass());

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {

      	logger.info("##### I am a redirect filter ####");
      	System.out.println("##### I am a redirect filter ####");
	
      	//PrintWriter out = response.getWriter();
        //CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse)response);     
      	CharResponseWrapper wrapper = new CharResponseWrapper(
      	   (HttpServletResponse)response);
 
        chain.doFilter(request, wrapper);
        
        PrintWriter out = response.getWriter();
        System.out.println("content type: " + wrapper.getContentType());
        logger.info("content type: " + wrapper.getContentType());
        
        System.out.println("Response = " + wrapper.toString());
      	logger.info("Response = " + wrapper.toString());
      	
      	if (wrapper.toString().contains("<div class=\"benny\">")){
      		System.out.println("Found benny tag. Redirecting");
          	logger.info("Found benny tag. Redirecting");
        
            HttpServletResponse httpResponse = (HttpServletResponse) response;  
          	
          	httpResponse.setStatus(httpResponse.SC_MOVED_TEMPORARILY);
    		httpResponse.setHeader("Location", "http://google.se");
    		httpResponse.setHeader("X-DRUTT-DP-REDIRECT-PASSTHRU", "TRUE");
       	} else {
       		System.out.println("Didn't find benny tag.");
          	logger.info("Didn't find benny tag.");
        }
        
      	response.setContentLength(wrapper.toString().length());
        out.write(wrapper.toString());
                
        logger.info("##### I am on my way back ####");
      	System.out.println("##### I am on my way back ####");
      	
      	logger.info("Is committed - " + response.isCommitted());
      	System.out.println("Is committed - " + response.isCommitted());
      	
      	
      	//StringReader sr = new StringReader(wrapper.toString());
        //Source xmlSource = new StreamSource((Reader)sr);
        
        //logger.info("XML = " + xmlSource.toString());
        //System.out.println("XML = " + xmlSource.toString());
        
		
		//httpResponse.setStatus(httpResponse.SC_MOVED_TEMPORARILY);
		//httpResponse.setHeader("Location", "http://google.se");
		//httpResponse.setHeader("X-DRUTT-DP-REDIRECT-PASSTHRU", "TRUE");
			
		logger.info("##### Exiting #####");
		System.out.println("##### Exiting #####");
		out.close();
	}

	public void init(FilterConfig filterConfig) {
		logger.info("Configuring filter");
	}

	public void destroy(){
	
	}
}


