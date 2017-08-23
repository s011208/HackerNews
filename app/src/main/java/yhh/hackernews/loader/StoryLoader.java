package yhh.hackernews.loader;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import yhh.hackernews.feed.Story;
import yhh.hackernews.utils.Utilities;

/**
 * Created by yhh
 */

public class StoryLoader implements RetrieveTopStoriesTask.Callback, RetrieveStoriesTask.Callback {
    private static final boolean DEBUG = Utilities.DEBUG;
    private static final String TAG = "StoryLoader";

    private static final int TASK_QUEUE_SIZE = 6;

    private static StoryLoader sInstance;

    public static synchronized StoryLoader getInstance() {
        if (sInstance == null) {
            sInstance = new StoryLoader();
        }
        return sInstance;
    }

    public interface Callback {
        void onStoryLoad();

        void onTopStoryListLoadFail();
    }

    private final List<Story> mStoryList = new ArrayList<>();
    private final List<Long> mTopStoriesId = new ArrayList<>();
    private final Queue<RetrieveStoriesTask> mTasksQueue = new LinkedList<>();
    private final List<RetrieveStoriesTask> mRunningTaskList = new ArrayList<>();
    private WeakReference<Callback> mCallback;

    private StoryLoader() {
    }

    public void setCallback(Callback cb) {
        mCallback = new WeakReference<>(cb);
    }

    public List<Story> getStoryList() {
        return new ArrayList<>(mStoryList);
    }

    public void loadStory() {
        if (DEBUG) {
            Log.v(TAG, "start to loadStory");
        }
        mTasksQueue.clear();
        mRunningTaskList.clear();
        new RetrieveTopStoriesTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTopStoriesLoadFinish(List<Long> storyIds) {
        mTopStoriesId.clear();
        mTopStoriesId.addAll(storyIds);
        for (Long storyId : storyIds) {
            mTasksQueue.add(new RetrieveStoriesTask(this, storyId));
        }
        for (int i = 0; i < TASK_QUEUE_SIZE && !mTasksQueue.isEmpty(); ++i) {
            RetrieveStoriesTask task = mTasksQueue.poll();
            mRunningTaskList.add(task);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onTopStoriesLoadFailed() {
        final Callback cb = mCallback.get();
        if (cb == null) return;
        cb.onTopStoryListLoadFail();
    }

    @Override
    public void onStoryLoad(RetrieveStoriesTask task, Story story) {
        int indexOfStory = -1;
        for (int i = 0; i < mStoryList.size(); ++i) {
            final Story item = mStoryList.get(i);
            if (item.getId() == story.getId()) {
                indexOfStory = i;
                break;
            }
        }
        if (indexOfStory == -1) {
            mStoryList.add(story);
        } else {
            mStoryList.set(indexOfStory, story);
        }

        Utilities.sortFeedListByIdArray(mStoryList, mTopStoriesId);

        onStoryLoadFinish(task);
    }

    @Override
    public void onStoryLoadFailed(RetrieveStoriesTask task) {
        onStoryLoadFinish(task);
    }

    private void onStoryLoadFinish(RetrieveStoriesTask task) {
        mRunningTaskList.remove(task);
        if (mRunningTaskList.isEmpty()) {
            int index = 0;
            for (Story story : mStoryList) {
                story.setDisplayIndex(index++);
            }
            final Callback cb = mCallback.get();
            if (cb == null) return;
            cb.onStoryLoad();

            if (!mTasksQueue.isEmpty()) {
                for (int i = 0; i < TASK_QUEUE_SIZE && !mTasksQueue.isEmpty(); ++i) {
                    RetrieveStoriesTask retrieveStoriesTask = mTasksQueue.poll();
                    mRunningTaskList.add(retrieveStoriesTask);
                    retrieveStoriesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }
        }
    }

    public boolean isLoadingStories() {
        return !mTasksQueue.isEmpty() || !mRunningTaskList.isEmpty();
    }
}
