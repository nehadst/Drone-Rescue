package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONObject;

public interface AlgorithmSelector {
    JSONObject executeAlgorithm(Drone d, Compass compass, MapMaker theMap, Echoer echoer);
}
