package com.example.mockproject_music.common;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mLeft;
    private int mTop;
    private int mRight;
    private int mDown;

    public SpaceItemDecoration(int mLeft, int mTop, int mRight, int mDown) {
        this.mLeft = mLeft;
        this.mTop = mTop;
        this.mRight = mRight;
        this.mDown = mDown;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {

        outRect.right = mRight;
        outRect.top = mTop;
        outRect.left = mLeft;
        outRect.bottom = mDown;
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() -1) {
            outRect.right = mRight;
            outRect.top = mTop;
            outRect.left = mLeft;
            outRect.bottom = mDown;

        }
    }
}
