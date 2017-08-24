package yhh.hackernews.loader;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import yhh.hackernews.feed.Comment;
import yhh.hackernews.feed.Story;
import yhh.hackernews.utils.Utilities;

/**
 * Created by yhh
 */

public class CommentLoader implements RetrieveCommentsTask.Callback {

    public interface Callback {
        void onBindComments(Comment comment);
    }

    private WeakReference<Callback> mCallback;

    private CommentDataSet mCommentDataSet;

    public CommentLoader() {
        mCommentDataSet = CommentDataSet.getInstance();
    }

    @Override
    public void onLoadComment(Comment comment, long parentId, Callback callback) {
        List<Comment> commentList = mCommentDataSet.getCommentMap().get(parentId);
        if (commentList == null) {
            commentList = new ArrayList<>();
            mCommentDataSet.getCommentMap().put(parentId, commentList);
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

        List<Long> ids = mCommentDataSet.getKidsMap().get(parentId);
        if (ids == null) {
            ids = new ArrayList<>();
            mCommentDataSet.getKidsMap().put(parentId, ids);
        }
        Utilities.sortFeedListByIdArray(commentList, ids);

        if (comment.getKids() != null && !comment.getKids().isEmpty()) {
            // load comment kids
            setKids(comment.getId(), comment.getKids());
            loadComments(comment);
        }

        if (mCallback == null) return;
        Callback cb = mCallback.get();
        if (cb == null) return;
        if (cb != callback) return;
        cb.onBindComments(comment);
    }

    @NonNull
    public List<Comment> getComments(long parentId) {
        List<Comment> rtn = mCommentDataSet.getCommentMap().get(parentId);
        if (rtn == null) return new ArrayList<>();
        else return rtn;
    }

    public void setCallback(Callback cb) {
        mCallback = new WeakReference<>(cb);
    }

    public void setKids(long parentId, List<Long> ids) {
        mCommentDataSet.getKidsMap().put(parentId, ids);
    }

    protected RetrieveCommentsTask constructRetrieveCommentsTask(RetrieveCommentsTask.Callback cb, WeakReference<CommentLoader.Callback> targetCallback, long commentId, long parentId) {
        return new RetrieveCommentsTask(cb, targetCallback.get(), commentId, parentId);
    }

    public void loadComments(Story story) {
        if (story.getKids() == null) return;
        for (Long commentId : story.getKids()) {
            constructRetrieveCommentsTask(this, mCallback, commentId, story.getId()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private void loadComments(Comment comment) {
        if (comment.getKids() == null) return;
        for (Long commentId : comment.getKids()) {
            constructRetrieveCommentsTask(this, mCallback, commentId, comment.getId()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    protected void clearAllDataSet() {
        mCommentDataSet.clearAllData();
    }

    public Map<Long, List<Comment>> getData() {
        return mCommentDataSet.getCommentMap();
    }
}
