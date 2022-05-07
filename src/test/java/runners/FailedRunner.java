package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use to provide the path of all the feature files
        features = "@target/failed.txt",
        //glue is where we find the implementations of gherkin steps
        //we provide the path of the package to get all the step definitions
        glue = "steps",
        dryRun = false,
        monochrome = true,
        tags = "@sprint12",
        plugin = {"html:target/cucumber.html","pretty","json:target/cucumber.json", "rerun:target/text/failed.txt"}
)



public class FailedRunner {

}
