package yhh.hackernews.loader;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import yhh.hackernews.feed.Story;
import yhh.hackernews.utils.Utilities;

/**
 * Created by yhh
 */

public class StoryLoader implements RetrieveTopStoriesTask.Callback, RetrieveStoriesTask.Callback {
    private static final boolean DEBUG = Utilities.DEBUG;
    private static final String TAG = "StoryLoader";

    private static final int TASK_QUEUE_SIZE = 6;

    public interface Callback {
        void onStoryLoad();

        void onTopStoryListLoadFail();
    }

    private WeakReference<Callback> mCallback;
    private final StoryDataSet mStoryDataSet;

    public StoryLoader() {
        mStoryDataSet = StoryDataSet.getInstance();
    }

    public void setCallback(Callback cb) {
        mCallback = new WeakReference<>(cb);
    }

    public List<Story> getStoryList() {
        return new ArrayList<>(mStoryDataSet.getStoryList());
    }

    public List<Long> getTopStoriesIdList() {
        return new ArrayList<>(mStoryDataSet.getTopStoriesId());
    }

    protected RetrieveTopStoriesTask constructTopStoriesTask(RetrieveTopStoriesTask.Callback cb) {
        return new RetrieveTopStoriesTask(cb);
    }

    protected RetrieveStoriesTask constructRetrieveStoriesTask(RetrieveStoriesTask.Callback cb, long storyId) {
        return new RetrieveStoriesTask(cb, storyId);
    }

    public void loadStory() {
        if (DEBUG) {
            Log.v(TAG, "start to loadStory");
        }
        mStoryDataSet.getTasksQueue().clear();
        mStoryDataSet.getRunningTaskList().clear();
        constructTopStoriesTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTopStoriesLoadFinish(List<Long> storyIds) {
        mStoryDataSet.getTopStoriesId().clear();
        mStoryDataSet.getTopStoriesId().addAll(storyIds);
        for (Long storyId : storyIds) {
            mStoryDataSet.getTasksQueue().add(constructRetrieveStoriesTask(this, storyId));
        }
        for (int i = 0; i < TASK_QUEUE_SIZE && !mStoryDataSet.getTasksQueue().isEmpty(); ++i) {
            RetrieveStoriesTask task = mStoryDataSet.getTasksQueue().poll();
            mStoryDataSet.getRunningTaskList().add(task);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onTopStoriesLoadFailed() {
        if (mCallback == null) return;
        final Callback cb = mCallback.get();
        if (cb == null) return;
        cb.onTopStoryListLoadFail();
    }

    @Override
    public void onStoryLoad(RetrieveStoriesTask task, Story story) {
        int indexOfStory = -1;
        for (int i = 0; i < mStoryDataSet.getStoryList().size(); ++i) {
            final Story item = mStoryDataSet.getStoryList().get(i);
            if (item.getId() == story.getId()) {
                indexOfStory = i;
                break;
            }
        }
        if (indexOfStory == -1) {
            mStoryDataSet.getStoryList().add(story);
        } else {
            mStoryDataSet.getStoryList().set(indexOfStory, story);
        }

        Utilities.sortFeedListByIdArray(mStoryDataSet.getStoryList(), mStoryDataSet.getTopStoriesId());

        onStoryLoadFinish(task);
    }

    @Override
    public void onStoryLoadFailed(RetrieveStoriesTask task) {
        onStoryLoadFinish(task);
    }

    private void onStoryLoadFinish(RetrieveStoriesTask task) {
        mStoryDataSet.getRunningTaskList().remove(task);
        if (mStoryDataSet.getRunningTaskList().isEmpty()) {
            int index = 0;
            for (Story story : mStoryDataSet.getStoryList()) {
                story.setDisplayIndex(index++);
            }

            if (!mStoryDataSet.getTasksQueue().isEmpty()) {
                for (int i = 0; i < TASK_QUEUE_SIZE && !mStoryDataSet.getTasksQueue().isEmpty(); ++i) {
                    RetrieveStoriesTask retrieveStoriesTask = mStoryDataSet.getTasksQueue().poll();
                    mStoryDataSet.getRunningTaskList().add(retrieveStoriesTask);
                    retrieveStoriesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }

            if (mCallback == null) return;
            final Callback cb = mCallback.get();
            if (cb == null) return;
            cb.onStoryLoad();
        }
    }

    public boolean isLoadingStories() {
        return !mStoryDataSet.getTasksQueue().isEmpty() || !mStoryDataSet.getRunningTaskList().isEmpty();
    }

    protected void clearAllDataSet() {
        mStoryDataSet.clearAllData();
    }
}
