package ca.mcmaster.se2aa4.island.team102;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompassTest {

    private Compass testCompass;

    @BeforeEach
    public void initCompass() { testCompass = new Compass(Heading.E); }

    @Test
    public void shouldGetLeft() {
        assertEquals(testCompass.getLeftHeading(), Heading.N);
    }

    @Test
    public void shouldGetRight() {
        assertEquals(testCompass.getRightHeading(), Heading.S);
    }

    @Test
    public void shouldGetFront() {
        assertEquals(testCompass.getHeading(), Heading.E);
    }

    @Test
    public void peekFront() {
        assertEquals(testCompass.peekCoordinates(Heading.E), new Location(1, 0));
    }

    @Test
    public void peekRightTurn() {
        assertEquals(testCompass.peekCoordinates(Heading.S), new Location(1, -1));
    }

    @Test
    public void peekLeftTurn() {
        assertEquals(testCompass.peekCoordinates(Heading.N), new Location(1, 1));
    }

    @Test
    public void addVisited() {
        testCompass.addVisitedLocation(new Location(0, 0));
        assertEquals(testCompass.alreadyVisited(new Location(0, 0)), true);
    }





}
