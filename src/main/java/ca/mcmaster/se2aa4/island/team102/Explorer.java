package ca.mcmaster.se2aa4.island.team102;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.team102.Compass.Heading;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private int initial_budget;
    private int current_budget;
    private MapMaker theMap;
    private Echoer echoer = new Echoer();
    private Drone d = new Drone();
    private Compass compass;
    private ScanParser parser = new ScanParser();

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        Heading initial_heading = Heading.valueOf(info.getString("heading"));
        compass = new Compass(initial_heading);
        theMap = new MapMaker(initial_heading, compass);
        d.battery = info.getInt("budget");
        d.currentState = State.asking_front;
        logger.info("The drone is currently facing {}", initial_heading.name());
        logger.info("Battery level is {}", d.battery);
        logger.info("The drone is currently in state {}", d.currentState);
        initial_budget = info.getInt("budget");
        current_budget = initial_budget;
    }

    private void emergency_return(){
        if (current_budget <= initial_budget / 2) {
            logger.info("The drone is returning to the starting point due to low battery");
            d.currentState = State.stopping;
        }
    }

    @Override
    public String takeDecision() {

        JSONObject decision;
        emergency_return();
        switch (d.currentState) {

            // verify all drone neighbour directions (except behind)
            case asking_front:
                decision = echoer.ask(compass.getHeading());
                break;

            case asking_left:
                decision = echoer.ask(compass.getLeftHeading());
                break;

            case asking_right:
                decision = echoer.ask(compass.getRightHeading());
                break;

            case exploring:
                if (Objects.equals(compass.heading, theMap.best_direction)) {
                    decision = d.fly();
                } else {
                    decision = d.turn(theMap.best_direction);
                }
                compass.updateCoordinates(theMap.best_direction);
                Location new_location = compass.getCoordinates();
                logger.info("The drone has moved to coordinates {} {}", new_location.x, new_location.y);
                if (compass.alreadyVisited(new_location)) {
                    logger.info("The drone has already visited these coordinates");  
                    // theMap.looking_for = "OUT_OF_RANGE";
                } else {
                    compass.addVisitedLocation(new_location);
                    // theMap.looking_for = "GROUND";
                }
                break;

            case scanning:
                decision = d.scan();
                break;                    
            case stopping:
                decision = d.stop(); 
                break;

            default:
                decision = d.stop();
                break;
        }
        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
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
        switch (d.currentState) {

            case asking_front:
                theMap.put(compass.getHeading(), extraInfo);
                d.currentState = State.asking_left;
                break;

            case asking_left:
                theMap.put(compass.getLeftHeading(), extraInfo);
                d.currentState = State.asking_right;
                break;

            case asking_right:
                theMap.put(compass.getRightHeading(), extraInfo); 
                // if (theMap.is_stuck()) {
                //     logger.info("STUCK");
                // }
                theMap.choose();
                theMap.reset();
                logger.info("The best direction to travel in is {}", theMap.best_direction);
                d.currentState = State.exploring;
                break;

            // after flight, change heading of compass and go to scanning
            case exploring:
                compass.heading = theMap.best_direction;
                d.currentState = State.scanning;
                break;

            // after scanning go back to verification of neighbors if no creeks or sites
            case scanning:
                JSONArray creeks = parser.get_creeks(extraInfo);
                JSONArray sites = parser.get_sites(extraInfo);
                if (creeks.length() > 0) {
                    logger.info("Found creek!");
                    d.currentState = State.asking_front;
                } else if (sites.length() > 0) {
                    logger.info("Found emergency site!");
                    d.currentState = State.stopping;
                }
                else {
                    d.currentState = State.asking_front;
                }

                // if (compass.alreadyVisited()) {
                //     theMap.looking_for = "OUT_OF_RANGE";
                // }
                // else {
                //     theMap.looking_for = "GROUND";
                // }                
                // break;
                

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
