package ca.mcmaster.se2aa4.island.team102;

import java.util.Set;
import java.util.Objects;
import java.util.LinkedHashMap;
import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team102.Compass.Heading;

public class MapMaker {
    int map_capacity = 4;
    LinkedHashMap<Heading, JSONObject> map = new LinkedHashMap<>(map_capacity);
    Heading best_direction;

    public void put(Heading heading, JSONObject extraInfo) {
        map.put(heading, extraInfo);
    }

    public MapMaker (Heading initial_heading) {
        this.best_direction = initial_heading;
    }

    public void choose() {
        Integer smallest_out_of_range = 1000;
        Set<Heading> keys = map.keySet();

        for (Heading direction : keys) {
            JSONObject extraInfo = map.get(direction);
            // type should be "OUT_OF_RANGE" or "GROUND"
            String type = extraInfo.getString("found");
            // range should be an Integer (how far out of range or ground is)
            Integer range = extraInfo.getInt("range");
            // return range for ground as soon as we get it
            // same direction of ground will be given in orderedmap
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