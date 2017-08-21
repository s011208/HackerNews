package yhh.hackernews.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import yhh.hackernews.feeds.Comment;
import yhh.hackernews.feeds.Story;

/**
 * Created by yhh.
 */
class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "news.db";
    private static final int DATABASE_VERSION = 1;

    Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void createTableStory(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Story.TABLE_NAME() + " ("
                + Story.DATA_ID + " INTEGER PRIMARY KEY,"
                + Story.DATA_TIME + " INTEGER NOT NULL,"
                + Story.DATA_DESCENDANTS + " INTEGER,"
                + Story.DATA_SCORE + " INTEGER,"
                + Story.DATA_TYPE + " TEXT,"
                + Story.DATA_KIDS + " TEXT,"
                + Story.DATA_BY + " TEXT"
                + Story.DATA_TITLE + " TEXT"
                + Story.DATA_URL + " TEXT"
                + ")");
    }

    private void createTableComment(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Comment.TABLE_NAME() + " ("
                + Comment.DATA_ID + " INTEGER PRIMARY KEY,"
                + Comment.DATA_TIME + " INTEGER NOT NULL,"
                + Comment.DATA_PARENT + " INTEGER NOT NULL,"
                + Comment.DATA_DELETED + " INTEGER NOT NULL default 0,"
                + Comment.DATA_TYPE + " TEXT,"
                + Comment.DATA_KIDS + " TEXT,"
                + Comment.DATA_BY + " TEXT"
                + Comment.DATA_TEXT + " TEXT"
                + ")");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTableStory(sqLiteDatabase);
        createTableComment(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
