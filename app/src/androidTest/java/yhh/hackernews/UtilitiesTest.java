package yhh.hackernews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import yhh.hackernews.utils.Utilities;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UtilitiesTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("yhh.hakernews", appContext.getPackageName());
    }

    @Test
    public void convertTopStoriesToList_isValid() throws Exception {
        final String rawData = "[15061605,15062782,15059929,15062546]";
        List<String> data = Utilities.convertTopStoriesToList(rawData);

        final List<String> expected = new ArrayList<>();
        expected.add("15061605");
        expected.add("15062782");
        expected.add("15059929");
        expected.add("15062546");

        assertEquals(expected, data);
    }

    @Test
    public void convertTopStoriesToList_isInvalid() throws Exception {
        final String rawData = "[15061605,15062782,15059929,15062546";
        try {
            Utilities.convertTopStoriesToList(rawData);
            Assert.fail("should get JSONException here");
        } catch (JSONException exception) {
            // pass
        }
    }

    @Test
    public void STORY_DATA_URL_isValid() throws Exception {
        final String url = Utilities.STORY_DATA_URL("15061605");
        assertEquals("https://hacker-news.firebaseio.com/v0/item/15061605.json", url);
    }
}
