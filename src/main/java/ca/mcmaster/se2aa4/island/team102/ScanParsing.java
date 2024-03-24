package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ScanParsing {
//This interface defines the methods for parsing scan results to extract specific information from JSON objects.    

    JSONArray getBiomes(JSONObject extraInfo);
    JSONArray getCreeks(JSONObject extraInfo);
    JSONArray getSites(JSONObject extraInfo);

}

