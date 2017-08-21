package yhh.hackernews.feeds;

import android.net.Uri;

import java.util.List;

/**
 * Created by yhh.
 */

public abstract class Feed {

    // json parser & database columns
    public static final String DATA_TIME = "time";

    /**
     * @see Feed#DATA_TYPE_COMMENT
     * @see Feed#DATA_TYPE_STORY
     */
    public static final String DATA_TYPE = "type";
    public static final String DATA_ID = "id";
    public static final String DATA_KIDS = "kids";
    public static final String DATA_BY = "by";

    public static String TABLE_NAME() {
        throw new RuntimeException("TABLE_NAME must be implemented");
    }

    public static Uri PROVIDER_URI() {
        throw new RuntimeException("PROVIDER_URI must be implemented");
    }

    public static final String DATA_TYPE_COMMENT = "comment";
    public static final String DATA_TYPE_STORY = "story";

    String mBy;

    long mId;

    long mTime;

    String mType;

    List<Integer> mKids;

    public Feed() {

    }
}
