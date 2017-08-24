package yhh.hackernews.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yhh.hackernews.feed.Comment;

/**
 * Created by yhh
 */
class CommentDataSet {
    private static CommentDataSet sInstance;

    static synchronized CommentDataSet getInstance() {
        if (sInstance == null) {
            sInstance = new CommentDataSet();
        }
        return sInstance;
    }

    private final Map<Long, List<Long>> mKidsMap = new HashMap<>();

    private final Map<Long, List<Comment>> mCommentMap = new HashMap<>();

    private CommentDataSet() {
    }

    Map<Long, List<Long>> getKidsMap() {
        return mKidsMap;
    }

    Map<Long, List<Comment>> getCommentMap() {
        return mCommentMap;
    }

    void clearAllData() {
        mKidsMap.clear();
        mCommentMap.clear();
    }
}
