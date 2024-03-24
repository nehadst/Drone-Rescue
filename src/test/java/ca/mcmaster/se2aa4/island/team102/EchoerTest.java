package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EchoerTest {
    private Echoer testEchoer;

    @BeforeEach
    public void initEchoer() {testEchoer = new Echoer();}

    @Test
    public void decideEchoSouth() {
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
