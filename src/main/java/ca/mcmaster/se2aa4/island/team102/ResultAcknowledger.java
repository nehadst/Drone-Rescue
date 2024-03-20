
package ca.mcmaster.se2aa4.island.team102;

public interface ResultAcknowledger {

    State executeAcknowledgement(ScanParsing parser, Compass compass, MapMaker theMap, Tracker tracker, Drone d, State currentState, String s);

}
