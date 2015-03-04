package helloworld.filters;

import helloworld.wrappers.CharResponseWrapper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


public final class HitCounterFilter implements Filter {
   private FilterConfig filterConfig = null;
   
   public void init(FilterConfig filterConfig) 
      throws ServletException {
      this.filterConfig = filterConfig;
   }
   public void destroy() {
      this.filterConfig = null;
   }
   public void doFilter(ServletRequest request,
      ServletResponse response, FilterChain chain) 
      throws IOException, ServletException {
      System.out.println("SaniFilter: Filter is being executed...");
	   if (filterConfig == null)
         return;
	
	// Don't execute too much here Sani- becuase we are on our way in
  
      PrintWriter out = response.getWriter();
      CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse)response);
    
 	  chain.doFilter(request, wrapper);

	  
	// Check response here - if it contains the redirect trigger tag - send back 302

	if(wrapper.getContentType().equals("text/html")) {
	
	// Logic goes here...
	
	}

    System.out.println("SaniFilter: Filter exiting...");
	  
   }


}
