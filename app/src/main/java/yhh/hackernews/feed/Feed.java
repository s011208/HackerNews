package yhh.hackernews.feed;

import android.content.ContentValues;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhh.
 */

public abstract class Feed {

    // json parser
    static final String DATA_TIME = "time";

    static final String DATA_TYPE = "type";
    static final String DATA_ID = "id";
    static final String DATA_KIDS = "kids";
    static final String DATA_BY = "by";

    private String mBy;

    private long mId;

    private long mTime;

    private String mType;

    private List<Long> mKids = new ArrayList<>();

    Feed(String rawJSON) {
        try {
            JSONObject jsonObject = new JSONObject(rawJSON);
            if (jsonObject.has(DATA_BY)) {
                mBy = jsonObject.getString(DATA_BY);
            }
            if (jsonObject.has(DATA_ID)) {
                mId = jsonObject.getLong(DATA_ID);
            }
            if (jsonObject.has(DATA_TIME)) {
                mTime = jsonObject.getLong(DATA_TIME);
            }
            if (jsonObject.has(DATA_TYPE)) {
                mType = jsonObject.getString(DATA_TYPE);
            }
            if (jsonObject.has(DATA_KIDS)) {
                JSONArray jsonArray = jsonObject.getJSONArray(DATA_KIDS);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    mKids.add(jsonArray.getLong(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    Feed(long id, long time, String by, String type, String kids) {
        mId = id;
        mTime = time;
        mBy = by;
        mType = type;
        if (!TextUtils.isEmpty(kids)) {
            try {
                JSONArray jsonArray = new JSONArray(kids);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    mKids.add(jsonArray.getLong(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DATA_BY, mBy);
        cv.put(DATA_ID, mId);
        cv.put(DATA_TIME, mTime);
        cv.put(DATA_TYPE, mType);
        JSONArray jsonArray = new JSONArray();
        for (Long kid : mKids) {
            jsonArray.put(kid);
        }
        cv.put(DATA_KIDS, jsonArray.toString());
        return cv;
    }

    public String getBy() {
        return mBy;
    }

    public String getType() {
        return mType;
    }

    public long getId() {
        return mId;
    }

    public long getTime() {
        return mTime;
    }

    public List<Long> getKids() {
        return new ArrayList<>(mKids);
    }

    @Override
    public String toString() {
        ContentValues cv = getContentValues();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (String key : cv.keySet()) {
            sb.append(key).append(": ").append(cv.get(key)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Feed) &&
                getId() == ((Feed) obj).getId();
    }
}
