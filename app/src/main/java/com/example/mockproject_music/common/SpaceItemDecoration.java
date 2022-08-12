package com.example.mockproject_music.common;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) %2 == 0) {
            outRect.right = space;
        } else {
            outRect.right = 0;
        }
    }
}
