package ca.mcmaster.se2aa4.island.team102;

public class Location {
    int x, y;
    public Location(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }

    public void update(Location change_in_loc) {
        this.x += change_in_loc.x;
        this.y += change_in_loc.y;
    }

    public Location calculate(Location change_in_loc) {
        return new Location(this.x + change_in_loc.x, this.y + change_in_loc.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location loc = (Location) o;

        if (x != loc.x) return false;
        return y == loc.y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + x + y;
        return result;
    }


}
