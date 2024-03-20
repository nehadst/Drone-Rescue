package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONObject;


//Interface defining the behavior of an algorithm selector.
//This interface is used to select and execute the appropriate algorithm for the drone's exploration and decision-making process.

public interface AlgorithmSelector {
    JSONObject executeAlgorithm(Drone d, Compass compass, MapMaker theMap, Echoer echoer);
}
