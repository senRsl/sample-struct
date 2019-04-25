package dc.test.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import dc.android.base.wrapper.HideInputWrapper;
import dc.android.base.wrapper.ImmersiveBarWrapper;
import dc.android.base.wrapper.SplashWrapper;
import dc.android.bridge.activity.BaseStructWrapperActivity;

/**
 * @author senrsl
 * @ClassName: SimpleWrapperActivity
 * @Package: dc.test.sample
 * @CreateTime: 2019/4/25 3:14 PM
 */
public class SimpleWrapperActivity extends BaseStructWrapperActivity {

    protected ImmersiveBarWrapper barWrapper;
    protected HideInputWrapper hideInputWrapper;

    public static void start(Context context) {
        Intent starter = new Intent(context, SimpleWrapperActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashWrapper splashWrapper = new SplashWrapper(this);
        splashWrapper.checkKeep();

        barWrapper = new ImmersiveBarWrapper(this);
        barWrapper.setShowStatus(true);
        barWrapper.setShowNav(true);
//        barWrapper.setShowFull(true);
        barWrapper.onCreate();
        initOnCreate();
        barWrapper.setImmersiveStatusBar(true, Color.WHITE);
    }

    @Override
    protected void initLayout() {
        super.initLayout();
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_VERTICAL);

        TextView tvDisplay = new TextView(this);
        tvDisplay.setText(R.string.app_name);
        layout.addView(tvDisplay);

        EditText etInput = new EditText(this);
        etInput.setHint(R.string.app_name);
        layout.addView(etInput);

        setContentView(layout);
    }

    @Override
    protected void initData() {
        super.initData();
        hideInputWrapper = new HideInputWrapper(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return hideInputWrapper.dispatchTouchEvent(ev);
    }
}
