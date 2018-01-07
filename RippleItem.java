package com.cilenco.ui.menu;

import android.view.View;
import android.widget.LinearLayout.LayoutParams;

import com.cilenco.testApplication.R;
import com.cilenco.ui.views.WholeRippleView;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter_extensions.drag.IDraggable;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.animation.Animation.INFINITE;

public class RippleItem extends PrimaryDrawerItem {
    protected boolean dragable;
    protected WholeRippleView ripple;

    public void playRipple(boolean play) {
        if(ripple == null) return;

        if(play) ripple.animateRipple();
        else ripple.stopRipple();
    }

    @Override
    public int getType() {
        return R.id.settingsItem; // Make sure to use a custom ID here
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        ripple = new WholeRippleView(v.getContext());

        v.setPadding(0, 0, 0, 0);
        LayoutParams params = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);

        ripple.setLayoutParams(params);
        ripple.setRippleRepeatCount(INFINITE);
        ripple.setRippleDuration(900);
        ripple.addView(v);

        return new ViewHolder(ripple);
    }
}
