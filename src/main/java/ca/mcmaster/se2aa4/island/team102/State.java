
package ca.mcmaster.se2aa4.island.team102;


public enum State {
//This enum class defines the possible states of the drone.
//The drone can be in one of the following states at any given time:    
    exploring, // The drone is moving to explore new areas.
    scanning, // The drone is scanning the area for interesting features.
    found_creek, // A creek has been found during exploration or scanning.
    returning, // The drone is returning to the base or a specified location.
    stopping, // The drone is stopping its operation, typically at the end of the mission.
    asking_front, // The drone is querying information about the area directly in front of it.
    asking_left, // The drone is querying information about the area to its left.
    asking_right, // The drone is querying information about the area to its right.
    turning // The drone is changing its direction.
}
