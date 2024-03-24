package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;

public class MapMakerTest {
    // Test class for MapMaker actions.
    private MapMaker testMap;

    @BeforeEach
    public void initMap() {
        // () -> void
        //This method sets up the test environment before each test by initializing a MapMaker with East heading and a Compass.
        Compass testCompass = new Compass(Heading.E);
        testMap = new MapMaker(Heading.E, testCompass);
    }

    @Test
    public void shouldPut() {
        //() -> void
        //This test verifies that the put method correctly adds a JSONObject to the map.
        Compass testCompass = new Compass(Heading.E);
        MapMaker testMap = new MapMaker(Heading.E, testCompass);

        testMap.put(Heading.E, new JSONObject().put("testkey", "testvalue"));
        assertTrue(testMap.map.containsKey(Heading.E));
        JSONObject testMapValue = testMap.map.get(Heading.E);
        assertEquals(testMapValue.get("testkey"), "testvalue");
    }

    @Test
    public void shouldBeStuck() {
        //() -> void
        //This test verifies that the isStuck method correctly returns true when the drone is stuck.
        testMap.map_compass.addVisitedLocation(new Location(1, 0));
        testMap.map_compass.addVisitedLocation(new Location(1, -1));

        testMap.put(Heading.E, new JSONObject().put("testkey", "testvalue"));
        testMap.put(Heading.S, new JSONObject().put("testkey", "testvalue"));

        assertTrue(testMap.isStuck());
    }

    @Test
    public void shouldChooseGround() throws Exception {
        //() -> void
        //This test verifies that the choose method correctly chooses the best direction to move in.
        testMap.put(Heading.E, new JSONObject()
            .put("range", "2")
            .put("found", "GROUND"));

        testMap.put(Heading.S, new JSONObject()
            .put("range", "51")
            .put("found", "OUT_OF_RANGE"));

        testMap.put(Heading.N, new JSONObject()
            .put("range", "0")
            .put("found", "OUT_OF_RANGE"));

        testMap.choose();
        assertEquals(testMap.best_direction, Heading.E);
    }

    @Test
    public void shouldChooseUnvisitedGround() throws Exception {
        //() -> void
        //This test verifies that the choose method correctly chooses the best direction to move in.
        // we have already visited E
        testMap.map_compass.addVisitedLocation(new Location(1, 0));
        // so we shouldnt choose E ground
        testMap.put(Heading.E, new JSONObject()
            .put("range", "2")
            .put("found", "GROUND"));
        // should choose S ground
        testMap.put(Heading.S, new JSONObject()
            .put("range", "2")
            .put("found", "GROUND"));

        testMap.put(Heading.N, new JSONObject()
            .put("range", "0")
            .put("found", "OUT_OF_RANGE"));

        testMap.choose();
        assertEquals(testMap.best_direction, Heading.S);
    }

    @Test
    public void shouldResetMap() {
        //() -> void
        //This test verifies that the reset method correctly resets the map.
        testMap.reset();
        assertEquals(testMap.size(), 0);
    }

    @Test
    public void shouldGetSize() {
        //() -> void
        //This test verifies that the size method correctly returns the size of the map.
        testMap.put(Heading.E, new JSONObject().put("testkey", "testvalue"));
        assertEquals(testMap.size(), 1);
    }

}
