package com.kilograpp.oromilconverter.view.custom;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextWatcher;
import android.util.AttributeSet;

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {

    TextWatcher textWatcher;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addTextChangedListener(CustomTextWatcher watcher) {
        textWatcher = watcher;
        if (isFocused())
            activateTextWatcher();
    }

    public void activateTextWatcher() {
        if (textWatcher != null)
            addTextChangedListener(textWatcher);
    }

    public void disableTextWatcher() {
        if (textWatcher != null)
            removeTextChangedListener(textWatcher);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused){
            activateTextWatcher();}
        else {
            setEnabled(false);
            disableTextWatcher();
        }
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        setEnabled(true);
        return super.requestFocus(direction, previouslyFocusedRect);
    }
}
