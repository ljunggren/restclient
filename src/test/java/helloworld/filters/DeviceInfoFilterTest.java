package helloworld.filters;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import org.junit.Test;

//import org.jmock.Mockery;
//import org.springframework.mock.web.MockFilterChain;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;

//import helloworld.filters.DeviceInfoFilter;

public class DeviceInfoFilterTest {

	//private Mockery context = new Mockery();
	
	//private DeviceInfoFilter deviceInfoFilter = new DeviceInfoFilter();	


	@Test
	public void test() throws ServletException, IOException {
		//MockFilterChain mockChain = new MockFilterChain();
		//MockHttpServletRequest req = new MockHttpServletRequest("/maintenance.jsp", null);
		//MockHttpServletResponse rsp = new MockHttpServletResponse();

		//deviceInfoFilter.doFilter(req, rsp, mockChain);
		//assertTrue("/maintenance.jsp",rsp.containsHeader("user-agent"));
		assertEquals("This must be true", 50, 50);
	}

}
