package ca.mcmaster.se2aa4.island.team102;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;



public class Compass {
//This class represents a compass for navigation purposes, keeping track of the drone's current heading,
// visited locations, and methods to determine new headings based on best direction.
    private Location loc = new Location(0, 0);
    private Map<List<Heading>, Location> update_coordinates = new HashMap<>();
    public Heading heading;
    private Set<Location> visited = new HashSet<>();
    private EnumMap<Heading, Heading> convert_to_right = new EnumMap<>(Map.of(
        Heading.N, Heading.E,
        Heading.E, Heading.S,
        Heading.S, Heading.W,
        Heading.W, Heading.N
    ));
    private EnumMap<Heading, Heading> convert_to_left = new EnumMap<>(Map.of(
        Heading.N, Heading.W,
        Heading.W, Heading.S,
        Heading.S, Heading.E,
        Heading.E, Heading.N
    ));


    Compass (Heading heading) { 
        // Compass(Heading) -> Compass
        // Constructor initializes the compass with a specific heading and sets up initial mappings for coordinate updates.


        this.heading = heading; 
        this.update_coordinates.put(Arrays.asList(Heading.E, Heading.S), new Location(1, -1));
        this.update_coordinates.put(Arrays.asList(Heading.E, Heading.N), new Location(1, 1));
        this.update_coordinates.put(Arrays.asList(Heading.E, Heading.E), new Location(1, 0));
        this.update_coordinates.put(Arrays.asList(Heading.S, Heading.W), new Location(-1, -1));
        this.update_coordinates.put(Arrays.asList(Heading.S, Heading.E), new Location(1, -1));
        this.update_coordinates.put(Arrays.asList(Heading.S, Heading.S), new Location(0, -1));
        this.update_coordinates.put(Arrays.asList(Heading.N, Heading.E), new Location(1, 1));
        this.update_coordinates.put(Arrays.asList(Heading.N, Heading.W), new Location(-1, 1));
        this.update_coordinates.put(Arrays.asList(Heading.N, Heading.N), new Location(0, 1));
        this.update_coordinates.put(Arrays.asList(Heading.W, Heading.N), new Location(-1, 1));
        this.update_coordinates.put(Arrays.asList(Heading.W, Heading.S), new Location(-1, -1));
        this.update_coordinates.put(Arrays.asList(Heading.W, Heading.W), new Location(-1, 0));
        
    }
    Heading getHeading() { return heading; }
    // () -> Heading
    // Returns the current heading of the compass.
    Heading getLeftHeading() { return convert_to_left.get(this.heading); }
    // () -> Heading
    // Calculates and returns the left heading relative to the current heading.
    Heading getRightHeading() { return convert_to_right.get(this.heading); }
     // () -> Heading
    // Calculates and returns the right heading relative to the current heading.
    Location newCoordinates(Heading best_direction) { return this.update_coordinates.get(Arrays.asList(this.heading, best_direction)); }
    // (Heading) -> Location
    // Returns new coordinates based on the best direction given as a parameter.


    public void updateCoordinates(Heading best_direction) {
    // (Heading) -> void
    // Updates the current location coordinates based on the best direction provided.
        Location change_in_loc = newCoordinates(best_direction);
        loc.update(change_in_loc);
    }

    public Location peekCoordinates(Heading best_direction) {
    // (Heading) -> Location
    // Calculates and returns potential new coordinates without updating current location, based on the best direction.    
        Location change_in_loc = newCoordinates(best_direction);
        return loc.calculate(change_in_loc);
    }

    public void addVisitedLocation(Location loc) {
    // (Location) -> void
    // Adds a new location to the set of already visited locations. 
        visited.add(loc);
    }

    public Location getCoordinates() {
    // () -> Location
    // Returns a copy of the current location to prevent modification through external references.
        return new Location(this.loc.x, this.loc.y);
    }
    public boolean alreadyVisited(Location new_location) {
    // (Location) -> boolean
    // Checks if a given location has already been visited.    
        return this.visited.contains(new_location);
    }

}