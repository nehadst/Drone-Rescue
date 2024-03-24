package ca.mcmaster.se2aa4.island.team102;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompassTest {
    // Test for compass class.
    private Compass testCompass;

    @BeforeEach
    public void initCompass() { testCompass = new Compass(Heading.E); }
    // () -> void
    //This method sets up the test environment before each test, initializing a Compass with East heading.

    @Test
    public void shouldGetLeft() {
        //() -> void
        //This test verifies that the getLeftHeading method correctly returns North when the compass is facing East.
        assertEquals(testCompass.getLeftHeading(), Heading.N);
    }

    @Test
    public void shouldGetRight() {
        //() -> void
        //This test verifies that the getRightHeading method correctly returns South when the compass is facing East.
        assertEquals(testCompass.getRightHeading(), Heading.S);
    }

    @Test
    public void shouldGetFront() {
        //() -> void
        //This test verifies that the getFrontHeading method correctly returns East when the compass is facing East.
        assertEquals(testCompass.getHeading(), Heading.E);
    }

    @Test
    public void peekFront() {
        //() -> void
        //This test verifies that peekCoordinates correctly predicts the next coordinates when moving forward (East) without actually moving.
        assertEquals(testCompass.peekCoordinates(Heading.E), new Location(1, 0));
    }

    @Test
    public void peekRightTurn() {
        //() -> void
        //This test verifies that peekCoordinates correctly predicts the next coordinates when turning right (South) without actually moving.
        assertEquals(testCompass.peekCoordinates(Heading.S), new Location(1, -1));
    }

    @Test
    public void peekLeftTurn() {
        //() -> void
        //This test verifies that peekCoordinates correctly predicts the next coordinates when turning left (North) without actually moving.
        assertEquals(testCompass.peekCoordinates(Heading.N), new Location(1, 1));
    }

    @Test
    public void addVisited() {
        //() -> void
        //This test checks if addVisitedLocation accurately records a new location as visited.
        testCompass.addVisitedLocation(new Location(0, 0));
        assertEquals(testCompass.alreadyVisited(new Location(0, 0)), true);
    }





}
