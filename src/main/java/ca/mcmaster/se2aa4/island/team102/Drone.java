package ca.mcmaster.se2aa4.island.team102;

import java.util.Stack;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Objects;

public class Drone {

    int x, y;
    String face;
    Integer battery;
    State currentState = State.verifying;
    Stack<String> path = new Stack<String>();
    HashMap<String, JSONObject> map = new HashMap<>();

    public String get_right_face() {
        String right_face = "";
        switch (this.face) {
            case "N":
                right_face = "E";
                break;
            case "W":
                right_face = "N";
                break;
            case "S":
                right_face = "W";
                break;
            case "E":
                right_face = "S";
                break;

        }
        return right_face;
    }

    public String get_left_face() {
        String left_face = "";
        switch (this.face) {
            case "N":
                left_face = "W";
                break;
            case "W":
                left_face = "S";
                break;
            case "S":
                left_face = "E";
                break;
            case "E":
                left_face = "N";
                break;

        }
        return left_face;

    }
    

    // check if the direction in which drone is currently facing has land
    public JSONObject verify() {
        JSONObject decision = new JSONObject();
        JSONObject extra_parameters = new JSONObject();
        decision.put("action", "echo");
        extra_parameters.put("direction", this.face);
        decision.put("parameters", extra_parameters);
        return decision;
    }

    public JSONObject verify_left() {
        JSONObject decision = new JSONObject();
        JSONObject extra_parameters = new JSONObject();
        decision.put("action", "echo");
        String left_face = get_left_face();
        extra_parameters.put("direction", left_face);
        decision.put("parameters", extra_parameters);
        return decision;
    }

    public JSONObject verify_right() {
        JSONObject decision = new JSONObject();
        JSONObject extra_parameters = new JSONObject();
        decision.put("action", "echo");
        String right_face = get_right_face();
        extra_parameters.put("direction", right_face);
        decision.put("parameters", extra_parameters);
        return decision;
    }

    public JSONObject turn() {

        throw new UnsupportedOperationException("Unimplemented method 'turn'");
    }

    public JSONObject fly() {
        JSONObject decision = new JSONObject();
        decision.put("action", "fly");
        path.push(this.face);
        return decision;
    }

    public void evaluate(JSONObject extraInfo) {

        map.put(this.face, extraInfo);

    }

    public void evaluate_left(JSONObject extraInfo) {
        String left_face = get_left_face();
        map.put(left_face, extraInfo);
    }

    public void evaluate_right(JSONObject extraInfo) {
        String right_face = get_right_face();
        map.put(right_face, extraInfo);

    }

    public String choose() {
        String best_direction = "";
        Integer smallest_out_of_range = 1000;

        for (HashMap.Entry<String, JSONObject> entry : map.entrySet()) {
            String direction = entry.getKey();
            JSONObject extraInfo = entry.getValue();
            // type should be "OUT_OF_RANGE" or "GROUND"
            String type = extraInfo.getString("found");
            // range should be an Integer (how far out of range or ground is)
            Integer range = extraInfo.getInt("range");
            if (Objects.equals(type, "OUT_OF_RANGE") && range < smallest_out_of_range && range != 0) {
                best_direction = direction;
                smallest_out_of_range = range;
            }
            // return range for ground as soon as we get it
            // since we know there will be only one ground in 4 directions
            if (type == "GROUND") {
                return direction;
            }
            
        }

        return best_direction;

    }

    public void reset_map() {
        map.clear();
    }

    public int get_map_size() {
        return map.size();
    }

    public JSONObject stop() {
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        return decision;
    }

}