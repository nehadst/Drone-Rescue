
package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONObject;

public class Drone {

//This class represents a drone that can perform various actions such as turn, fly, stop, and scan.

    Integer battery;
    State currentState;

    private static Drone instance = null;

    private Drone() {} // Constructor

    public JSONObject turn(Heading face) {
        // (Heading) -> JSONObject
        // This method returns a JSONObject command to make the drone turn in the 
        // direction of the given heading

        JSONObject decision = new JSONObject();
        JSONObject extraParameters = new JSONObject();
        decision.put("action", "heading");
        extraParameters.put("direction", face.name());
        decision.put("parameters", extraParameters);
        return decision;
    }

    public JSONObject fly() {
        // () -> JSONObject
        // This method returns the command to make the drone fly forward
        JSONObject decision = new JSONObject();
        decision.put("action", "fly");
        return decision;
    }


    public JSONObject stop() {
    //() -> JSONObject
    //This method returns the command to make the drone stop.    
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        return decision;
    }

    public JSONObject scan() {
    //() -> JSONObject
    //This method returns the command to make the drone scan.    
        JSONObject decision = new JSONObject();
        decision.put("action", "scan");
        return decision;
    }
}