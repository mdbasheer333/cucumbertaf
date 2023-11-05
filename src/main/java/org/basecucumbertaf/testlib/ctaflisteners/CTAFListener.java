package org.basecucumbertaf.testlib.ctaflisteners;

import org.basecucumbertaf.utils.IGlobals;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class CTAFListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext context) {
        String[] fNames = context.getCurrentXmlTest().getParameter("cucumber.features").split("/");
        String featureName = fNames [fNames.length-1];
        Map<String, Integer> mp = new HashMap<>();
        mp.put(featureName, 1);
        mp.put("current_iteration", 1);
        IGlobals.counterTracker.set(mp);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

}
