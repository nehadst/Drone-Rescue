package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONTokener;
import java.io.StringReader;
import org.json.JSONArray;


public class DefaultResultAcknowledger implements ResultAcknowledger {
//This class is responsible for acknowledging the result of the drone's action and updating the state of the drone accordingly.
    private static final Logger logger = LogManager.getLogger(DefaultResultAcknowledger.class);
    // Acknowledges the result of the drone's action and updates the state of the drone accordingly.
    @Override
    public State executeAcknowledgement(ScanParsing parser, Compass compass, MapMaker theMap, Tracker tracker, Drone d, State currentState, String s) {
        //(ScanParser, Compass, MapMaker, Tracker, Drone, State, int, String) -> State
        //Processes the JSON response from the drone actions, updates the system state, and logs the results. Adjusts the drone's current state based on the response.
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        d.battery -= cost;
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);

        
        switch (currentState) {

            case asking_front:
                theMap.put(compass.getHeading(), extraInfo);
                return State.asking_left;

            case asking_left:
                theMap.put(compass.getLeftHeading(), extraInfo);
                return State.asking_right;

            case asking_right:
                theMap.put(compass.getRightHeading(), extraInfo); 
                try {
                    theMap.choose();
                    theMap.reset();
                    logger.info("The best direction to travel in is {}", theMap.best_direction);
                    return State.exploring;

                // in case we're stuck (all neighbours visited) then return
                } catch (Exception e) {
                    logger.info("STUCK");
                    logger.info("battery is {}", d.battery);
                    return State.stopping;
                }


            // after flight, change heading of compass and go to scanning
            case exploring:
                compass.heading = theMap.best_direction;
                return State.scanning;

            // after scanning go back to verification of neighbors if no creeks or sites
            case scanning:
                JSONArray creeks = parser.get_creeks(extraInfo);
                JSONArray sites = parser.get_sites(extraInfo);
                if (creeks.length() > 0) {
                    logger.info("Found creek!");
                    tracker.add_creek(creeks.getString(0), compass.getCoordinates());
                } else if (sites.length() > 0) {
                    logger.info("Found emergency site!");
                    tracker.add_emergency_site(sites.getString(0), compass.getCoordinates());
                }
                return State.asking_front;
            default:
                return State.stopping;
        }
     
    }
}