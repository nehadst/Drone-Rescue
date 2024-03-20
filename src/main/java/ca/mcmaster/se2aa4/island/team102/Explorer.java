package ca.mcmaster.se2aa4.island.team102;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {
// Represents the core class of the exploration mission, handling initialization, decision-making, and final reporting of the exploration activities.   

    private final Logger logger = LogManager.getLogger();
    private int initial_budget;
    private int current_budget;
    private MapMaker theMap;
    private Echoer echoer = new Echoer();
    private Drone d = new Drone();
    private Compass compass;
    private ScanParser parser = new ScanParser();
    private Tracker tracker = new Tracker();
    private AlgorithmSelector selectedAlgorithm;
    private DefaultResultAcknowledger resultAcknowledger;

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
        current_budget = initial_budget;

        // Select algorithm for the drone to use (PrimaryAlgorithm by defualt)
        this.selectedAlgorithm = setAlgorithm(1);
        this.resultAcknowledger = new DefaultResultAcknowledger();
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

    public void acknowledgeResults(String s) {
        //(String) -> void
        //Acknowledges the results of the last action, updates the state, and logs any changes.
        d.currentState = resultAcknowledger.executeAcknowledgement(parser, compass, theMap, tracker, d, d.currentState, current_budget, s);
    }


    private void emergency_return(){
        //() -> void
         // If in any emergency state such as low battery, return immediately to the starting point.
        if (current_budget <= initial_budget / 2) {
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
        // TODO: Work on the algo more to ensure emergency site is found
        // Other wise we cant use the tracker.find_closest_creek() method
        return "no creek found";
    }
}

    

    

