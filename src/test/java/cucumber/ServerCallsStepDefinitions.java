package cucumber;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.HttpHelper;


public class ServerCallsStepDefinitions {

	private static final Logger LOGGER = Logger.getLogger(ServerCallsStepDefinitions.class.getName());
	private static HttpHelper httpHelper; 
    private String ua = "defaultua";  
    private String filename = "defaultfn"; 
    private String servlet = "defaultsl"; 
	
    private String urlbase = "http://localhost:8080/restclient-1.0.0/";
    
	@Given("^a user has a '(.+)' device$")
	public void a_user_has_a_device(String device) throws Throwable {
		// Express the Regexp above with the code you wish you had
		//throw new PendingException();
		LOGGER.info("Device = " + device);
		this.ua = device;
	}

	@When("^accessing the '(.+)'$")
	public void accessing_the_fileservlet(String servlet) throws Throwable {
		// Express the Regexp above with the code you wish you had
		//throw new PendingException();
		LOGGER.info("Page = " + servlet);
		this.servlet = servlet;
	}

	@When("^using the filename '(.+)'$")
	public void using_the_filename_tablesample_xml(String filename) throws Throwable {
		// Express the Regexp above with the code you wish you had
		//throw new PendingException();
		LOGGER.info("Filename = " + filename);
		this.filename = filename;
	}


	@Then("^the content returned should be styled by '(.+)'$")
	public void the_content_returned_should_be_styled_by_class_(String cssclass) throws Throwable {
		// Express the Regexp above with the code you wish you had
		LOGGER.info("To compare with cssclass = " + cssclass);
		String urlString = urlbase + servlet + "?filename=" + filename;
		LOGGER.info("The resulting test uses the user-agent string " + ua + " to access :"+ urlString);		   
		assertTrue(HttpHelper.FindStringInUrlContent(urlString, cssclass, ua));
	}

	@Then("^transformed by '(.+)'$")
	public void transformed_by_class_(String xslclass) throws Throwable {
		LOGGER.info("To compare with cssclass = " + xslclass);
		String urlString = urlbase + servlet + "?filename=" + filename;
		LOGGER.info("The resulting test uses the user-agent string " + ua + " to access :"+ urlString);		   
		
	    // TODO: TO be implemented
		//HttpHelper.FindStringInUrlContent(urlString, xslclass);
	}
	
	
	@Then("^the content returned should be compliant to '(.+)'$")
	public void the_content_returned_should_be_compliant_to_(String device) throws Throwable {
		// Express the Regexp above with the code you wish you had
		//throw new PendingException();
		LOGGER.info("To compare with " + device);
		LOGGER.info("The resulting test uses the user-agent string " + ua + " to access :"+ urlbase + servlet + "?filename=" + filename);
	    
	}
}
