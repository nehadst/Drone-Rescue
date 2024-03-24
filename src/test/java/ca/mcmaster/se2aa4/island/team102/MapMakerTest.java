package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;

public class MapMakerTest {
    private MapMaker testMap;

    @BeforeEach
    public void initMap() {
        Compass testCompass = new Compass(Heading.E);
        testMap = new MapMaker(Heading.E, testCompass);
    }

    @Test
    public void shouldPut() {
        Compass testCompass = new Compass(Heading.E);
        MapMaker testMap = new MapMaker(Heading.E, testCompass);

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

        assertTrue(testMap.isStuck());
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
