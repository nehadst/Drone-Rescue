package ca.mcmaster.se2aa4.island.team102;

import java.util.HashMap;
import java.util.Objects;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team102.Compass.Heading;

public class MapMaker {

    HashMap<Heading, JSONObject> map = new HashMap<>();
    Heading best_direction;

    public void put(Heading heading, JSONObject extraInfo) {
        map.put(heading, extraInfo);
    }

    public MapMaker (Heading initial_heading) {
        this.best_direction = initial_heading;
    }

    public void choose() {
        Integer smallest_out_of_range = 1000;

        for (HashMap.Entry<Heading, JSONObject> entry : map.entrySet()) {
            Heading direction = entry.getKey();
            JSONObject extraInfo = entry.getValue();
            // type should be "OUT_OF_RANGE" or "GROUND"
            String type = extraInfo.getString("found");
            // range should be an Integer (how far out of range or ground is)
            Integer range = extraInfo.getInt("range");
            // return range for ground as soon as we get it
            // since we know there will be only one ground in 4 directions
            if (Objects.equals(type,"GROUND")) {
                this.best_direction = direction;
                break;
            }
            
        }

    }

    public void reset() {
        map.clear();
    }

    public int size() {
        return map.size();
    }


}