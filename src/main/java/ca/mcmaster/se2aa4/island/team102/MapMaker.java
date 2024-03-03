package ca.mcmaster.se2aa4.island.team102;
import java.util.Random;
import java.util.Set;
import java.util.Objects;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.Optional;
import ca.mcmaster.se2aa4.island.team102.Compass.Heading;

public class MapMaker {
    Random random = new Random();
    int map_capacity = 3;
    LinkedHashMap<Heading, JSONObject> map = new LinkedHashMap<>(map_capacity);
    Heading best_direction;
    String looking_for = "GROUND";
    Compass map_compass;

    public void put(Heading heading, JSONObject extraInfo) {
        map.put(heading, extraInfo);
    }

    public MapMaker (Heading initial_heading, Compass compass) {
        this.best_direction = initial_heading;
        this.map_compass = compass;
    }
    // // check if all neighbouring directions have been visited (=> stuck)
    // public boolean is_stuck() {
    //     List<Heading> keys = new ArrayList<>(map.keySet());

    //     for (Heading direction : keys) {
    //         JSONObject extraInfo = map.get(direction);
    //         String type = extraInfo.getString("found");
    //         if (!this.map_compass.alreadyVisited(this.map_compass.peekCoordinates(direction))) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    // check if there is UNVISITED NEIGHBOURING GROUND
    // public Optional<String> unvisited_ground(List<Heading> keys) {

    //     for (Heading direction : keys) {
    //         JSONObject extraInfo = map.get(direction);
    //         String type = extraInfo.getString("found");
    //         if (!this.map_compass.alreadyVisited(this.map_compass.peekCoordinates(direction))
    //             && (Objects.equals(type, this.looking_for))) {
    //             return Optional.of(direction.toString());
    //         }
    //     }
    //     return Optional.empty();
    // }

    // // check if there is ANY UNVISITED NEIGHBOUR IN RANGE
    // public Optional<String> unvisited(List<Heading> keys) {

    //     for (Heading direction : keys) {
    //         JSONObject extraInfo = map.get(direction);
    //         Integer range = extraInfo.getInt("range");
    //         if (!this.map_compass.alreadyVisited(this.map_compass.peekCoordinates(direction))
    //             && (range >= 1)) {
    //             return Optional.of(direction.toString());
    //         }
    //     }
    //     return Optional.empty();
    // }

    public void choose() {

        List<Heading> keys = new ArrayList<>(map.keySet());
        // // choose a random direction if all have already been visited
        // if (this.is_stuck()) {
        //     this.best_direction = keys.get(random.nextInt(3));        
        //     return;
        // }

        // unvisited ground > unvisited area > current_direction
        // TODO: replace current_direction with random?
        // Optional<String> primary = unvisited_ground(keys);
        // Optional<String> secondary = unvisited(keys);
        // if (primary.isPresent()) {
        //     this.best_direction = Heading.valueOf(primary.get());
        // } else if (secondary.isPresent()) {
        //     this.best_direction = Heading.valueOf(secondary.get());
        // }
        for (Heading direction : keys) {
            JSONObject extraInfo = map.get(direction);
            // type should be "OUT_OF_RANGE" or "GROUND"
            String type = extraInfo.getString("found");
            // range should be an Integer (how far out of range or ground is)
            Integer range = extraInfo.getInt("range");
            // return range for ground as soon as we get it
            // same direction of ground will be given in orderedmap
            switch (this.looking_for) {
                case "GROUND":
                    // looking for ground minimum conditions:
                    // 1. it is ground
                    // 2. it hasnt already been visited
                    if (Objects.equals(type, this.looking_for) 
                        && !this.map_compass.alreadyVisited(this.map_compass.peekCoordinates(direction))) {
                        this.best_direction = direction;
                        return;
                    }
                    break;
                case "OUT_OF_RANGE":
                    // looking for non-ground minimum conditions:
                    // 1. it is non ground
                    // 2. it is in range
                    if (Objects.equals(type, this.looking_for)
                        && range >= 2) {
                        this.best_direction = direction;
                        return;
                    }

            }

        }
        // if no nearby unvisited ground, try to find any UNVISITED tile in RANGE
        for (Heading direction : keys) {
            JSONObject extraInfo = map.get(direction);
            // type should be "OUT_OF_RANGE" or "GROUND"
            String type = extraInfo.getString("found");
            // range should be an Integer (how far out of range or ground is)
            Integer range = extraInfo.getInt("range");
            if (Objects.equals(type, "OUT_OF_RANGE")
                && range >= 2) {
                this.best_direction = direction;
                return;
            }

        }
    }


        // for (Heading direction : keys) {
        //     JSONObject extraInfo = map.get(direction);
        //     // type should be "OUT_OF_RANGE" or "GROUND"
        //     String type = extraInfo.getString("found");
        //     // range should be an Integer (how far out of range or ground is)
        //     Integer range = extraInfo.getInt("range");
        //     // return range for ground as soon as we get it
        //     // same direction of ground will be given in orderedmap
        //     // also try to choose ground which hasnt been visited already
        //     // if none such available then continue in current direction
        //     if (Objects.equals(type, this.looking_for) 
        //         && (!this.map_compass.alreadyVisited(this.map_compass.peekCoordinates(direction)))
        //         ) {
        //         this.best_direction = direction;
        //         return;
        //     }
            
        // }

    // }

    public void reset() {
        map.clear();
    }

    public int size() {
        return map.size();
    }


}