package yhh.hackernews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import yhh.hackernews.feed.Story;
import yhh.hackernews.utils.Utilities;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class UtilitiesTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("yhh.hackernews", appContext.getPackageName());
    }

    @Test
    public void convertTopStoriesToList_is_Valid() throws Exception {
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
    public void convertTopStoriesToList_is_Invalid() {
        final String rawData = "[15061605,15062782,15059929,15062546";
        try {
            Utilities.convertTopStoriesToList(rawData);
            Assert.fail("should get JSONException here");
        } catch (JSONException exception) {
            // pass
        }
    }

    @Test
    public void STORY_DATA_URL_is_Valid() {
        final String url = Utilities.DATA_URL(15061605);
        assertEquals("https://hacker-news.firebaseio.com/v0/item/15061605.json", url);
    }

    @Test
    public void getTimeDiff_is_1day() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        final long now = TimeUnit.DAYS.toMillis(1) + 1;
        final long time = 0;
        final String result = Utilities.getTimeDiff(appContext, now, time);
        final String expected = "1 " + appContext.getString(R.string.day);
        assertEquals(expected, result);
    }

    @Test
    public void getTimeDiff_is_2days() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        final long now = TimeUnit.DAYS.toMillis(1) * 2 + 1;
        final long time = 0;
        final String result = Utilities.getTimeDiff(appContext, now, time);
        final String expected = "2 " + appContext.getString(R.string.days);
        assertEquals(expected, result);
    }

    @Test
    public void getTimeDiff_is_1minute() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        final long now = TimeUnit.MINUTES.toMillis(1) + 1;
        final long time = 0;
        final String result = Utilities.getTimeDiff(appContext, now, time);
        final String expected = "1 " + appContext.getString(R.string.minute);
        assertEquals(expected, result);
    }

    @Test
    public void getTimeDiff_is_2minutes() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        final long now = TimeUnit.MINUTES.toMillis(1) * 2 + 1;
        final long time = 0;
        final String result = Utilities.getTimeDiff(appContext, now, time);
        final String expected = "2 " + appContext.getString(R.string.minutes);
        assertEquals(expected, result);
    }

    @Test
    public void getTimeDiff_is_1hour() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        final long now = TimeUnit.HOURS.toMillis(1) + 1;
        final long time = 0;
        final String result = Utilities.getTimeDiff(appContext, now, time);
        final String expected = "1 " + appContext.getString(R.string.hour);
        assertEquals(expected, result);
    }

    @Test
    public void getTimeDiff_is_2hours() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        final long now = TimeUnit.HOURS.toMillis(1) * 2 + 1;
        final long time = 0;
        final String result = Utilities.getTimeDiff(appContext, now, time);
        final String expected = "2 " + appContext.getString(R.string.hours);
        assertEquals(expected, result);
    }

    @Test
    public void sortFeedListByIdArray_is_correct() {
        List<Long> ids = FeedUtils.constructFakeTopStoryList();
        List<Story> testList = FeedUtils.constructFakeStoryList();
        Collections.shuffle(testList);
        Utilities.sortFeedListByIdArray(testList, ids);
        assertEquals(FeedUtils.constructFakeStoryList(), testList);

    }
}
