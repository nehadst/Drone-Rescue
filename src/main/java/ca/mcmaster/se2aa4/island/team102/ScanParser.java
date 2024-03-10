package ca.mcmaster.se2aa4.island.team102;

import org.json.JSONArray;
import org.json.JSONObject;


public class ScanParser implements ScanParsing {
    @Override
    public JSONArray get_biomes(JSONObject extraInfo) {
        JSONArray biomes = extraInfo.getJSONArray("biomes");
        return biomes;
    }
    @Override
    public JSONArray get_creeks(JSONObject extraInfo) {
        JSONArray creeks = extraInfo.getJSONArray("creeks");
        return creeks;
    }
    @Override
    public JSONArray get_sites(JSONObject extraInfo) {
        JSONArray sites = extraInfo.getJSONArray("sites");
        return sites;
    }

}