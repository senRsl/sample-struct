package dc.test.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import dc.android.base.activity.BridgeActivity;
import dc.android.common.BridgeContext;
import dc.android.common.BridgeOpcode;
import dc.android.common.utils.KeepInstance;
import dc.common.Logger;

/**
 * @author senrsl
 * @ClassName: TestSampleActivity
 * @Package: dc.test.sample
 * @CreateTime: 2018/12/18 12:07 PM
 */
public class TestSampleActivity extends BridgeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BridgeContext.CLS_WELCOME = DisplayActivity.class.getCanonicalName();
        Logger.w(BridgeContext.CLS_WELCOME, BridgeContext.CLS_LOGIN);
        KeepInstance.getInstance().setStatus(BridgeOpcode.YES);


//        barWrapper.setShowFull(true);
//        barWrapper.setShowNav(true);
        super.onCreate(savedInstanceState);

        barWrapper.setShowStatus(true);
        barWrapper.onCreate();
        generateLayout();
        barWrapper.setImmersiveStatusBar(true, Color.WHITE);
        Logger.w(this, getClass().getName());
    }

    private void generateLayout() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_VERTICAL);

        TextView tvDisplay = new TextView(this);
        tvDisplay.setText(getClass().getCanonicalName() + " " + getClass().getName() + " " + this.getPackageCodePath() + " " + getPackageName() + " " + getPackageResourcePath());
        layout.addView(tvDisplay);

        EditText etInput = new EditText(this);
        etInput.setHint(R.string.app_name);
        layout.addView(etInput);

        Button btn00 = new Button(this);
        btn00.setAllCaps(false);
        btn00.setText(DisplayActivity.class.getCanonicalName());
        btn00.setOnClickListener(v -> DisplayActivity.start(this));
        layout.addView(btn00);

        Button btn01 = new Button(this);
        btn01.setAllCaps(false);
        btn01.setText(SimpleWrapperActivity.class.getCanonicalName());
        btn01.setOnClickListener(v -> SimpleWrapperActivity.start(this));
        layout.addView(btn01);

        setContentView(layout);
    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        hideInputWrapper = new HideInputWrapper(this);
//        return hideInputWrapper.dispatchTouchEvent(ev);
//    }


}
