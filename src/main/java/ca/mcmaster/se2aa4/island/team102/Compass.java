package ca.mcmaster.se2aa4.island.team102;
import java.util.EnumMap;
import java.util.Map;

public class Compass {

    public enum Heading { N, S, E, W }
    public Heading heading;
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
    Compass (Heading heading) { this.heading = heading; }
    Heading getHeading() { return heading; }
    Heading getLeftHeading() { return convert_to_left.get(this.heading); }
    Heading getRightHeading() { return convert_to_right.get(this.heading); }

}