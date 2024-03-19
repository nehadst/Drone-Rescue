package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EchoerTest {
    private Echoer testEchoer;

    @BeforeAll
    public void initEchoer() {testEchoer = new Echoer();}
    
    @Test
    public void DecideEchoFront() {
        JSONObject decision = new JSONObject();
        JSONObject extra_parameters = new JSONObject();
        decision.put("action", "echo");
        extra_parameters.put("direction", Heading.E);
        decision.put("parameters", extra_parameters);
        
        JSONObject testDecision = testEchoer.ask(Heading.E);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void DecideEchoRight() {
        JSONObject decision = new JSONObject();
        JSONObject extra_parameters = new JSONObject();
        decision.put("action", "echo");
        extra_parameters.put("direction", Heading.S);
        decision.put("parameters", extra_parameters);
        
        JSONObject testDecision = testEchoer.ask(Heading.S);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void DecideEchoLeft() {
        JSONObject decision = new JSONObject();
        JSONObject extra_parameters = new JSONObject();
        decision.put("action", "echo");
        extra_parameters.put("direction", Heading.N);
        decision.put("parameters", extra_parameters);
        
        JSONObject testDecision = testEchoer.ask(Heading.N);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }
}
