package helloworld.utils;
import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.logging.Log;

public class FilterUtils {

	public void logHeaders(ServletRequest request, Log log){
		Enumeration keys = request.getParameterNames();
		while (keys.hasMoreElements() )
		{
			String key = (String)keys.nextElement();

			//To retrieve a single value
			String value = request.getParameter(key);
			log.info(key + ":" + value);
		}
	}

}
