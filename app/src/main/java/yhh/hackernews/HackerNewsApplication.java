package yhh.hackernews;

import android.app.Application;

/**
 * Created by yhh
 */

public class HackerNewsApplication extends Application {

    public static final int TEST_MODE_NONE = 0;
    public static final int TEST_MODE_MOCK_STORY_LOADER = 1;
    public static final int TEST_MODE_MOCK_COMMENT_LOADER = 2;

    private int mTestMode = TEST_MODE_NONE;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setTestMode(int testMode) {
        mTestMode = testMode;
    }

    public int getTestMode() {
        return mTestMode;
    }
}
