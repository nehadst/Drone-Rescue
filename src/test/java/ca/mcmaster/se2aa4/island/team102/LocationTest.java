package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {
    private Location testLocation;

    @BeforeEach
    public void initLocation() {testLocation = new Location(0, 0);}

    @Test
    public void shouldUpdate() {
        testLocation.update(new Location(1,1));
        assertEquals(testLocation, new Location(1, 1));
    }

    @Test
    public void shouldCalculate() {
        Location testCalc = testLocation.calculate(new Location(1, 1));
        assertEquals(testCalc, new Location(1, 1));
    }





}
