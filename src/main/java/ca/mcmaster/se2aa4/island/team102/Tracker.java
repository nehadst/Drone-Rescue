package ca.mcmaster.se2aa4.island.team102;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tracker {
//Tracks identified features of interest, such as creeks and emergency sites, during exploration.    

    // map ID to Location
    HashMap<String, Location> creeks = new HashMap<>();
    HashMap<String, Location> emergency_site = new HashMap<>();

    public void addCreek(String ID, Location loc) {
    //(String, Location) -> void
    //Records a creek's ID and its location.    
        creeks.put(ID, loc);
    }
    public void addEmergencySite(String ID, Location loc) {
    //(String, Location) -> void
    //Records an emergency site's ID and its location.  
        emergency_site.put(ID, loc);
    }

    public String findClosestCreek() {
    //() -> String
    //Finds the closest creek to the emergency site.    
        List<String> erIds = new ArrayList<>(emergency_site.keySet());
        List<String> creeksId = new ArrayList<>(creeks.keySet());
        // if no emergency sites found, return first creek in list
        if (erIds.isEmpty()) {
            if (!creeksId.isEmpty()) {
                return creeksId.get(0);
            }
            else {
                return "no creek found";
            }
        }
        String erId = erIds.get(0);
        Location erLoc = emergency_site.get(erId);
        String closestCreek = "";
        double distance = Double.POSITIVE_INFINITY;

        for (String creek_id : creeksId) {
            Location creekLoc = creeks.get(creek_id);
            double newDist = findDistance(creekLoc, erLoc);
            if (newDist < distance) {
                distance = newDist;
                closestCreek = creek_id; 
            }
        }
        return closestCreek;


    }

    public double findDistance(Location A, Location B) {
    //(Location, Location) -> double
    //Finds the distance between two locations.    
        return Math.sqrt(((B.x - A.x) * (B.x - A.x)) + ((B.y - A.y) * (B.y - A.y)));
    }

}