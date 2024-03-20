package ca.mcmaster.se2aa4.island.team102;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tracker {

    // map ID to Location
    HashMap<String, Location> creeks = new HashMap<>();
    HashMap<String, Location> emergency_site = new HashMap<>();

    public void add_creek(String ID, Location loc) {
        creeks.put(ID, loc);
    }
    public void add_emergency_site(String ID, Location loc) {
        emergency_site.put(ID, loc);
    }

    public String find_closest_creek() {
        List<String> ER_IDS = new ArrayList<>(emergency_site.keySet());
        String ER_ID = ER_IDS.get(0);
        Location ER_Loc = emergency_site.get(ER_ID);
        List<String> CREEKS_ID = new ArrayList<>(creeks.keySet());
        String closest_creek = "";
        double distance = Double.POSITIVE_INFINITY;

        for (String creek_id : CREEKS_ID) {
            Location creek_loc = creeks.get(creek_id);
            double new_dist = find_distance(creek_loc, ER_Loc);
            if (new_dist < distance) {
                distance = new_dist;
                closest_creek = creek_id; 
            }
        }
        return closest_creek;


    }

    public double find_distance(Location A, Location B) {
        return Math.sqrt(((B.x - A.x) * (B.x - A.x)) + ((B.y - A.y) * (B.y - A.y)));
    }

}