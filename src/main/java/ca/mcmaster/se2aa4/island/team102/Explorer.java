package ca.mcmaster.se2aa4.island.team102;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

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
        JSONObject decision;
        emergency_return();
        decision = selectedAlgorithm.executeAlgorithm(d, compass, theMap, echoer);
        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }

    private AlgorithmSelector setAlgorithm(Integer algorithmType) {
        // If more algorithms are added, we can use a switch statement to select the algorithm
        
        switch (algorithmType) {
            default:
                return new PrimaryAlgorithm();
        }
    }

    public void acknowledgeResults(String s) {}


    private void emergency_return(){
        // If in any emergency state such as low battery, return immediately
        if (current_budget <= initial_budget / 2) {
            logger.info("The drone is returning to the starting point due to low battery");
            d.currentState = State.stopping;
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
        logger.info("Contents of creek_map {}", tracker.creeks);
        logger.info("Contents of emergency site map {}", tracker.emergency_site);
        // TODO: Work on the algo more to ensure emergency site is found
        // Other wise we cant use the tracker.find_closest_creek() method
        return "no creek found";
    }
}

    

    

