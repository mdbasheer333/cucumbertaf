package org.cucumbertaf.ctaflisteners;

import org.cucumbertaf.utils.Globals;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CTAFListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        //System.out.println("------before scenario---result.getName()---------" + result.getTestName());
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestFailure(ITestResult result) {
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        String featureName = context.getCurrentXmlTest().getParameter("featurename");
        int iteration = Integer.parseInt(context.getCurrentXmlTest().getParameter("iteration"));

        System.out.println("----before test tag in xml-----result.context()---------" + featureName + " ----------- " + iteration);

        Globals.counterTracker.put(featureName, iteration);
        System.out.println(Globals.counterTracker);
    }

    public void onFinish(ITestContext context) {

    }

}
