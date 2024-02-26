package ca.mcmaster.se2aa4.island.team102;

import java.util.Stack;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Drone {

    int x, y;
    int leftLimitX = 1;
    int topLimitY = 1;
    int rightLimitX;
    int bottomLimitY;
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

    private ArrayList<String> getPossibleDirections() {
        // Get possible directions given drone position

        HashMap<String, String> possibleDirections = new HashMap<>();
        ArrayList<String> validDirections = new ArrayList<>();

        possibleDirections.put("N", "y");
        possibleDirections.put("E", "y");
        possibleDirections.put("S", "y");
        possibleDirections.put("W", "y");

        // Ensure that making a U-turn and turning in the direction already faced is impossible
        if (this.face.equals("N")) {
            possibleDirections.put("S", "n");
            possibleDirections.put("N", "n");
        } else if (this.face.equals("E")) {
            possibleDirections.put("W", "n");
            possibleDirections.put("E", "n");
        } else if (this.face.equals("S")) {
            possibleDirections.put("N", "n");
            possibleDirections.put("S", "n");
        } else {
            possibleDirections.put("E", "n");
            possibleDirections.put("W", "n");
        }

        // Ensure drone can't go off the map
        if (x == leftLimitX) {
            possibleDirections.put("W", "n");
        }
        if (x == rightLimitX) {
            possibleDirections.put("E", "n");
        }
        if (y == topLimitY) {
            possibleDirections.put("N", "n");
        }
        if (y == bottomLimitY) {
            possibleDirections.put("S", "n");
        }
        
        for (String direction : possibleDirections.keySet()) {
            if (possibleDirections.get(direction).equals("y")) {
                validDirections.add(direction);
            }
        }

        return validDirections;
    }

    public JSONObject turn(String direction) {
        // Given a direction, return a JSONObject that allows the drone to turn in that direction if possible
        
        ArrayList<String> validDirections = getPossibleDirections();
        if (!validDirections.contains(direction)) {
            throw new IllegalArgumentException("Cannot turn off of the map, make a U-turn, or turn in the direction of the current heading.");
        }

        JSONObject directionTurn = new JSONObject();
        directionTurn.put("action", "heading");

        JSONObject directionParameters = new JSONObject();
        directionParameters.put("direction", direction);
        directionTurn.put("parameters", directionParameters);

        // Remove after further testing
        throw new UnsupportedOperationException("Unimplemented method 'turn'");
        
        // return directionTurn;    
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