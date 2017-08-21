package yhh.hackernews.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import yhh.hackernews.feeds.Comment;
import yhh.hackernews.feeds.Story;

/**
 * Created by yhh.
 */

public class HackerNewsProvider extends ContentProvider {

    private static final UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final String AUTHORITY = "yhh.hackernews.database.hackernews";

    private static final int TABLE_STORY = 1000;
    private static final int TABLE_COMMENT = 2000;

    static {
        sMatcher.addURI(AUTHORITY, Story.TABLE_NAME(), TABLE_STORY);
        sMatcher.addURI(AUTHORITY, Comment.TABLE_NAME(), TABLE_COMMENT);
    }

    private SQLiteDatabase mDatabase;

    @Override
    public boolean onCreate() {
        mDatabase = new Database(getContext()).getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor rtn;
        switch (sMatcher.match(uri)) {
            case TABLE_STORY:
                rtn = mDatabase.query(Story.TABLE_NAME(), projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case TABLE_COMMENT:
                rtn = mDatabase.query(Comment.TABLE_NAME(), projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return rtn;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri rtn;
        switch (sMatcher.match(uri)) {
            case TABLE_STORY:
                rtn = ContentUris.withAppendedId(uri, mDatabase.insert(Story.TABLE_NAME(), null, values));
                break;
            case TABLE_COMMENT:
                rtn = ContentUris.withAppendedId(uri, mDatabase.insert(Comment.TABLE_NAME(), null, values));
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return rtn;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rtn;
        switch (sMatcher.match(uri)) {
            case TABLE_STORY:
                rtn = mDatabase.delete(Story.TABLE_NAME(), selection, selectionArgs);
                break;
            case TABLE_COMMENT:
                rtn = mDatabase.delete(Comment.TABLE_NAME(), selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return rtn;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int rtn;
        switch (sMatcher.match(uri)) {
            case TABLE_STORY:
                rtn = mDatabase.update(Story.TABLE_NAME(), values, selection, selectionArgs);
                break;
            case TABLE_COMMENT:
                rtn = mDatabase.update(Comment.TABLE_NAME(), values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return rtn;
    }
}
