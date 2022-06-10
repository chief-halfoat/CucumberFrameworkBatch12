package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use to provide the path of all the feature files
        features = "src/test/resources/features",
        //glue is where we find the implementations of gherkin steps
        //we provide the path of the package to get all the step definitions
        glue = "steps",
        dryRun = true,
        monochrome = true,
        tags = "@ETETest1"
)

public class SmokeRunner {
}
