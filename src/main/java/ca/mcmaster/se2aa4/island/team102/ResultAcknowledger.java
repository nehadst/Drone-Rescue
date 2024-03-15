
package ca.mcmaster.se2aa4.island.team102;
import org.json.JSONArray;

public interface ResultAcknowledger {
    public void acknowledgeResults(JSONArray parse, Compass compass, MapMaker theMap, Tracker tracker, Drone d, State currentState, int current_budget, String s);
}