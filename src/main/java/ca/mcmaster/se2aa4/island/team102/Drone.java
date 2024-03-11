
package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONObject;

public class Drone {

    
    Integer battery;
    State currentState;

    public JSONObject turn(Heading face) {
        JSONObject decision = new JSONObject();
        JSONObject extra_parameters = new JSONObject();
        decision.put("action", "heading");
        extra_parameters.put("direction", face.name());
        decision.put("parameters", extra_parameters);
        return decision;
    }

    public JSONObject fly() {
        JSONObject decision = new JSONObject();
        decision.put("action", "fly");
        return decision;
    }


    public JSONObject stop() {
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        return decision;
    }

    public JSONObject scan() {
        JSONObject decision = new JSONObject();
        decision.put("action", "scan");
        return decision;
    }
}