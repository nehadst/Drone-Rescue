package ca.mcmaster.se2aa4.island.team102;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private boolean evaluation_needed = true;
    private final int move_limit_before_scan = 1; // this is basically a move limiter before scanning. it can be adjusted as needed.
    int counter = 0;
    int mapped = 0;
    private Stack<String> path_tracking = new Stack<String>();
    private int initial_budget;
    private int current_budget;
    Drone d = new Drone();

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        d.face = info.getString("heading");
        d.battery = info.getInt("budget");
        logger.info("The drone is facing {}", d.face);
        logger.info("Battery level is {}", d.battery);
        logger.info("The drone is currently in state {}", d.currentState);
        initial_budget = info.getInt("budget");
        current_budget = initial_budget;
    }

    private void emergency_return(){
        // If in any emergency state such as low battery, return immediately
        if (current_budget <= initial_budget / 2) {
            logger.info("The drone is returning to the starting point due to low battery");
            d.currentState = State.returning;
        }
}

    @Override
    public String takeDecision() {

        JSONObject decision = new JSONObject();
        emergency_return();
        switch (d.currentState) {
            // turn CW
            case turning:
                decision = d.turn();
                break;

            // verify all drone neighbour directions (except behind)
            case verifying:
                switch (mapped) {
                    case 0:
                        decision = d.verify();
                        break;
                    case 1:
                        decision = d.verify_left();
                        break;
                        
                    case 2:
                        decision = d.verify_right();
                        break;
                    default:
                        decision = d.stop();
                }
                evaluation_needed = true;
                break;

            case exploring:
                decision = d.fly();
                d.currentState = State.verifying;
                break;

            // TODO
            // case scanning:
            //     decision.put("action", "scan");
            //     // boolean creekFound = ProcessingScans(); I will implement this method later
            //     // currentState = creekFound ? State.found_creek : State.exploring; 
            //     counter = 0;
            //     currentState = State.exploring;
            //     break;
            // case found_creek:
            //     currentState = State.returning;
            //     break;

            case returning:
                if (!path_tracking.isEmpty()){
                    decision.put("action", "fly");     
                    decision.put("parameters", back_to_start());
                } else {
                        d.currentState = State.stopping;
                }
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
        current_budget -= cost;
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);

        // if we need to add "extras" to map
        if (evaluation_needed) {
            switch (mapped) {
                case 0:
                    d.evaluate(extraInfo);
                    break;
                case 1:
                    d.evaluate_left(extraInfo);
                    break;
                case 2:
                    d.evaluate_right(extraInfo);
                    break;
            }
            mapped++;
        }
        // if our map is full (scanned 3 directions)
        if (d.get_map_size() == 3) {
            String best_direction = d.choose();
            d.reset_map();
            logger.info("The best direction to travel in is {}", best_direction);

        }
    }

    @Override
    public String deliverFinalReport() {
        // for debugging purposes only
        // try {
        //     Thread.sleep(3000000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        return "no creek found";
    }

}
