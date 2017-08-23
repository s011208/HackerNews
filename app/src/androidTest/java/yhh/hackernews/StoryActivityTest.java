package yhh.hackernews;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StoryActivityTest {

    @Rule
    public ActivityTestRule<StoryActivity> mActivityRule = new ActivityTestRule<StoryActivity>(
            StoryActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
        }
    };

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }
}
