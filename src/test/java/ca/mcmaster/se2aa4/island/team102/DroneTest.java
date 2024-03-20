package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DroneTest {

    private Drone testDrone;

    @BeforeEach
    public void initDrone() {testDrone = new Drone();}

    @Test
    public void DecideTurnSouth() {
        JSONObject decision = new JSONObject();
        JSONObject extra_parameters = new JSONObject();
        decision.put("action", "heading");
        extra_parameters.put("direction", Heading.S);
        decision.put("parameters", extra_parameters);
        
        JSONObject testDecision = testDrone.turn(Heading.S);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void DecideTurnNorth() {
        JSONObject decision = new JSONObject();
        JSONObject extra_parameters = new JSONObject();
        decision.put("action", "heading");
        extra_parameters.put("direction", Heading.N);
        decision.put("parameters", extra_parameters);
        
        JSONObject testDecision = testDrone.turn(Heading.N);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void DecideTurnEast() {
        JSONObject decision = new JSONObject();
        JSONObject extra_parameters = new JSONObject();
        decision.put("action", "heading");
        extra_parameters.put("direction", Heading.E);
        decision.put("parameters", extra_parameters);
        
        JSONObject testDecision = testDrone.turn(Heading.E);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void DecideTurnWest() {
        JSONObject decision = new JSONObject();
        JSONObject extra_parameters = new JSONObject();
        decision.put("action", "heading");
        extra_parameters.put("direction", Heading.W);
        decision.put("parameters", extra_parameters);
        
        JSONObject testDecision = testDrone.turn(Heading.W);

        assertEquals(decision.get("action"), testDecision.get("action"));

        JSONObject parameters = (JSONObject) decision.get("parameters");
        JSONObject testParameters = (JSONObject) testDecision.get("parameters");

        assertEquals(parameters.toString(), testParameters.toString());
    }

    @Test
    public void DecideFlyFront() {
        JSONObject decision = new JSONObject();
        decision.put("action", "fly");
        
        JSONObject testDecision = testDrone.fly();

        assertEquals(decision.get("action"), testDecision.get("action"));
    }

    @Test
    public void DecideStop() {
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        
        JSONObject testDecision = testDrone.stop();

        assertEquals(decision.get("action"), testDecision.get("action"));
    }

    @Test
    public void DecideScan() {
        JSONObject decision = new JSONObject();
        decision.put("action", "scan");
        
        JSONObject testDecision = testDrone.scan();

        assertEquals(decision.get("action"), testDecision.get("action"));
    }
    
}
