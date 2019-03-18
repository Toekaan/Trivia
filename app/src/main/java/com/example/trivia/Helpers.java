package com.example.trivia;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.Button;

public class Helpers {
    /** aniamtes the given button
     * FROM: https://android--code.blogspot.com/2015/07/android-background-color-animation.html */
    static void animateButton(Button button) {
        ColorDrawable[] color = {new ColorDrawable(Color.WHITE), new ColorDrawable(Color.GREEN)};
        TransitionDrawable trans = new TransitionDrawable(color);
        button.setBackground(trans);
        trans.startTransition(1500); // duration 3 seconds
    }
}
