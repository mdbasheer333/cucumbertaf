package org.cucumbertaf.testlib.ctaflisteners;

import org.cucumbertaf.utils.Globals;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

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
        Map<String,Integer> mp=new HashMap<>();
        mp.put(featureName, iteration);
        Globals.counterTracker.set(mp);
        System.out.println(Globals.counterTracker.get());
    }

    public void onFinish(ITestContext context) {

    }

}
