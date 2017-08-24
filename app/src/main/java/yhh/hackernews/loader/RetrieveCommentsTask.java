package yhh.hackernews.loader;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import yhh.hackernews.feed.Comment;
import yhh.hackernews.utils.Utilities;

/**
 * Created by yhh
 */

public class RetrieveCommentsTask extends AsyncTask<Void, Void, Comment> {
    private static final String TAG = "RetrieveCommentsTask";
    private static final boolean DEBUG = Utilities.DEBUG;

    public interface Callback {
        void onLoadComment(Comment comment, long parentId, CommentLoader.Callback callback);
    }

    private final WeakReference<Callback> mCallback;
    private final WeakReference<CommentLoader.Callback> mTargetCallback;
    private final long mCommentId;
    private final long mParentId;

    protected RetrieveCommentsTask(Callback cb, CommentLoader.Callback targetCallback, long commentId, long parentId) {
        mCallback = new WeakReference<>(cb);
        mTargetCallback = new WeakReference<>(targetCallback);
        mCommentId = commentId;
        mParentId = parentId;
    }

    @Override
    protected Comment doInBackground(Void... params) {
        final String rawData = Utilities.getStringFromHttp(Utilities.DATA_URL(mCommentId));
        if (rawData == null) {
            if (DEBUG) {
                Log.w(TAG, "task failed");
            }
            return null;
        }
        return new Comment(rawData);
    }

    @Override
    protected void onPostExecute(Comment comment) {
        if (comment == null) return;
        final Callback cb = mCallback.get();
        if (cb == null) return;
        cb.onLoadComment(comment, mParentId, mTargetCallback.get());
    }
}
