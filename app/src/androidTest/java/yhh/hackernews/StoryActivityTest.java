package yhh.hackernews;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ProgressBar;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import yhh.hackernews.feed.Story;
import yhh.hackernews.loader.StoryLoader;
import yhh.hackernews.util.RecyclerViewItemCountAssertion;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class StoryActivityTest {

    @Rule
    public ActivityTestRule<StoryActivity> mActivityRule = new ActivityTestRule<StoryActivity>(
            StoryActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            ((HackerNewsApplication) InstrumentationRegistry.getTargetContext().getApplicationContext())
                    .setTestMode(HackerNewsApplication.TEST_MODE_MOCK_STORY_LOADER);
        }

        @Override
        protected void afterActivityFinished() {
            super.afterActivityFinished();
            ((HackerNewsApplication) mActivityRule.getActivity().getApplication())
                    .setTestMode(HackerNewsApplication.TEST_MODE_NONE);
        }
    };

    @Before
    public void setUp() {
        Intents.init();
        ((ProgressBar) mActivityRule.getActivity().findViewById(R.id.loading_progressbar)).setIndeterminate(false);
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void storiesListItemCount_is_correct() {
        final StoryLoader storyLoader = Mockito.mock(StoryLoader.class);
        Mockito.when(storyLoader.getStoryList()).thenReturn(FeedUtils.constructFakeStoryList());
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().setStoryLoader(storyLoader);
                mActivityRule.getActivity().onStoryLoad();
            }
        });
        onView(withId(R.id.top_stories_recycler))
                .check(new RecyclerViewItemCountAssertion(FeedUtils.constructFakeStoryList().size()));
    }

    @Test
    public void progressBarWhenLoadingDone_is_invisible() {
        final StoryLoader storyLoader = Mockito.mock(StoryLoader.class);
        Mockito.when(storyLoader.getStoryList()).thenReturn(FeedUtils.constructFakeStoryList());
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().setStoryLoader(storyLoader);
                mActivityRule.getActivity().onStoryLoad();
            }
        });
        onView(withId(R.id.loading_progressbar)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE))));
    }

    @Test
    public void progressBarWhenLoading_is_visible() {
        final StoryLoader storyLoader = Mockito.mock(StoryLoader.class);
        Mockito.when(storyLoader.getStoryList()).thenReturn(FeedUtils.constructFakeStoryList());
        Mockito.when(storyLoader.isLoadingStories()).thenReturn(true);
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().setStoryLoader(storyLoader);
                mActivityRule.getActivity().onStoryLoad();
            }
        });
        onView(withId(R.id.loading_progressbar)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));
    }

    @Test
    public void loadingFailTextWhenLoadDone_is_invisible() {
        final StoryLoader storyLoader = Mockito.mock(StoryLoader.class);
        Mockito.when(storyLoader.getStoryList()).thenReturn(FeedUtils.constructFakeStoryList());
        Mockito.when(storyLoader.isLoadingStories()).thenReturn(true);
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().setStoryLoader(storyLoader);
                mActivityRule.getActivity().onStoryLoad();
            }
        });
        onView(withId(R.id.top_story_list_load_failed_hint)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE))));
    }

    @Test
    public void loadingFailTextWhenTopStoriesLoadFailWithEmptyStories_is_visible() {
        final StoryLoader storyLoader = Mockito.mock(StoryLoader.class);
        Mockito.when(storyLoader.getStoryList()).thenReturn(new ArrayList<Story>());
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().setStoryLoader(storyLoader);
                mActivityRule.getActivity().onTopStoryListLoadFail();
            }
        });
        onView(withId(R.id.top_story_list_load_failed_hint)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));
    }

    @Test
    public void loadingFailTextWhenTopStoriesLoadFailWithStories_is_invisible() {
        final StoryLoader storyLoader = Mockito.mock(StoryLoader.class);
        Mockito.when(storyLoader.getStoryList()).thenReturn(FeedUtils.constructFakeStoryList());
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().setStoryLoader(storyLoader);
                mActivityRule.getActivity().onTopStoryListLoadFail();
            }
        });
        onView(withId(R.id.top_story_list_load_failed_hint)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE))));
    }

    @Test
    public void clickRecyclerViewStory_start_commentActivity() {
        final StoryLoader storyLoader = Mockito.mock(StoryLoader.class);
        Mockito.when(storyLoader.getStoryList()).thenReturn(FeedUtils.constructFakeStoryList());
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().setStoryLoader(storyLoader);
                mActivityRule.getActivity().onStoryLoad();
            }
        });
        onView(withId(R.id.top_stories_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasComponent(CommentActivity.class.getName()));
    }

    @Test
    public void onItemClick_start_commentActivity() {
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().onItemClick(FeedUtils.constructFakeStoryList().get(0));
            }
        });
        intended(hasComponent(CommentActivity.class.getName()));
    }
}
