package ca.mcmaster.se2aa4.island.team102;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// use mvn test to test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompassTest {

    private Compass compass;

    @BeforeAll
    public void initCompass() { compass = new Compass(Heading.E); }

    @Test
    public void shouldGetLeft() {
        assertEquals(compass.getLeftHeading(), Heading.N);
    }

    @Test
    public void shouldGetRight() {
        assertEquals(compass.getRightHeading(), Heading.S);
    }

    @Test
    public void shouldGetFront() {
        assertEquals(compass.getHeading(), Heading.E);
    }

    @Test
    public void peekFront() {
        assertEquals(compass.peekCoordinates(Heading.E), new Location(1, 0));
    }

    @Test
    public void peekRightTurn() {
        assertEquals(compass.peekCoordinates(Heading.S), new Location(1, -1));
    }

    @Test
    public void peekLeftTurn() {
        assertEquals(compass.peekCoordinates(Heading.N), new Location(1, 1));
    }

    @Test
    public void addVisited() {
        compass.addVisitedLocation(new Location(0, 0));
        assertEquals(compass.alreadyVisited(new Location(0, 0)), true);
    }





}
