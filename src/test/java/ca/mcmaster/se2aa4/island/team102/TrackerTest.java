package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackerTest {
    // Test class for Tracker actions.
    private Tracker testTracker;

    @BeforeEach
    public void initTracker() {testTracker = new Tracker();}
    // () -> void
    //This method sets up the test environment before each test by initializing a Tracker.

    @Test
    public void shouldAddCreek() {
        //() -> void
        //This test verifies that the addCreek method correctly adds a creek to the creeks list.
        testTracker.addCreek("testID", new Location(0, 0));
        assertEquals(testTracker.creeks.size(), 1);
    }

    @Test
    public void shouldAddERSite() {
        //() -> void
        //This test verifies that the addEmergencySite method correctly adds an emergency site to the emergency_site list.
        testTracker.addEmergencySite("testID", new Location(0, 0));
        assertEquals(testTracker.emergency_site.size(), 1);
    }

    @Test
    public void nothingFound() {
        //() -> void
        //This test verifies that the findClosestCreek method correctly returns "no creek found" when no creeks are found.
        assertEquals(testTracker.findClosestCreek(), "no creek found");
    }

    @Test
    public void chooseCreekNoER() {
        //() -> void
        //This test verifies that the findClosestCreek method correctly returns the only creek found when no emergency sites are found.
        testTracker.addCreek("testID", new Location(0, 0));
        assertEquals(testTracker.findClosestCreek(), "testID");
    }

    @Test
    public void chooseClosestCreektoER() {
        //() -> void
        //This test verifies that the findClosestCreek method correctly returns the closest creek to the emergency site.
        testTracker.addEmergencySite("testERID", new Location(0, 0));
        testTracker.addCreek("testCREEKID2", new Location(2, 2));
        testTracker.addCreek("testCREEKID3", new Location(3, 3));
        testTracker.addCreek("testCREEKID1", new Location(1, 1));

        assertEquals(testTracker.findClosestCreek(), "testCREEKID1");

    }

    @Test
    public void calcDistance() {
        //() -> void
        //This test verifies that the findDistance method correctly calculates the distance between two locations.
        Location a = new Location(0, 0);
        Location b = new Location (3, 4);
        assertEquals(testTracker.findDistance(a, b), 5);
    }
}
