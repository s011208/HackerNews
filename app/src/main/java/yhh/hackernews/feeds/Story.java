package yhh.hackernews.feeds;

import android.net.Uri;

import yhh.hackernews.database.HackerNewsProvider;

/**
 * Created by yhh.
 */

public class Story extends Feed {

    // json parser & database columns
    public static final String DATA_DESCENDANTS = "descendants";
    public static final String DATA_SCORE = "score";
    public static final String DATA_TITLE = "title";
    public static final String DATA_URL = "url";

    public static final String TABLE_NAME = "story";

    public static String TABLE_NAME() {
        return TABLE_NAME;
    }

    private static final Uri PROVIDER_URI = Uri.parse("content://" + HackerNewsProvider.AUTHORITY + "/" + TABLE_NAME);

    public static Uri PROVIDER_URI() {
        return PROVIDER_URI;
    }

    private int mDescendants;
    private int mScore;

    private String mTitle;
    private String mUrl;
}
