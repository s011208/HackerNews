package yhh.hackernews.feeds;

import android.net.Uri;

import yhh.hackernews.database.HackerNewsProvider;

/**
 * Created by yhh.
 */

public class Comment extends Feed {

    // json parser & database columns
    public static final String DATA_PARENT = "parent";
    public static final String DATA_TEXT = "text";
    public static final String DATA_DELETED = "deleted";

    private static final String TABLE_NAME = "comment";

    public static String TABLE_NAME() {
        return TABLE_NAME;
    }

    private static final Uri PROVIDER_URI = Uri.parse("content://" + HackerNewsProvider.AUTHORITY + "/" + TABLE_NAME);

    public static Uri PROVIDER_URI() {
        return PROVIDER_URI;
    }

    private long mParent;
    private String mText;
    private boolean mDeleted;
}
