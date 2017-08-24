package yhh.hackernews.loader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import yhh.hackernews.feed.Story;

/**
 * Created by yhh
 */

class StoryDataSet {
    private static StoryDataSet sInstance;

    synchronized static StoryDataSet getInstance() {
        if (sInstance == null) {
            sInstance = new StoryDataSet();
        }
        return sInstance;
    }

    private final List<Story> mStoryList = new ArrayList<>();
    private final List<Long> mTopStoriesId = new ArrayList<>();
    private final Queue<RetrieveStoriesTask> mTasksQueue = new LinkedList<>();
    private final List<RetrieveStoriesTask> mRunningTaskList = new ArrayList<>();

    private StoryDataSet() {
    }

    List<Story> getStoryList() {
        return mStoryList;
    }

    List<Long> getTopStoriesId() {
        return mTopStoriesId;
    }

    Queue<RetrieveStoriesTask> getTasksQueue() {
        return mTasksQueue;
    }

    List<RetrieveStoriesTask> getRunningTaskList() {
        return mRunningTaskList;
    }

    void clearAllData() {
        mStoryList.clear();
        mTopStoriesId.clear();
        mTasksQueue.clear();
        mRunningTaskList.clear();
    }
}
