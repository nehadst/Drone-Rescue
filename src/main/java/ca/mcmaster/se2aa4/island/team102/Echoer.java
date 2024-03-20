package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONObject;

public class Echoer {
//Responsible for creating echo requests to determine the type of terrain in a given direction.
        public JSONObject ask(Heading face) {
        //(Heading) -> JSONObject
        //Creates a command for an echo request in the specified direction to check for land.
        //Echo is useful in the cases to check terrain to see where we're at.    
            JSONObject decision = new JSONObject();
            JSONObject extra_parameters = new JSONObject();
            decision.put("action", "echo");
            extra_parameters.put("direction", face.name());
            decision.put("parameters", extra_parameters);
            return decision;
        }



}