package ca.mcmaster.se2aa4.island.team102;

import java.util.Objects;

public class Location {
    //This class is used for tracking and manipulating positions within the simulated environment.
    public int x;
    public int y;
  
    public Location(int xVal, int yVal) {
    //(int, int) -> Location
    //Initializes a location with specific x and y coordinates.    
        this.x = xVal;
        this.y = yVal;
    }

    public void update(Location changeInLoc) {
    //(Location) -> void
    //Updates the location by adding the changeInLoc to the current location.    
        this.x += changeInLoc.x;
        this.y += changeInLoc.y;
    }

    public Location calculate(Location changeInLoc) {
    //(Location) -> Location
    //Calculates the new location by adding the changeInLoc to the current location and returns the new location.    
        return new Location(this.x + changeInLoc.x, this.y + changeInLoc.y);
    }

    @Override
    public boolean equals(Object o) {
    //(Object) -> boolean
    //Checks if the current location is equal to another location.    
        if (this == o) { return true; }
        if (!(o instanceof Location)) { return false; }

        Location loc = (Location) o;

        return (x == loc.x) && (y == loc.y);
    }

    @Override
    public int hashCode() {
    //() -> int
    //Generates a hash code for the current location.  

        return Objects.hash(x, y);
    }


}

