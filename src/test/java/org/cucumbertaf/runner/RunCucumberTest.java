package org.cucumbertaf.runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.cucumbertaf.corelib.DriverClass;
import org.cucumbertaf.utils.Globals;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "rerun:target/failedrerun.txt"},
        features = {"src/test/resources"},
        glue = {"org.cucumbertaf.stepdefs", "org.cucumbertaf.hooks"})
public class RunCucumberTest {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider
    public Object[][] scenarios(ITestContext context) {
        if (testNGCucumberRunner == null) {
            testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        }
        List<Object[]> lst = new ArrayList<>();
        String fName = context.getCurrentXmlTest().getParameter("featurename") + ".feature";
        int iterations = Integer.parseInt(context.getCurrentXmlTest().getParameter("iterations"));
        Object[][] obj = testNGCucumberRunner.provideScenarios();
        for (int j = 0; j < iterations; j++) {
            for (int i = 0; i < obj.length; i++) {
                PickleWrapper p = ((PickleWrapper) obj[i][0]);
                String[] var1 = p.getPickle().getUri().getPath().split("/");
                String f_Name = var1[var1.length - 1];
                if (fName.equalsIgnoreCase(f_Name)) {
                    lst.add(obj[i]);
                }
            }
        }
        Object[][] custom_obj = new Object[lst.size()][1];
        for (int i = 0; i < lst.size(); i++) {
            custom_obj[i] = lst.get(i);
        }
        Globals.counterTracker.get().put("total_scenarios_count", Integer.parseInt(String.valueOf(lst.size() / iterations)));
        return custom_obj;
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        testNGCucumberRunner.finish();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        DriverClass.setDriverNull();
    }

}
