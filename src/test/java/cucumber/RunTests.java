package cucumber;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/cucumber/restcalls.feature",format = {"pretty"})
public class RunTests {

//    public static void main(String[] args) throws Exception {
//        JUnitCore.main(
//                "com.stackoverflow.MyTestSuite");
//    }
}
 

