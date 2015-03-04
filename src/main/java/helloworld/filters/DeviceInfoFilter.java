package helloworld.filters;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

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


public class DeviceInfoFilter implements Filter {

	private FilterConfig filterConfig = null;
	private String configfile;
	private String cssparam;
	private String xslparam;
	private Properties deviceproperties;

	protected final Log logger = LogFactory.getLog(getClass());

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain, String cssparamloc, String xslparamloc, String configfileloc)
			throws IOException, ServletException {
		
		configfile = configfileloc;
		cssparam = cssparamloc;
		xslparam = xslparamloc;
		
	    doFilter(request, response, chain); 
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {

		logger.info("##### DeviceInfoFilter ####");
		logger.info("Config file is: " + configfile);

		String ua = ((HttpServletRequest)request).getHeader("user-agent");
			
		logger.info("User-agent is " + ua);

		Enumeration e = deviceproperties.propertyNames();
	    String xslclasskey = "";
		String cssclasskey = "";
		
		while (e.hasMoreElements() && xslclasskey.equals("") && cssclasskey.equals("")) {
			String key = (String) e.nextElement();
			if (key.startsWith("match")){
				String uapattern = deviceproperties.getProperty(key);
				logger.info("Matching " + uapattern);
				if (ua.matches(uapattern)){
					logger.info("Found match in " + key + ":" + uapattern);
					xslclasskey = key.replace("match", "xsltype");
					cssclasskey = key.replace("match", "csstype");
					logger.info("xslclasskey = " + xslclasskey +".");
					logger.info("cssclasskey = " + cssclasskey +".");
				}
			}
		}
		
		if (xslclasskey.equals("") || cssclasskey.equals("")) {
			xslclasskey = "xsltype.default";
			cssclasskey = "csstype.default";
			logger.info("xslclasskey = " + xslclasskey);
			logger.info("cssclasskey = " + cssclasskey);
		}
		
		String cssparamvalue = deviceproperties.getProperty(cssclasskey);
		String xslparamvalue = deviceproperties.getProperty(xslclasskey);

		logger.info("Setting " + cssparam + " to " + cssparamvalue);
		logger.info("Setting " + xslparam + " to " + xslparamvalue);
	
		request.setAttribute(cssparam, cssparamvalue);
		request.setAttribute(xslparam, xslparamvalue);
		
		// cast response to HttpServletResponse
		//HttpServletResponse httpResponse = (HttpServletResponse) response;

		//httpResponse.addHeader(cssparam, cssparamvalue);
		//httpResponse.addHeader(xslparam, xslparamvalue);

		chain.doFilter(request, response);

		logger.info("##### Exiting #####");
	}

	public void init(FilterConfig filterConfig) {
		logger.info("Configuring filter");
		this.filterConfig = filterConfig;

		this.cssparam = filterConfig.getInitParameter("cssparam");
		this.xslparam = filterConfig.getInitParameter("xslparam");
		logger.info("Initializing css param = " + cssparam);
		logger.info("Initializing xsl param = " + xslparam);
	
		this.configfile = filterConfig.getInitParameter("configfile");
		logger.info("Initializing config file = " + configfile);
		
		String configPath = filterConfig.getServletContext().getRealPath(configfile);
		logger.info("Config path is " +configPath);
		//				
		deviceproperties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(configPath);
			logger.info("Loading device proprties from xml file");
			deviceproperties.loadFromXML(fis);
			Enumeration e = deviceproperties.propertyNames();

			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				logger.info(key + " -- " + deviceproperties.getProperty(key));
			}
		} catch (InvalidPropertiesFormatException exception) {
			logger.error("Invalid Property format...");
			logger.error(exception, exception.getCause());
		} catch (IOException exception) {
			logger.error("Error when loading file to xmlStream...");
			logger.error(exception, exception.getCause());
		} 

	}

	public void destroy(){
		this.filterConfig = null;   
	}
}


