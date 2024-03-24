package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackerTest {
    private Tracker testTracker;

    @BeforeEach
    public void initTracker() {testTracker = new Tracker();}

    @Test
    public void shouldAddCreek() {
        testTracker.addCreek("testID", new Location(0, 0));
        assertEquals(testTracker.creeks.size(), 1);
    }

    @Test
    public void shouldAddERSite() {
        testTracker.addEmergencySite("testID", new Location(0, 0));
        assertEquals(testTracker.emergency_site.size(), 1);
    }

    @Test
    public void nothingFound() {
        assertEquals(testTracker.findClosestCreek(), "no creek found");
    }

    @Test
    public void chooseCreekNoER() {
        testTracker.addCreek("testID", new Location(0, 0));
        assertEquals(testTracker.findClosestCreek(), "testID");
    }

    @Test
    public void chooseClosestCreektoER() {
        testTracker.addEmergencySite("testERID", new Location(0, 0));
        testTracker.addCreek("testCREEKID2", new Location(2, 2));
        testTracker.addCreek("testCREEKID3", new Location(3, 3));
        testTracker.addCreek("testCREEKID1", new Location(1, 1));

        // testCREEKID1 (1, 1) should be closest to testERID (0, 0)
        assertEquals(testTracker.findClosestCreek(), "testCREEKID1");

    }

    @Test
    public void calcDistance() {
        // dist between (0, 0) and (3, 4) should be 5
        Location a = new Location(0, 0);
        Location b = new Location (3, 4);
        assertEquals(testTracker.findDistance(a, b), 5);
    }
}
