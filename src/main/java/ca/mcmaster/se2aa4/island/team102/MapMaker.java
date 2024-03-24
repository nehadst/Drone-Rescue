package ca.mcmaster.se2aa4.island.team102;
import java.util.Objects;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.json.JSONObject;

public class MapMaker {
//This class is used for tracking and manipulating the map of the simulated environment.    
    int map_capacity = 3;
    Map<Heading, JSONObject> map = new LinkedHashMap<>(map_capacity);
    Heading best_direction;
    String looking_for = "GROUND";
    Compass map_compass;

    public void put(Heading heading, JSONObject extraInfo) {
    //(Heading, JSONObject) -> void
    //Puts the heading and extraInfo into the map.    
        map.put(heading, extraInfo);
    }

    public MapMaker (Heading initialHeading, Compass compass) {
    //(Heading, Compass) -> MapMaker
    //Initializes a MapMaker with initialHeading and compass.    
        this.best_direction = initialHeading;
        this.map_compass = compass;
    }

    public boolean isStuck() {
    //() -> boolean
    //Determines if the current state is stuck based on visited locations and map information.    
        List<Heading> keys = new ArrayList<>(map.keySet());

        for (Heading direction : keys) {
            if (!this.map_compass.alreadyVisited(this.map_compass.peekCoordinates(direction))) {
                return false;
            }            
        }
        return true;

    }

    public void choose() throws Exception {
    //() -> void
    //Chooses the next best direction based on map information. Throws Exception if stuck.    
        List<Heading> keys = new ArrayList<>(map.keySet());

        if (this.isStuck()) {
            throw new Exception("STUCK");
        }

        for (Heading direction : keys) {
            JSONObject extraInfo = map.get(direction);
            // type should be "OUT_OF_RANGE" or "GROUND"
            String type = extraInfo.getString("found");
            // return range for ground as soon as we get it
            // same direction of ground will be given in orderedmap
            if (Objects.equals(type, this.looking_for)
                && !this.map_compass.alreadyVisited(this.map_compass.peekCoordinates(direction))) {
                this.best_direction = direction;
                return;
            }
            
        }


    }

    public void reset() {
    //() -> void
    //Resets the map.    
        map.clear();
    }

    public int size() {
    //() -> int
    //Returns the size of the map.    
        return map.size();
    }
}

