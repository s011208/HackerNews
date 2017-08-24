package yhh.hackernews;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import yhh.hackernews.feed.Story;
import yhh.hackernews.loader.RetrieveStoriesTask;
import yhh.hackernews.loader.RetrieveTopStoriesTask;
import yhh.hackernews.loader.StoryLoader;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class StoryLoaderTest {

    @Before
    public void setUp() {
        StoryLoader storyLoader = new StoryLoaderMock(null, null);
        ((StoryLoaderMock) storyLoader).clearAllDataSet();
    }

    @After
    public void tearDown() {
        StoryLoader storyLoader = new StoryLoaderMock(null, null);
        ((StoryLoaderMock) storyLoader).clearAllDataSet();
    }

    @Test
    public void topStories_data_correct() throws InterruptedException {
        CountDownLatch topStoryCountDown = new CountDownLatch(1);
        CountDownLatch storiesCountDown = new CountDownLatch(FeedUtils.constructFakeStoryList().size());
        StoryLoader storyLoader = new StoryLoaderMock(topStoryCountDown, storiesCountDown);
        storyLoader.loadStory();
        topStoryCountDown.await();
        storiesCountDown.await();
        assertEquals(FeedUtils.constructFakeTopStoryList(), storyLoader.getTopStoriesIdList());
    }

    @Test
    public void stories_data_correct() throws InterruptedException {
        CountDownLatch topStoryCountDown = new CountDownLatch(1);
        CountDownLatch storiesCountDown = new CountDownLatch(FeedUtils.constructFakeStoryList().size());
        StoryLoader storyLoader = new StoryLoaderMock(topStoryCountDown, storiesCountDown);
        storyLoader.loadStory();
        topStoryCountDown.await();
        storiesCountDown.await();
        assertEquals(FeedUtils.constructFakeStoryList(), storyLoader.getStoryList());
    }

    private static class StoryLoaderMock extends StoryLoader {
        private CountDownLatch mTopStoryCountDown;
        private CountDownLatch mStoriesCountDown;

        private StoryLoaderMock(CountDownLatch topStoryCountDown, CountDownLatch storyCountDown) {
            mTopStoryCountDown = topStoryCountDown;
            mStoriesCountDown = storyCountDown;
        }

        @Override
        protected RetrieveTopStoriesTask constructTopStoriesTask(RetrieveTopStoriesTask.Callback cb) {
            return new FakeRetrieveTopStoriesTask(cb, mTopStoryCountDown);
        }

        @Override
        protected RetrieveStoriesTask constructRetrieveStoriesTask(RetrieveStoriesTask.Callback cb, long storyId) {
            return new FakeRetrieveStoriesTask(cb, storyId, mStoriesCountDown);
        }

        @Override
        public void clearAllDataSet() {
            super.clearAllDataSet();
        }
    }

    private static class FakeRetrieveTopStoriesTask extends RetrieveTopStoriesTask {
        private CountDownLatch mCountDownLatch;

        private FakeRetrieveTopStoriesTask(RetrieveTopStoriesTask.Callback cb, CountDownLatch countDownLatch) {
            super(cb);
            mCountDownLatch = countDownLatch;
        }

        @Override
        protected List<Long> doInBackground(Void... voids) {
            return FeedUtils.constructFakeTopStoryList();
        }

        @Override
        protected void onPostExecute(List<Long> storyIds) {
            super.onPostExecute(storyIds);
            mCountDownLatch.countDown();
        }
    }

    private static class FakeRetrieveStoriesTask extends RetrieveStoriesTask {
        private CountDownLatch mCountDownLatch;
        private long mStoryId;

        private FakeRetrieveStoriesTask(RetrieveStoriesTask.Callback cb, long storyId, CountDownLatch countDownLatch) {
            super(cb, storyId);
            mStoryId = storyId;
            mCountDownLatch =countDownLatch;
        }

        @Override
        protected Story doInBackground(Void... params) {
            for (Story story : FeedUtils.constructFakeStoryList()) {
                if (story.getId() == mStoryId) return story;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Story story) {
            super.onPostExecute(story);
            mCountDownLatch.countDown();
        }
    }
}
