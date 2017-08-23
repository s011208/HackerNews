package yhh.hackernews.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import yhh.hackernews.R;

/**
 * Created by yhh
 */

class CommentRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView mByAndTimeInfo, mText;

    private View mBaseView;

    CommentRecyclerViewHolder(View itemView) {
        super(itemView);
        mBaseView = itemView;
        mByAndTimeInfo = (TextView) itemView.findViewById(R.id.by_and_time_info);
        mText = (TextView) itemView.findViewById(R.id.text);
    }

    TextView getByAndTimeInfo() {
        return mByAndTimeInfo;
    }

    TextView getText() {
        return mText;
    }

    View getBaseView() {
        return mBaseView;
    }
}
