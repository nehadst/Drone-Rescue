package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ScanParsing {

    public JSONArray get_biomes(JSONObject extraInfo);
    public JSONArray get_creeks(JSONObject extraInfo);
    public JSONArray get_sites(JSONObject extraInfo);

}

