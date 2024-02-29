package ca.mcmaster.se2aa4.island.team102;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.team102.Compass.Heading;

import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private int initial_budget;
    private int current_budget;
    private MapMaker theMap = new MapMaker();
    private Echoer echoer = new Echoer();
    private Drone d = new Drone();
    private Compass compass;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        Heading initial_heading = Heading.valueOf(info.getString("heading"));
        compass = new Compass(initial_heading);
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
            d.currentState = State.returning;
        }
    }

    @Override
    public String takeDecision() {

        JSONObject decision = new JSONObject();
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
                d.currentState = State.stopping;
                break;
                    
            case stopping:
                decision.put("action", "stop");   
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
                theMap.choose();
                theMap.reset();
                logger.info("The best direction to travel in is {}", theMap.best_direction);
                d.currentState = State.exploring;
                break;

            // after flight, go back to verification of neighbors and change heading of compass
            case exploring:
                compass.heading = theMap.best_direction;
                d.currentState = State.asking_front;
                break;
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
