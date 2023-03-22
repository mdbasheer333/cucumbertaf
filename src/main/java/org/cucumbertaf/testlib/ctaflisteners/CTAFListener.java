package org.cucumbertaf.testlib.ctaflisteners;

import org.cucumbertaf.utils.Globals;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class CTAFListener implements ITestListener {

    public void onStart(ITestContext context) {
        String featureName = context.getCurrentXmlTest().getParameter("featurename");
        Map<String, Integer> mp = new HashMap<>();
        mp.put(featureName, 1);
        mp.put("current_iteration", 1);
        Globals.counterTracker.set(mp);
    }

}
