package ca.mcmaster.se2aa4.island.team102;

public class Location {
    //This class is used for tracking and manipulating positions within the simulated environment.
    int x, y;
    public Location(int _x, int _y) {
    //(int, int) -> Location
    //Initializes a location with specific x and y coordinates.    
        this.x = _x;
        this.y = _y;
    }

    public void update(Location change_in_loc) {
    //(Location) -> void
    //Updates the location by adding the change_in_loc to the current location.    
        this.x += change_in_loc.x;
        this.y += change_in_loc.y;
    }

    public Location calculate(Location change_in_loc) {
    //(Location) -> Location
    //Calculates the new location by adding the change_in_loc to the current location and returns the new location.    
        return new Location(this.x + change_in_loc.x, this.y + change_in_loc.y);
    }

    @Override
    public boolean equals(Object o) {
    //(Object) -> boolean
    //Checks if the current location is equal to another location.    
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location loc = (Location) o;

        if (x != loc.x) return false;
        return y == loc.y;
    }

    @Override
    public int hashCode() {
    //() -> int
    //Generates a hash code for the current location.    
        final int prime = 31;
        int result = 1;
        result = (prime * result) + x + y;
        return result;
    }


}

