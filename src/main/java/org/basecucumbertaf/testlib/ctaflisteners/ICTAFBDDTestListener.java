package org.basecucumbertaf.testlib.ctaflisteners;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.basecucumbertaf.utils.IGlobals;

public class ICTAFBDDTestListener implements ConcurrentEventListener {
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
        IGlobals.error = result.getError();
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        Result result = event.getResult();
        IGlobals.error = result.getError();
    }
}