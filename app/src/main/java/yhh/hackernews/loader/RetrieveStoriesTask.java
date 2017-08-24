package yhh.hackernews.loader;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import yhh.hackernews.feed.Story;
import yhh.hackernews.utils.Utilities;

/**
 * Created by yhh
 */
public class RetrieveStoriesTask extends AsyncTask<Void, Void, Story> {

    public interface Callback {
        void onStoryLoad(RetrieveStoriesTask task, Story story);

        void onStoryLoadFailed(RetrieveStoriesTask task);
    }

    private final WeakReference<Callback> mCallback;
    private final long mStoryId;

    protected RetrieveStoriesTask(Callback cb, long storyId) {
        mCallback = new WeakReference<>(cb);
        mStoryId = storyId;
    }

    @Override
    protected Story doInBackground(Void... params) {
        if (isCancelled()) return null;
        final String storyRawData = Utilities.getStringFromHttp(Utilities.DATA_URL(mStoryId));
        if (storyRawData == null) return null;
        return new Story(storyRawData);
    }

    @Override
    protected void onPostExecute(Story story) {
        final Callback cb = mCallback.get();
        if (cb == null) return;
        if (story == null) cb.onStoryLoadFailed(this);
        else cb.onStoryLoad(this, story);
    }
}
