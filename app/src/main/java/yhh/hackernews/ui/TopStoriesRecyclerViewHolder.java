package yhh.hackernews.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import yhh.hackernews.R;

/**
 * Created by yhh
 */
class TopStoriesRecyclerViewHolder extends RecyclerView.ViewHolder {
    private View mBaseView;

    private TextView mTitlePrefix, mTitle, mByInfo, mTimeAndCommentInfo;

    TopStoriesRecyclerViewHolder(View itemView) {
        super(itemView);
        mBaseView = itemView;
        mTitlePrefix = (TextView) itemView.findViewById(R.id.title_prefix);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mByInfo = (TextView) itemView.findViewById(R.id.by_info);
        mTimeAndCommentInfo = (TextView) itemView.findViewById(R.id.time_and_comment_info);
    }

    View getBaseView() {
        return mBaseView;
    }

    TextView getTitlePrefix() {
        return mTitlePrefix;
    }

    TextView getTitle() {
        return mTitle;
    }

    TextView getByInfo() {
        return mByInfo;
    }

    TextView getTimeAndCommentInfo() {
        return mTimeAndCommentInfo;
    }
}
