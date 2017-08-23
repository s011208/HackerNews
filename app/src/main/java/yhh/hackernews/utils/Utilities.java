package yhh.hackernews.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import yhh.hackernews.R;
import yhh.hackernews.feed.Feed;

/**
 * Created by yhh.
 */

public class Utilities {
    public static final boolean DEBUG = true;

    private static final String TAG = "Utilities";

    public static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";

    private static final long MINUTE = 60 * 1000;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;

    private Utilities() {
    }

    public static String DATA_URL(long storyID) {
        return "https://hacker-news.firebaseio.com/v0/item/" + storyID + ".json";
    }

    public static List<String> convertTopStoriesToList(String rawData) throws JSONException {
        List<String> rtn = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(rawData);
        for (int i = 0; i < jsonArray.length(); ++i) {
            rtn.add(String.valueOf(jsonArray.getInt(i)));
        }
        return rtn;
    }

    @Nullable
    public static String getStringFromHttp(final String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String tempStr;
                StringBuffer stringBuffer = new StringBuffer();

                while ((tempStr = bufferedReader.readLine()) != null) {
                    stringBuffer.append(tempStr);
                }

                bufferedReader.close();
                inputStream.close();
                return stringBuffer.toString();
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    public static String getTimeDiff(Context context, long now, long time) {
        final long timeDiff = now - time;
        if (timeDiff >= DAY) {
            long day = timeDiff / DAY;
            if (day == 1) {
                return day + " " + context.getString(R.string.day);
            } else {
                return day + " " + context.getString(R.string.days);
            }
        } else if (timeDiff >= HOUR) {
            long hour = timeDiff / HOUR;
            if (hour == 1) {
                return hour + " " + context.getString(R.string.hour);
            } else {
                return hour + " " + context.getString(R.string.hours);
            }
        } else {
            long minute = timeDiff / MINUTE;
            if (minute <= 1) {
                return minute + " " + context.getString(R.string.minute);
            } else {
                return minute + " " + context.getString(R.string.minutes);
            }
        }
    }

    public static void sortFeedListByIdArray(@NonNull List<? extends Feed> feedList, @NonNull final List<Long> ids) {
        // TODO: 2017/8/23 add test case
        Collections.sort(feedList, new Comparator<Feed>() {
            @Override
            public int compare(Feed o1, Feed o2) {
                int index1 = ids.indexOf(o1.getId());
                int index2 = ids.indexOf(o2.getId());
                return index1 < index2 ? -1 : ((index1 == index2) ? 0 : 1);
            }
        });
    }
}
