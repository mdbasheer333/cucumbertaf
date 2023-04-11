package org.cucumbertaf.ctaftestng;

import io.cucumber.core.gherkin.Step;
import io.cucumber.plugin.event.Location;
import org.apiguardian.api.API;

import java.net.URI;
import java.util.List;

/**
 * Wraps CucumberPickle to avoid exposing it as part of the public api.
 */
@API(status = API.Status.STABLE)
public final class Pickle {

    private final io.cucumber.core.gherkin.Pickle pickle;

    Pickle(io.cucumber.core.gherkin.Pickle pickle) {
        this.pickle = pickle;
    }

    io.cucumber.core.gherkin.Pickle getPickle() {
        return pickle;
    }

    public String getName() {
        return pickle.getName();
    }

    public String getLanguage() {
        return pickle.getLanguage();
    }

    public String getKeyword() {
        return pickle.getKeyword();
    }

    public String getId() {
        return pickle.getId();
    }

    public Location getScenarioLocation() {
        return pickle.getScenarioLocation();
    }

    public List<Step> getSteps() {
        return pickle.getSteps();
    }

    public int getScenarioLine() {
        return pickle.getScenarioLocation().getLine();
    }

    public int getLine() {
        return pickle.getLocation().getLine();
    }

    public List<String> getTags() {
        return pickle.getTags();
    }

    public URI getUri() {
        return pickle.getUri();
    }

}
