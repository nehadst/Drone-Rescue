package ca.mcmaster.se2aa4.island.team102;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.Stack;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private enum State { exploring, scanning, found_creek, returning, stopping};
    private State currentState = State.exploring;
    private final int move_limit_before_scan = 1; // this is basically a move limiter before moving. it can be adjusted as needed.
    int counter = 0;
    private Stack<String> path_tracking = new Stack<String>();
    private int initial_budget;
    private int current_budget;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
        initial_budget = info.getInt("budget");
        current_budget = initial_budget;
    }

    private void emergency_return(){
        if (current_budget <= initial_budget / 2) {
            logger.info("The drone is returning to the starting point due to low battery");
            currentState = State.returning;
        }
}

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        emergency_return();
        switch (currentState) {
            case exploring:
                decision.put("action", "fly");
                counter++;
                if (counter >= move_limit_before_scan){
                    currentState = State.scanning;
                }
                break;
            case scanning:
                decision.put("action", "scan");
                // boolean creekFound = ProcessingScans(); I will implement this method later
                // currentState = creekFound ? State.found_creek : State.exploring; 
                counter = 0;
                break;
            case found_creek:
                currentState = State.returning;
                break;
            case returning:
                if (!path_tracking.isEmpty()){
                    decision.put("action", "fly");     
                    decision.put("parameters", back_to_start());
                } else{
                        currentState = State.stopping;}
                break;
                    
            case stopping:
                decision.put("action", "stop");   
                break;
        }
        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }


    private JSONObject back_to_start(){
        JSONObject parameters = new JSONObject();
        if (!path_tracking.isEmpty()){
            String direction = path_tracking.pop();
            String reverse_direcion = get_reverse_direction(direction);
            parameters.put("direction", reverse_direcion);
        }
        return parameters;
    }

    private String get_reverse_direction(String direction){
        switch (direction){
            case "N":
                return "S";
            case "S":
                return "N";
            case "E":
                return "W";
            case "W":
                return "E";
            default:
                return "";
        }
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        current_budget -= cost;
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
