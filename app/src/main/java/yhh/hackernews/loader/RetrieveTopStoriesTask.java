package yhh.hackernews.loader;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import yhh.hackernews.utils.Utilities;

/**
 * Created by yhh
 */
public class RetrieveTopStoriesTask extends AsyncTask<Void, Void, List<Long>> {
    private static final boolean DEBUG = Utilities.DEBUG;
    private static final String TAG = "RetrieveTopStoriesTask";

    public interface Callback {
        void onTopStoriesLoadFinish(List<Long> storyIds);

        void onTopStoriesLoadFailed();
    }

    private final WeakReference<Callback> mCallback;

    protected RetrieveTopStoriesTask(Callback cb) {
        mCallback = new WeakReference<>(cb);
    }

    @Override
    protected List<Long> doInBackground(Void... voids) {
        long startTime = System.currentTimeMillis();
        if (isCancelled()) return null;
        final String rawData = Utilities.getStringFromHttp(Utilities.TOP_STORIES_URL);
        if (rawData == null) {
            if (DEBUG) {
                Log.w(TAG, "failed to retrieve top stories");
            }
            return null;
        }


        final List<Long> storiesId = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(rawData);
            for (int i = 0; i < jsonArray.length(); ++i) {
                storiesId.add(jsonArray.getLong(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (DEBUG) {
            Log.i(TAG, "RetrieveTopStoriesTask takes: " + (System.currentTimeMillis() - startTime) + " ms");
        }

        return storiesId;
    }

    @Override
    protected void onPostExecute(List<Long> storyIds) {
        final Callback cb = mCallback.get();
        if (cb == null) {
            if (DEBUG) {
                Log.w(TAG, "callback in null");
            }
            return;
        }
        if (storyIds == null) cb.onTopStoriesLoadFailed();
        else cb.onTopStoriesLoadFinish(storyIds);
    }
}
