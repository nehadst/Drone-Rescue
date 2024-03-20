package ca.mcmaster.se2aa4.island.team102;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {
// Represents the core class of the exploration mission, handling initialization, decision-making, and final reporting of the exploration activities.   

    private final Logger logger = LogManager.getLogger();
    private int initial_budget;
    private MapMaker theMap;
    private Echoer echoer = new Echoer();
    private Drone d = new Drone();
    private Compass compass;
    private Tracker tracker = new Tracker();
    private ScanParser parser;
    private AlgorithmSelector selectedAlgorithm;
    private ResultAcknowledger resultAcknowledger;

    @Override
    public void initialize(String s) {
    //(String) -> void
    //Initializes the exploration command center with the given JSON string. 
    //The string contains information about the initial state of the drone and the budget for the exploration mission.    
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

        // Select algorithm, acknowledger, and parser for the drone to use
        this.selectedAlgorithm = setAlgorithm(0);
        this.resultAcknowledger = setAcknowledger(0);
        this.parser = setParser(0);
    }

    @Override
    public String takeDecision() {
    //() -> String
    //Determines the next action for the drone to take based on the current state and returns it to a JSON string.    
        JSONObject decision;
        emergency_return();
        decision = selectedAlgorithm.executeAlgorithm(d, compass, theMap, echoer);
        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }

    private AlgorithmSelector setAlgorithm(Integer algorithmType) {
        //(Integer) -> AlgorithmSelector
        //Selects the exploration algorithm based on the type provided and returns the corresponding selector.
        
        switch (algorithmType) {
            default:
                return new PrimaryAlgorithm();
        }
    }

    private ScanParser setParser(Integer parserType) {
        // (Integer) -> ScanParsing
        // Selects the type of parser to use from the ScanParsing interface

        switch (parserType) {
            default:
                return new ScanParser();
        }
    }

    private ResultAcknowledger setAcknowledger(Integer acknowledgeType) {
        // (Integer) -> ResultAcknowledger
        // Selects the type of acknowledger to use from the ResultAcknowledger interface

        switch (acknowledgeType) {
            default:
                return new DefaultResultAcknowledger();
        }
    }

    public void acknowledgeResults(String s) {
        //(String) -> void
        //Acknowledges the results of the last action, updates the state, and logs any changes.
        d.currentState = resultAcknowledger.executeAcknowledgement(parser, compass, theMap, tracker, d, d.currentState, s);
    }

    private void emergency_return(){

         //() -> void
         // If in any emergency state such as low battery, return immediately to the starting point.
        if (d.battery <= initial_budget / 2) {
            logger.info("The drone is returning to the starting point due to low battery");
            d.currentState = State.stopping;
        }
    }

    @Override
    public String deliverFinalReport() {
        //() -> String
        //Compiles and delivers the final report at the end of the exploration, providing details of discoveries.
        // for debugging purposes only
        // try {
        //     Thread.sleep(3000000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        logger.info("Contents of creek_map {}", tracker.creeks);
        logger.info("Contents of emergency site map {}", tracker.emergency_site);
        String closest_creek = tracker.find_closest_creek();
        logger.info("Closest creek is {}", closest_creek);
        return closest_creek;
    }
}