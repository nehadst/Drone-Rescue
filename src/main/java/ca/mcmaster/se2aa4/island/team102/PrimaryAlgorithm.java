package ca.mcmaster.se2aa4.island.team102;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class PrimaryAlgorithm implements AlgorithmSelector{

    private final Logger logger = LogManager.getLogger();

    @Override
    public JSONObject executeAlgorithm(Drone d, Compass compass, MapMaker theMap, Echoer echoer) {
        JSONObject decision = new JSONObject();

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
                
                // double check if we have landed on an already visited spot
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

        return decision;
    }
}

