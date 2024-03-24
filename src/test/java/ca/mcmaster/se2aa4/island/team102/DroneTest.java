package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;

public class DroneTest {

    private Drone testDrone;

    @BeforeEach
    public void initDrone() {testDrone = new Drone();}

    @Test
    public void decideTurnSouth() {
        JSONObject decision = new JSONObject();
        JSONObject extraParameters = new JSONObject();
        decision.put("action", "heading");
        extraParameters.put("direction", Heading.S);
        decision.put("parameters", extraParameters);
        
        JSONObject testDecision = testDrone.turn(Heading.S);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void decideTurnNorth() {
        JSONObject decision = new JSONObject();
        JSONObject extraParameters = new JSONObject();
        decision.put("action", "heading");
        extraParameters.put("direction", Heading.N);
        decision.put("parameters", extraParameters);
        
        JSONObject testDecision = testDrone.turn(Heading.N);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void decideTurnEast() {
        JSONObject decision = new JSONObject();
        JSONObject extraParameters = new JSONObject();
        decision.put("action", "heading");
        extraParameters.put("direction", Heading.E);
        decision.put("parameters", extraParameters);
        
        JSONObject testDecision = testDrone.turn(Heading.E);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void decideTurnWest() {
        JSONObject decision = new JSONObject();
        JSONObject extraParameters = new JSONObject();
        decision.put("action", "heading");
        extraParameters.put("direction", Heading.W);
        decision.put("parameters", extraParameters);
        
        JSONObject testDecision = testDrone.turn(Heading.W);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void decideFlyFront() {
        JSONObject decision = new JSONObject();
        decision.put("action", "fly");
        
        JSONObject testDecision = testDrone.fly();

        assertEquals(decision.get("action"), testDecision.get("action"));
    }

    @Test
    public void decideStop() {
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        
        JSONObject testDecision = testDrone.stop();

        assertEquals(decision.get("action"), testDecision.get("action"));
    }

    @Test
    public void decideScan() {
        JSONObject decision = new JSONObject();
        decision.put("action", "scan");
        
        JSONObject testDecision = testDrone.scan();

        assertEquals(decision.get("action"), testDecision.get("action"));
    }
    
}
