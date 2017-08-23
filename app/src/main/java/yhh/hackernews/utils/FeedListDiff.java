package yhh.hackernews.utils;

import android.support.v7.util.DiffUtil;

import java.util.List;

import yhh.hackernews.feed.Comment;
import yhh.hackernews.feed.Feed;
import yhh.hackernews.feed.Story;

/**
 * Created by yhh
 */

public class FeedListDiff extends DiffUtil.Callback {
    private List<? extends Feed> mOldList;
    private List<? extends Feed> mNewList;

    public FeedListDiff(List<? extends Feed> oldList, List<? extends Feed> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getId() == mNewList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if (mOldList.get(0) instanceof Story) {
            Story oldItem = (Story) mOldList.get(oldItemPosition);
            Story newItem = (Story) mNewList.get(newItemPosition);

            return oldItem.getDescendants() == newItem.getDescendants() &&
                    oldItem.getScore() == newItem.getScore() &&
                    oldItem.getDisplayIndex() == newItem.getDisplayIndex() &&
                    oldItem.getBy().equals(newItem.getBy());
        } else if (mOldList.get(0) instanceof Comment) {
            Comment oldItem = (Comment) mOldList.get(oldItemPosition);
            Comment newItem = (Comment) mNewList.get(newItemPosition);

            return oldItem.getText() != null && oldItem.getText().equals(newItem.getText());
        }
        return true;
    }
}
