import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.espn.utils.BaseForWebTests;

/**
 * It configures the cucumber options and links them to the features and step definitions directories.
 */
@CucumberOptions(
            plugin = {"pretty"},
            features = {"src/test/java/tests/features/espn"},
            glue = {"tests.stepDefinitions.espn"}
    )
    public class WebTestRunner extends BaseForWebTests {
        private TestNGCucumberRunner testNGCucumberRunner;

        @BeforeClass(alwaysRun = true)
        public void setUpClass() {
            testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
            //testSetUp();
        }

        @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
        public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable {
            testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
        }

        @DataProvider
        public Object[][] scenarios() {
            return testNGCucumberRunner.provideScenarios();
        }

        @AfterClass(alwaysRun = true)
        public void tearDownClass() {
            testNGCucumberRunner.finish();
        }
}

