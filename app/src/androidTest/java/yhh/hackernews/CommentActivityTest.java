package yhh.hackernews;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import yhh.hackernews.feed.Comment;
import yhh.hackernews.loader.CommentLoader;
import yhh.hackernews.util.RecyclerViewItemCountAssertion;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CommentActivityTest {

    @Rule
    public ActivityTestRule<CommentActivity> mActivityRule = new ActivityTestRule<CommentActivity>(
            CommentActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            ((HackerNewsApplication) InstrumentationRegistry.getTargetContext().getApplicationContext())
                    .setTestMode(HackerNewsApplication.TEST_MODE_MOCK_COMMENT_LOADER);
        }

        @Override
        protected void afterActivityFinished() {
            super.afterActivityFinished();
            ((HackerNewsApplication) mActivityRule.getActivity().getApplication())
                    .setTestMode(HackerNewsApplication.TEST_MODE_NONE);
        }

        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, CommentActivity.class);
            result.putExtra(CommentActivity.EXTRA_STORY, FeedUtils.constructFakeStoryList().get(0).getContentValues());
            return result;
        }
    };

    private CommentActivity mCommentActivity;

    @Before
    public void setUp() {
        CommentLoader commentLoader = new CommentLoaderTest.CommentLoaderMock(null);
        ((CommentLoaderTest.CommentLoaderMock) commentLoader).clearAllDataSet();
        mCommentActivity = mActivityRule.getActivity();
    }

    @After
    public void tearDown() {
        CommentLoader commentLoader = new CommentLoaderTest.CommentLoaderMock(null);
        ((CommentLoaderTest.CommentLoaderMock) commentLoader).clearAllDataSet();
    }

    @Test
    public void getStory_is_equals() {
        assertEquals(mCommentActivity.getStory(), FeedUtils.constructFakeStoryList().get(0));
    }

    @Test
    public void commentListItemCount_is_equals() {
        final CommentLoader commentLoader = Mockito.mock(CommentLoader.class);
        Map<Long, List<Comment>> commentMap = FeedUtils.constructFakeCommentMap();
        Mockito.when(commentLoader.getComments(15082810L)).thenReturn(commentMap.get(15082810L));
        Mockito.when(commentLoader.getComments(15083682L)).thenReturn(commentMap.get(15083682L));
        Mockito.when(commentLoader.getComments(15083902L)).thenReturn(commentMap.get(15083902L));
        Mockito.when(commentLoader.getComments(15083748L)).thenReturn(commentMap.get(15083748L));
        Mockito.when(commentLoader.getComments(15083199L)).thenReturn(commentMap.get(15083199L));
        Mockito.when(commentLoader.getComments(15083303L)).thenReturn(commentMap.get(15083303L));
        Mockito.when(commentLoader.getComments(15083074L)).thenReturn(commentMap.get(15083074L));

        mCommentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCommentActivity.setCommentLoader(commentLoader);
                mCommentActivity.onBindComments(FeedUtils.constructFakeCommentList().get(0));
            }
        });

        onView(withId(R.id.comment_recyclerview))
                .check(new RecyclerViewItemCountAssertion(FeedUtils.constructFakeCommentList().size()));
    }

    @Test
    public void getComments_is_correct() {
        final CommentLoader commentLoader = Mockito.mock(CommentLoader.class);
        Map<Long, List<Comment>> commentMap = FeedUtils.constructFakeCommentMap();
        Mockito.when(commentLoader.getComments(15082810L)).thenReturn(commentMap.get(15082810L));
        Mockito.when(commentLoader.getComments(15083682L)).thenReturn(commentMap.get(15083682L));
        Mockito.when(commentLoader.getComments(15083902L)).thenReturn(commentMap.get(15083902L));
        Mockito.when(commentLoader.getComments(15083748L)).thenReturn(commentMap.get(15083748L));
        Mockito.when(commentLoader.getComments(15083199L)).thenReturn(commentMap.get(15083199L));
        Mockito.when(commentLoader.getComments(15083303L)).thenReturn(commentMap.get(15083303L));
        Mockito.when(commentLoader.getComments(15083074L)).thenReturn(commentMap.get(15083074L));

        List<Comment> result = CommentActivity.getComments(commentLoader, FeedUtils.constructFakeStoryList().get(0).getId(), 0);
        assertEquals(FeedUtils.constructFakeCommentList(), result);
    }

    @Test
    public void loadingTextWithoutData_is_visible() {
        final CommentLoader commentLoader = Mockito.mock(CommentLoader.class);
        mCommentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCommentActivity.setCommentLoader(commentLoader);
            }
        });

        onView(withId(R.id.hint_textview)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));
    }

    @Test
    public void loadingTextWithData_is_invisible() {
        final CommentLoader commentLoader = Mockito.mock(CommentLoader.class);
        Map<Long, List<Comment>> commentMap = FeedUtils.constructFakeCommentMap();
        Mockito.when(commentLoader.getComments(15082810L)).thenReturn(commentMap.get(15082810L));
        Mockito.when(commentLoader.getComments(15083682L)).thenReturn(commentMap.get(15083682L));
        Mockito.when(commentLoader.getComments(15083902L)).thenReturn(commentMap.get(15083902L));
        Mockito.when(commentLoader.getComments(15083748L)).thenReturn(commentMap.get(15083748L));
        Mockito.when(commentLoader.getComments(15083199L)).thenReturn(commentMap.get(15083199L));
        Mockito.when(commentLoader.getComments(15083303L)).thenReturn(commentMap.get(15083303L));
        Mockito.when(commentLoader.getComments(15083074L)).thenReturn(commentMap.get(15083074L));

        mCommentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCommentActivity.setCommentLoader(commentLoader);
                mCommentActivity.onBindComments(FeedUtils.constructFakeCommentList().get(0));
            }
        });

        onView(withId(R.id.hint_textview)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE))));
    }
}
