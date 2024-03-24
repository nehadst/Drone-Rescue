package ca.mcmaster.se2aa4.island.team102;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class Tracker {
//Tracks identified features of interest, such as creeks and emergency sites, during exploration.    

    // map ID to Location
    Map<String, Location> creeks = new HashMap<>();
    Map<String, Location> emergency_site = new HashMap<>();

    public void addCreek(String id, Location loc) {
    //(String, Location) -> void
    //Records a creek's ID and its location.    
        creeks.put(id, loc);
    }
    public void addEmergencySite(String id, Location loc) {
    //(String, Location) -> void
    //Records an emergency site's ID and its location.  
        emergency_site.put(id, loc);
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

    public double findDistance(Location a, Location b) {
    //(Location, Location) -> double
    //Finds the distance between two locations.    
        return Math.sqrt(((b.x - a.x) * (b.x - a.x)) + ((b.y - a.y) * (b.y - a.y)));
    }

}