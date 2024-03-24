package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONArray;
import org.json.JSONObject;


public class ScanParser implements ScanParsing {
//Parses scanning results from JSON objects to extract specific information.    
    @Override
    public JSONArray getBiomes(JSONObject extraInfo) {
    // (JSONObject) -> JSONArray
    // Extracts and returns the array of biomes from the given JSON object containing scan information.    
        return extraInfo.getJSONArray("biomes");
    }
    @Override
    public JSONArray getCreeks(JSONObject extraInfo) {
    // (JSONObject) -> JSONArray
    // Extracts and returns the array of creek IDs from the given JSON object containing scan information.   
        return extraInfo.getJSONArray("creeks");
    }
    @Override
    public JSONArray getSites(JSONObject extraInfo) {
    // (JSONObject) -> JSONArray
    // Extracts and returns the array of site IDs from the given JSON object containing scan information.    
        return extraInfo.getJSONArray("sites");
    }

}