package cucumber;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.HttpHelper;


public class RestCallsStepDefinitions {

	private static final Logger LOGGER = Logger.getLogger(ServerCallsStepDefinitions.class.getName());
	private static HttpHelper httpHelper; 
    private String msisdn = "";
    private String page_uuid = "";
    private String param = "";
	
    private String urlbase = "http://localhost:8080/restclient-1.0.0/";

    @Given("^a user has the phone number '(\\d+)'$")
    public void a_user_has_the_phone_number(String msisdn) throws Throwable {
        // Express the Regexp above with the code you wish you had
        this.msisdn = msisdn;
        LOGGER.info("MSISDN = " + msisdn);
        //throw new PendingException();
    }

    @When("^accessing the json service '(.+)'$")
    public void accessing_the_json_service(String page_uuid) throws Throwable {
        this.page_uuid = page_uuid;
        LOGGER.info("UUID = " + page_uuid);
      // Express the Regexp above with the code you wish you hadthrow new PendingException();
    }

    @When("^sending the parameter '(.+)'$")
    public void sending_the_parameter(String param) throws Throwable {
        // Express the Regexp above with the code you wish you had
        this.param = param;
        LOGGER.info("PARAM = "+ param);
        //throw new PendingException();
    }

    @Then("^the content returned should have the keys '(.+)'$")
    public void the_content_returned_should_have_the_keys(String keys) throws Throwable {
        // Express the Regexp above with the code you wish you had
        LOGGER.info("Keys = " + keys);
        //throw new PendingException();
    }

}
