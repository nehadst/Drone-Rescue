package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScanParserTest {

    ScanParser testParser;
    JSONObject testJSONExtras;

    @BeforeEach
    public void initJSON() {
        testParser = new ScanParser();
        testJSONExtras = new JSONObject()
            .put("biomes", new JSONArray(List.of("GLACIER", "ALPINE")))
            .put("creeks", new JSONArray(List.of("creekid")))
            .put("sites", new JSONArray(List.of("ERid")));
    }

    @Test
    public void shouldGetBiomes() {
        assertEquals(testParser.getBiomes(testJSONExtras).toString(), new JSONArray(List.of("GLACIER", "ALPINE")).toString());
    }

    @Test
    public void shouldGetCreeks() {
        assertEquals(testParser.getCreeks(testJSONExtras).toString(), new JSONArray(List.of("creekid")).toString());
    }

    @Test
    public void shouldGetSites() {
        assertEquals(testParser.getSites(testJSONExtras).toString(), new JSONArray(List.of("ERid")).toString());
    }



}