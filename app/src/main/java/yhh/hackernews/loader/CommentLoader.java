package yhh.hackernews.loader;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yhh.hackernews.feed.Comment;
import yhh.hackernews.feed.Story;
import yhh.hackernews.utils.Utilities;

/**
 * Created by yhh
 */

public class CommentLoader implements RetrieveCommentsTask.Callback {
    private static CommentLoader sInstance;

    public static synchronized CommentLoader getInstance() {
        if (sInstance == null) {
            sInstance = new CommentLoader();
        }
        return sInstance;
    }

    @Override
    public void onLoadComment(Comment comment, long parentId, Callback callback) {
        Callback cb = mCallback.get();
        if (cb == null) return;
        if (cb != callback) return;
        List<Comment> commentList = mCommentMap.get(parentId);
        if (commentList == null) {
            commentList = new ArrayList<>();
            mCommentMap.put(parentId, commentList);
        }

        // update cache
        int indexOfComment = -1;
        for (int i = 0; i < commentList.size(); ++i) {
            final Comment item = commentList.get(i);
            if (item.getId() == comment.getId()) {
                indexOfComment = i;
                break;
            }
        }
        if (indexOfComment == -1) {
            commentList.add(comment);
        } else {
            commentList.set(indexOfComment, comment);
        }

        List<Long> ids = mKidsMap.get(parentId);
        if (ids == null) {
            ids = new ArrayList<>();
            mKidsMap.put(parentId, ids);
        }
        Utilities.sortFeedListByIdArray(commentList, ids);

        cb.onBindComments(comment);

        if (comment.getKids() != null && !comment.getKids().isEmpty()) {
            // load comment kids
            setKids(comment.getId(), comment.getKids());
            loadComments(comment);
        }
    }

    public interface Callback {
        void onBindComments(Comment comment);
    }

    private WeakReference<Callback> mCallback;

    private final Map<Long, List<Long>> mKidsMap = new HashMap<>();

    private final Map<Long, List<Comment>> mCommentMap = new HashMap<>();

    private CommentLoader() {
    }

    @NonNull
    public List<Comment> getComments(long parentId) {
        List<Comment> rtn = mCommentMap.get(parentId);
        if (rtn == null) return new ArrayList<>();
        else return rtn;
    }

    public void setCallback(Callback cb) {
        mCallback = new WeakReference<>(cb);
    }

    public void setKids(long parentId, List<Long> ids) {
        mKidsMap.put(parentId, ids);
    }

    public void loadComments(Story story) {
        if (story.getKids() == null) return;
        for (Long commentId : story.getKids()) {
            new RetrieveCommentsTask(this, mCallback.get(), commentId, story.getId()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    public void loadComments(Comment comment) {
        if (comment.getKids() == null) return;
        for (Long commentId : comment.getKids()) {
            new RetrieveCommentsTask(this, mCallback.get(), commentId, comment.getId()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
}
