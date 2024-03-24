package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;

public class EchoerTest {
    // Test class for Echoer actions.
    private Echoer testEchoer;

    @BeforeEach
    public void initEchoer() {testEchoer = new Echoer();}
    // () -> void
    //This method sets up the test environment before each test by initializing an Echoer.

    @Test
    public void decideEchoSouth() {
        //() -> void
        //This test verifies that the ask method correctly generates a decision JSON for echoing south.
        JSONObject decision = new JSONObject();
        JSONObject extraParameters = new JSONObject();
        decision.put("action", "echo");
        extraParameters.put("direction", Heading.S);
        decision.put("parameters", extraParameters);
        
        JSONObject testDecision = testEchoer.ask(Heading.S);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void decideEchoNorth() {
        //() -> void
        //This test verifies that the ask method correctly generates a decision JSON for echoing north.
        JSONObject decision = new JSONObject();
        JSONObject extraParameters = new JSONObject();
        decision.put("action", "echo");
        extraParameters.put("direction", Heading.N);
        decision.put("parameters", extraParameters);
        
        JSONObject testDecision = testEchoer.ask(Heading.N);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void decideEchoEast() {
        //() -> void
        //This test verifies that the ask method correctly generates a decision JSON for echoing east.
        JSONObject decision = new JSONObject();
        JSONObject extraParameters = new JSONObject();
        decision.put("action", "echo");
        extraParameters.put("direction", Heading.E);
        decision.put("parameters", extraParameters);
        
        JSONObject testDecision = testEchoer.ask(Heading.E);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void decideEchoWest() {
        //() -> void
        //This test verifies that the ask method correctly generates a decision JSON for echoing west.
        JSONObject decision = new JSONObject();
        JSONObject extraParameters = new JSONObject();
        decision.put("action", "echo");
        extraParameters.put("direction", Heading.W);
        decision.put("parameters", extraParameters);
        
        JSONObject testDecision = testEchoer.ask(Heading.W);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }
}
