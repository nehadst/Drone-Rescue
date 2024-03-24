package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {
    // Test class for Location actions.
    private Location testLocation;

    @BeforeEach
    public void initLocation() {testLocation = new Location(0, 0);}
    // () -> void
    //This method sets up the test environment before each test by initializing a Location at (0, 0).

    @Test
    public void shouldUpdate() {
        //() -> void
        //This test verifies that the update method correctly updates the location to (1, 1).
        testLocation.update(new Location(1,1));
        assertEquals(testLocation, new Location(1, 1));
    }

    @Test
    public void shouldCalculate() {
        //() -> void
        //This test verifies that the calculate method correctly calculates the location (1, 1) from the current location (0, 0).
        Location testCalc = testLocation.calculate(new Location(1, 1));
        assertEquals(testCalc, new Location(1, 1));
    }





}
