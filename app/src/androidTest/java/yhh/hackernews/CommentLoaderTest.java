package yhh.hackernews;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;

import yhh.hackernews.feed.Comment;
import yhh.hackernews.loader.CommentLoader;
import yhh.hackernews.loader.RetrieveCommentsTask;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CommentLoaderTest {

    @Before
    public void setUp() {
        CommentLoader commentLoader = new CommentLoaderMock(null);
        ((CommentLoaderMock) commentLoader).clearAllDataSet();
    }

    @After
    public void tearDown() {
        CommentLoader commentLoader = new CommentLoaderMock(null);
        ((CommentLoaderMock) commentLoader).clearAllDataSet();
    }

    @Test
    public void commentData_is_correct() throws InterruptedException {
        CountDownLatch commentCountDown = new CountDownLatch(FeedUtils.constructFakeCommentList().size());
        CommentLoader commentLoader = new CommentLoaderMock(commentCountDown);
        commentLoader.loadComments(FeedUtils.constructFakeStoryList().get(0));
        commentCountDown.await();

        assertEquals(4, commentLoader.getComments(15082810).size());
        assertEquals(0, commentLoader.getComments(15084095).size());
        assertEquals(2, commentLoader.getComments(15083748).size());
        assertEquals(1, commentLoader.getComments(15083199).size());
        assertEquals(1, commentLoader.getComments(15083303).size());
        assertEquals(1, commentLoader.getComments(15083074).size());
    }

    private static class CommentLoaderMock extends CommentLoader {
        private CountDownLatch mCommentCountDown;

        private CommentLoaderMock(CountDownLatch commentCountDown) {
            mCommentCountDown = commentCountDown;
        }

        @Override
        protected RetrieveCommentsTask constructRetrieveCommentsTask(RetrieveCommentsTask.Callback cb, WeakReference<Callback> targetCallback, long commentId, long parentId) {
            return new FakeRetrieveCommentTask(cb, null, commentId, parentId, mCommentCountDown);
        }

        @Override
        public void clearAllDataSet() {
            super.clearAllDataSet();
        }
    }

    private static class FakeRetrieveCommentTask extends RetrieveCommentsTask {
        private CountDownLatch mCountDownLatch;
        private long mCommentId;

        private FakeRetrieveCommentTask(Callback cb, CommentLoader.Callback targetCallback, long commentId, long parentId, CountDownLatch countDownLatch) {
            super(cb, targetCallback, commentId, parentId);
            mCountDownLatch = countDownLatch;
            mCommentId = commentId;
        }

        @Override
        protected Comment doInBackground(Void... voids) {
            for (Comment comment : FeedUtils.constructFakeCommentList()) {
                if (comment.getId() == mCommentId) return comment;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Comment comment) {
            super.onPostExecute(comment);
            mCountDownLatch.countDown();
        }
    }
}
