package yhh.hackernews.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yhh.hackernews.R;
import yhh.hackernews.feed.Story;
import yhh.hackernews.utils.Utilities;

/**
 * Created by yhh
 */

public class TopStoriesRecyclerAdapter extends RecyclerView.Adapter<TopStoriesRecyclerViewHolder> {
    public interface Callback {
        void onItemClick(Story story);
    }

    private final Context mContext;
    private final Callback mCallback;
    private final List<Story> mStoriesList = new ArrayList<>();

    public TopStoriesRecyclerAdapter(Context context, Callback cb, List<Story> storiesList) {
        mContext = context;
        mCallback = cb;
        mStoriesList.addAll(storiesList);
    }

    public void setStoriesList(List<Story> storiesList) {
        mStoriesList.clear();
        mStoriesList.addAll(storiesList);
    }

    @Override
    public TopStoriesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopStoriesRecyclerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.top_stories_list_item, null));
    }

    @Override
    public void onBindViewHolder(TopStoriesRecyclerViewHolder holder, int position) {
        final Story story = getItem(position);
        holder.getTitlePrefix().setText((position + 1) + ". ");
        holder.getTitle().setText(story.getTitle());
        holder.getByInfo().setText(mContext.getString(R.string.top_stories_by_info, story.getScore(),
                story.getScore() <= 1 ? mContext.getString(R.string.point) : mContext.getString(R.string.points), story.getBy()));
        holder.getTimeAndCommentInfo().setText(mContext.getString(R.string.top_stories_time_and_comment_info,
                Utilities.getTimeDiff(mContext, System.currentTimeMillis(), story.getTime() * 1000), story.getDescendants(),
                story.getDescendants() <= 1 ? mContext.getString(R.string.comment) : mContext.getString(R.string.comments)));
        holder.getBaseView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback == null) return;
                mCallback.onItemClick(story);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStoriesList.size();
    }

    Story getItem(int index) {
        return mStoriesList.get(index);
    }
}
