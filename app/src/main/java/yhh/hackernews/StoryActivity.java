package yhh.hackernews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import yhh.hackernews.feed.Story;
import yhh.hackernews.loader.StoryLoader;
import yhh.hackernews.ui.TopStoriesRecyclerAdapter;
import yhh.hackernews.utils.Utilities;

public class StoryActivity extends AppCompatActivity implements StoryLoader.Callback,
        SwipeRefreshLayout.OnRefreshListener, TopStoriesRecyclerAdapter.Callback {
    private static final boolean DEBUG = Utilities.DEBUG;
    private static final String TAG = "StoryActivity";

    private StoryLoader mStoryLoader;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mTopStoriesRecycler;
    private TopStoriesRecyclerAdapter mTopStoriesRecyclerAdapter;

    private ProgressBar mLoadingProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        }
        setContentView(R.layout.activity_story);

        mStoryLoader = StoryLoader.getInstance();
        mStoryLoader.setCallback(this);
        if (mStoryLoader.getStoryList().isEmpty()) {
            mStoryLoader.loadStory();
        }
        initComponents();
    }

    private void initComponents() {
        mTopStoriesRecycler = (RecyclerView) findViewById(R.id.top_stories_recycler);
        mTopStoriesRecycler.setHasFixedSize(true);
        mTopStoriesRecycler.setLayoutManager(new LinearLayoutManager(this));
        mTopStoriesRecyclerAdapter = new TopStoriesRecyclerAdapter(StoryActivity.this, StoryActivity.this, mStoryLoader.getStoryList());
        mTopStoriesRecycler.setAdapter(mTopStoriesRecyclerAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mLoadingProgressbar = (ProgressBar) findViewById(R.id.loading_progressbar);

        if (mStoryLoader.isLoadingStories()) {
            mSwipeRefreshLayout.setEnabled(false);
            mLoadingProgressbar.setVisibility(View.VISIBLE);
        } else {
            mSwipeRefreshLayout.setEnabled(true);
            mLoadingProgressbar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onStoryLoad() {
        mTopStoriesRecyclerAdapter.setStoriesList(mStoryLoader.getStoryList());
        mTopStoriesRecyclerAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);

        if (mStoryLoader.isLoadingStories()) {
            mSwipeRefreshLayout.setEnabled(false);
            mLoadingProgressbar.setVisibility(View.VISIBLE);
        } else {
            mSwipeRefreshLayout.setEnabled(true);
            mLoadingProgressbar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        if (DEBUG) {
            Log.d(TAG, "onRefresh");
        }
        mStoryLoader.loadStory();
        mLoadingProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(Story story) {
        Intent intent = new Intent(StoryActivity.this, CommentActivity.class);
        intent.putExtra(CommentActivity.EXTRA_STORY, story.getContentValues());
        startActivity(intent);
    }
}
