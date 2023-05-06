package org.cucumbertaf.testlib.ctaflisteners;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.cucumbertaf.utils.Globals;

public class CTAFBDDTestListener implements ConcurrentEventListener {
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
        publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
        publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
    }

    private void handleTestStepFinished(TestStepFinished event) {
        Result result = event.getResult();
        Globals.error = result.getError();
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        Result result = event.getResult();
        Globals.error = result.getError();
    }
}