
package ca.mcmaster.se2aa4.island.team102;

public interface ResultAcknowledger {
    public State executeAcknowledgement(ScanParser parser, Compass compass, MapMaker theMap, Tracker tracker, Drone d, State currentState, int current_budget, String s);
}
