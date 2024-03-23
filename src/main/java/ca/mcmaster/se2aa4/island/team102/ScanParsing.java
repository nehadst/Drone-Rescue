package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ScanParsing {
//This interface defines the methods for parsing scan results to extract specific information from JSON objects.    

    public JSONArray getBiomes(JSONObject extraInfo);
    public JSONArray getCreeks(JSONObject extraInfo);
    public JSONArray getSites(JSONObject extraInfo);

}

