package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ScanParsing {
//This interface defines the methods for parsing scan results to extract specific information from JSON objects.    

    public JSONArray get_biomes(JSONObject extraInfo);
    public JSONArray get_creeks(JSONObject extraInfo);
    public JSONArray get_sites(JSONObject extraInfo);

}

