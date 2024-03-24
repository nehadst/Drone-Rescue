package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScanParserTest {
    // Test class for ScanParser actions.

    ScanParser testParser;
    JSONObject testJSONExtras;

    @BeforeEach
    public void initJSON() {
        // () -> void
        //This method sets up the test environment before each test by initializing a ScanParser and a JSONObject with biomes, creeks, and sites.
        testParser = new ScanParser();
        testJSONExtras = new JSONObject()
            .put("biomes", new JSONArray(List.of("GLACIER", "ALPINE")))
            .put("creeks", new JSONArray(List.of("creekid")))
            .put("sites", new JSONArray(List.of("ERid")));
    }

    @Test
    public void shouldGetBiomes() {
        //() -> void
        //This test verifies that the getBiomes method correctly returns the biomes from the JSONObject.
        assertEquals(testParser.getBiomes(testJSONExtras).toString(), new JSONArray(List.of("GLACIER", "ALPINE")).toString());
    }

    @Test
    public void shouldGetCreeks() {
        //() -> void
        //This test verifies that the getCreeks method correctly returns the creeks from the JSONObject.
        assertEquals(testParser.getCreeks(testJSONExtras).toString(), new JSONArray(List.of("creekid")).toString());
    }

    @Test
    public void shouldGetSites() {
        //() -> void
        //This test verifies that the getSites method correctly returns the sites from the JSONObject.
        assertEquals(testParser.getSites(testJSONExtras).toString(), new JSONArray(List.of("ERid")).toString());
    }



}