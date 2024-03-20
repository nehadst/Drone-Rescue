package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapMakerTest {
    private MapMaker testMap;
    private Compass testCompass;

    @BeforeEach
    public void initMap() {
        testCompass = new Compass(Heading.E);
        testMap = new MapMaker(Heading.E, testCompass);
    }

    @Test
    public void shouldPut() {
        testMap.put(Heading.E, new JSONObject().put("testkey", "testvalue"));
        assertTrue(testMap.map.containsKey(Heading.E));
        JSONObject testMapValue = testMap.map.get(Heading.E);
        assertEquals(testMapValue.get("testkey"), "testvalue");
    }

    @Test
    public void shouldBeStuck() {
        testMap.map_compass.addVisitedLocation(new Location(1, 0));
        testMap.map_compass.addVisitedLocation(new Location(1, -1));

        testMap.put(Heading.E, new JSONObject().put("testkey", "testvalue"));
        testMap.put(Heading.S, new JSONObject().put("testkey", "testvalue"));

        assertTrue(testMap.is_stuck());
    }

    @Test
    public void shouldChooseGround() throws Exception {

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
        testMap.reset();
        assertEquals(testMap.size(), 0);
    }

    @Test
    public void shouldGetSize() {
        testMap.put(Heading.E, new JSONObject().put("testkey", "testvalue"));
        assertEquals(testMap.size(), 1);
    }

}
