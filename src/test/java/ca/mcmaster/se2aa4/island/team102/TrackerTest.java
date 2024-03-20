package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrackerTest {
    private Tracker testTracker;

    @BeforeEach
    public void initTracker() {testTracker = new Tracker();}

    @Test
    public void shouldAddCreek() {
        testTracker.add_creek("testID", new Location(0, 0));
        assertEquals(testTracker.creeks.size(), 1);
    }

    @Test
    public void shouldAddERSite() {
        testTracker.add_emergency_site("testID", new Location(0, 0));
        assertEquals(testTracker.emergency_site.size(), 1);
    }

    @Test
    public void nothingFound() {
        assertEquals(testTracker.find_closest_creek(), "no creek found");
    }

    @Test
    public void chooseCreekNoER() {
        testTracker.add_creek("testID", new Location(0, 0));
        assertEquals(testTracker.find_closest_creek(), "testID");
    }

    @Test
    public void chooseClosestCreektoER() {
        testTracker.add_emergency_site("testERID", new Location(0, 0));
        testTracker.add_creek("testCREEKID2", new Location(2, 2));
        testTracker.add_creek("testCREEKID3", new Location(3, 3));
        testTracker.add_creek("testCREEKID1", new Location(1, 1));

        // testCREEKID1 (1, 1) should be closest to testERID (0, 0)
        assertEquals(testTracker.find_closest_creek(), "testCREEKID1");

    }

    @Test
    public void calcDistance() {
        // dist between (0, 0) and (3, 4) should be 5
        Location A = new Location(0, 0);
        Location B = new Location (3, 4);
        assertEquals(testTracker.find_distance(A, B), 5);
    }
}
