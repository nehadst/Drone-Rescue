package ca.mcmaster.se2aa4.island.team102;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class PrimaryAlgorithm implements AlgorithmSelector{
//Implements the primary algorithm for drone navigation and decision-making.    

    private final Logger logger = LogManager.getLogger();

    @Override
    public JSONObject executeAlgorithm(Drone d, Compass compass, MapMaker theMap, Echoer echoer) {
    //(Drone, Compass, MapMaker, Echoer) -> JSONObject
    //Executes the primary algorithm for drone navigation and decision-making.    
        JSONObject decision = new JSONObject();

        switch (d.currentState) {

            // verify all drone neighbour directions (except behind)
            case asking_front:
            // ask the echoer for the front direction
                decision = echoer.ask(compass.getHeading());
                break;

            case asking_left:
            // ask the echoer for the left direction
                decision = echoer.ask(compass.getLeftHeading());
                break;

            case asking_right:
            // ask the echoer for the right direction
                decision = echoer.ask(compass.getRightHeading());
                break;

            case exploring:
            // explore the map
                if (Objects.equals(compass.heading, theMap.best_direction)) {
                    decision = d.fly();
                } else {
                    decision = d.turn(theMap.best_direction);
                }
                
                compass.updateCoordinates(theMap.best_direction);
                Location newLocation = compass.getCoordinates();
                
                // double check if we have landed on an already visited spot
                if (logger.isInfoEnabled()){
                    logger.info("The drone has moved to coordinates {} {}", newLocation.x, newLocation.y);}
                if (compass.alreadyVisited(newLocation)) {
                    logger.info("The drone has already visited these coordinates");  
                    // theMap.looking_for = "OUT_OF_RANGE";
                } else {
                    compass.addVisitedLocation(newLocation);
                    // theMap.looking_for = "GROUND";
                }
                break;

            case scanning:
            // scan the map
                decision = d.scan();
                break;                    
            case stopping:
            // stop the drone
                decision = d.stop(); 
                break;

            default:
                decision = d.stop();
                break;
        }

        return decision;
    }
}

