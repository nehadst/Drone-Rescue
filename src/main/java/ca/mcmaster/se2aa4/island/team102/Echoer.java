package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONObject;

public class Echoer {

        // check if the direction in which drone is currently facing has land
        public JSONObject ask(Heading face) {
            JSONObject decision = new JSONObject();
            JSONObject extra_parameters = new JSONObject();
            decision.put("action", "echo");
            extra_parameters.put("direction", face.name());
            decision.put("parameters", extra_parameters);
            return decision;
        }
}