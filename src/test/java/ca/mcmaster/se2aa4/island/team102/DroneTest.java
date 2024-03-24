package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;

public class DroneTest {
    // Test class for Drone actions.

    private Drone testDrone;

    @BeforeEach
    public void initDrone() {testDrone = Drone.getInstance();}
    // () -> void
    //This method sets up the test environment before each test by retrieving the singleton instance of Drone.

    @Test
    public void decideTurnSouth() {
        //() -> void
        //This test verifies that the turn method correctly generates a decision JSON for turning south.
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
        //() -> void
        //This test verifies that the turn method correctly generates a decision JSON for turning north.
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
        //() -> void
        //This test verifies that the turn method correctly generates a decision JSON for turning east.
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
        //() -> void
        //This test verifies that the turn method correctly generates a decision JSON for turning west.
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
        //() -> void
        //This test verifies that the fly method correctly generates a decision JSON for flying forward.
        JSONObject decision = new JSONObject();
        decision.put("action", "fly");
        
        JSONObject testDecision = testDrone.fly();

        assertEquals(decision.get("action"), testDecision.get("action"));
    }

    @Test
    public void decideStop() {
        //() -> void
        //This test verifies that the stop method correctly generates a decision JSON for stopping.
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        
        JSONObject testDecision = testDrone.stop();

        assertEquals(decision.get("action"), testDecision.get("action"));
    }

    @Test
    public void decideScan() {
        //() -> void
        //This test verifies that the scan method correctly generates a decision JSON for scanning.
        JSONObject decision = new JSONObject();
        decision.put("action", "scan");
        
        JSONObject testDecision = testDrone.scan();

        assertEquals(decision.get("action"), testDecision.get("action"));
    }
    
}
