package org.cucumbertaf.testlib.ctaflisteners;

import org.cucumbertaf.utils.Globals;
import org.testng.ITestContext;
import org.testng.ITestListener;

import java.util.HashMap;
import java.util.Map;

public class CTAFListener implements ITestListener {

    public void onStart(ITestContext context) {
        String[] fNames = context.getCurrentXmlTest().getParameter("cucumber.features").split("/");
        String featureName = fNames [fNames.length-1];
        Map<String, Integer> mp = new HashMap<>();
        mp.put(featureName, 1);
        mp.put("current_iteration", 1);
        Globals.counterTracker.set(mp);
    }

}
