package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONArray;
import org.json.JSONObject;


public class ScanParser implements ScanParsing {
//Parses scanning results from JSON objects to extract specific information.    
    @Override
    public JSONArray get_biomes(JSONObject extraInfo) {
    // (JSONObject) -> JSONArray
    // Extracts and returns the array of biomes from the given JSON object containing scan information.    
        JSONArray biomes = extraInfo.getJSONArray("biomes");
        return biomes;
    }
    @Override
    public JSONArray get_creeks(JSONObject extraInfo) {
    // (JSONObject) -> JSONArray
    // Extracts and returns the array of creek IDs from the given JSON object containing scan information.   
        JSONArray creeks = extraInfo.getJSONArray("creeks");
        return creeks;
    }
    @Override
    public JSONArray get_sites(JSONObject extraInfo) {
    // (JSONObject) -> JSONArray
    // Extracts and returns the array of site IDs from the given JSON object containing scan information.    
        JSONArray sites = extraInfo.getJSONArray("sites");
        return sites;
    }

}