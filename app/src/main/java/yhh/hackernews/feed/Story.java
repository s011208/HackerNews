package yhh.hackernews.feed;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yhh.
 */

public class Story extends Feed {

    // json parser
    private static final String DATA_DESCENDANTS = "descendants";
    private static final String DATA_SCORE = "score";
    private static final String DATA_TITLE = "title";
    private static final String DATA_URL = "url";

    private int mDescendants;
    private int mScore;

    private String mTitle;
    private String mUrl;


    public Story(String rawJSON) {
        super(rawJSON);
        try {
            JSONObject jsonObject = new JSONObject(rawJSON);
            if (jsonObject.has(DATA_DESCENDANTS)) {
                mDescendants = jsonObject.getInt(DATA_DESCENDANTS);
            }
            if (jsonObject.has(DATA_SCORE)) {
                mScore = jsonObject.getInt(DATA_SCORE);
            }
            if (jsonObject.has(DATA_TITLE)) {
                mTitle = jsonObject.getString(DATA_TITLE);
            }
            if (jsonObject.has(DATA_URL)) {
                mUrl = jsonObject.getString(DATA_URL);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Story(ContentValues cv) {
        super(cv.getAsLong(DATA_ID), cv.getAsLong(DATA_TIME), cv.getAsString(DATA_BY), cv.getAsString(DATA_TYPE), cv.getAsString(DATA_KIDS));
        mDescendants = cv.getAsInteger(DATA_DESCENDANTS);
        mScore = cv.getAsInteger(DATA_SCORE);
        mTitle = cv.getAsString(DATA_TITLE);
        mUrl = cv.getAsString(DATA_URL);
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues cv = super.getContentValues();
        cv.put(DATA_DESCENDANTS, mDescendants);
        cv.put(DATA_SCORE, mScore);
        cv.put(DATA_TITLE, mTitle);
        cv.put(DATA_URL, mUrl);
        return cv;
    }

    public int getDescendants() {
        return mDescendants;
    }

    public int getScore() {
        return mScore;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }
}
