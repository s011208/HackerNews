package yhh.hackernews.ui;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yhh.hackernews.R;
import yhh.hackernews.feed.Comment;
import yhh.hackernews.utils.FeedListDiff;
import yhh.hackernews.utils.Utilities;

/**
 * Created by yhh
 */

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerViewHolder> {
    private final Context mContext;
    private final List<Comment> mCommentList = new ArrayList<>();

    public CommentRecyclerAdapter(@NonNull Context context, @NonNull List<Comment> commentList) {
        mContext = context;
        mCommentList.addAll(commentList);
    }


    @Override
    public CommentRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentRecyclerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.comment_list_item, null));
    }

    @Override
    public void onBindViewHolder(CommentRecyclerViewHolder holder, int position) {
        final Comment comment = getItem(position);
        holder.getByAndTimeInfo().setText(mContext.getString(R.string.comment_by_and_time_info, comment.getBy(), Utilities.getTimeDiff(mContext, System.currentTimeMillis(), comment.getTime() * 1000)));
        if (!TextUtils.isEmpty(comment.getText())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.getText().setText(Html.fromHtml(comment.getText(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                //noinspection deprecation
                holder.getText().setText(Html.fromHtml(comment.getText()));
            }
        } else {
            holder.getText().setText(comment.getText());
        }
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    @NonNull
    private Comment getItem(int position) {
        return mCommentList.get(position);
    }

    public void updateCommentList(List<Comment> commentList) {
        final FeedListDiff diff = new FeedListDiff(mCommentList, commentList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diff);

        mCommentList.clear();
        mCommentList.addAll(commentList);

        diffResult.dispatchUpdatesTo(this);
    }
}
