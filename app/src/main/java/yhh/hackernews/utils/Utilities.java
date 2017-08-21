package yhh.hackernews.utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhh.
 */

public class Utilities {
    public static final boolean DEBUG = true;

    private static final String TAG = "Utilities";

    public static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";

    public static String STORY_DATA_URL(String storyID) {
        return "https://hacker-news.firebaseio.com/v0/item/" + storyID + ".json";
    }

    public static List<String> convertTopStoriesToList(String rawData) throws JSONException {
        List<String> rtn = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(rawData);
        for (int i = 0; i < jsonArray.length(); ++i) {
            rtn.add(String.valueOf(jsonArray.getInt(i)));
        }
        return rtn;
    }
}
