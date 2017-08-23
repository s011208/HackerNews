package yhh.hackernews.feed;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yhh.
 */

public class Comment extends Feed {

    // json parser
    private static final String DATA_PARENT = "parent";
    private static final String DATA_TEXT = "text";
    private static final String DATA_DELETED = "deleted";

    private long mParent;
    private String mText;
    private boolean mDeleted;
    private int mLevel = 0;


    public Comment(String rawJSON) {
        super(rawJSON);
        try {
            JSONObject jsonObject = new JSONObject(rawJSON);
            if (jsonObject.has(DATA_PARENT)) {
                mParent = jsonObject.getLong(DATA_PARENT);
            }
            if (jsonObject.has(DATA_TEXT)) {
                mText = jsonObject.getString(DATA_TEXT);
            }
            if (jsonObject.has(DATA_DELETED)) {
                mDeleted = jsonObject.getBoolean(DATA_DELETED);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues cv = super.getContentValues();
        cv.put(DATA_PARENT, mParent);
        cv.put(DATA_TEXT, mText);
        cv.put(DATA_DELETED, mDeleted);
        return cv;
    }

    public long getParent() {
        return mParent;
    }

    public String getText() {
        return mText;
    }

    public boolean isDeleted() {
        return mDeleted;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public int getLevel() {
        return mLevel;
    }
}
