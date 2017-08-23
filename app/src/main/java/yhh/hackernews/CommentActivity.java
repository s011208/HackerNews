package yhh.hackernews;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import yhh.hackernews.feed.Comment;
import yhh.hackernews.feed.Story;
import yhh.hackernews.loader.CommentLoader;
import yhh.hackernews.ui.CommentRecyclerAdapter;
import yhh.hackernews.utils.Utilities;

/**
 * Created by yhh
 */

public class CommentActivity extends AppCompatActivity implements CommentLoader.Callback {
    private static final String TAG = "CommentActivity";
    private static final boolean DEBUG = Utilities.DEBUG;

    public static final String EXTRA_STORY = "extra_story";

    private Story mStory;
    private CommentLoader mCommentLoader;

    private RecyclerView mRecyclerView;
    private CommentRecyclerAdapter mCommentRecyclerAdapter;
    private TextView mHintTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mStory = new Story((ContentValues) getIntent().getParcelableExtra(EXTRA_STORY));
        if (DEBUG) {
            Log.v(TAG, "story: " + mStory.toString());
        }
        mCommentLoader = CommentLoader.getInstance();
        mCommentLoader.setCallback(this);
        mCommentLoader.setKids(mStory.getId(), mStory.getKids());

        setContentView(R.layout.activity_comment);
        initComponents();
        mCommentLoader.loadComments(mStory);
    }

    private void initComponents() {
        mHintTextView = (TextView) findViewById(R.id.hint_textview);

        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Comment> commentList = getComments(mStory.getId(), 0);
        if (!commentList.isEmpty()) {
            mHintTextView.setVisibility(View.INVISIBLE);
        } else if (!mStory.getKids().isEmpty()) {
            mHintTextView.setText(R.string.comment_activity_loading_hint);
        }
        mCommentRecyclerAdapter = new CommentRecyclerAdapter(CommentActivity.this, commentList);
        mRecyclerView.setAdapter(mCommentRecyclerAdapter);
    }

    @Override
    public void onBindComments(Comment comment) {
        if (DEBUG) {
            Log.d(TAG, "onBindComments, comment id: " + comment.getId() + ", by: " + comment.getBy());
        }
        if (comment.isDeleted()) {
            if (mCommentRecyclerAdapter.getItemCount() == 0) {
                mHintTextView.setText(R.string.comment_activity_no_comments_hint);
            }
            return;
        }

        List<Comment> storyComments = getComments(mStory.getId(), 0);

        mCommentRecyclerAdapter.updateCommentList(storyComments);

        mHintTextView.setVisibility(View.INVISIBLE);
    }

    private List<Comment> getComments(long keyId, int level) {
        List<Comment> kidsComment = mCommentLoader.getComments(keyId);
        if (kidsComment.isEmpty()) return new ArrayList<>();
        List<Comment> rtn = new ArrayList<>();
        for (Comment comment : kidsComment) {
            if (comment.isDeleted()) continue;
            comment.setLevel(level);
            rtn.add(comment);
            rtn.addAll(getComments(comment.getId(), level + 1));
        }
        return rtn;
    }
}
