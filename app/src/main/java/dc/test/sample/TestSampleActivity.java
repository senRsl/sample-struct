package dc.test.sample;

import android.graphics.Color;
import android.os.Bundle;
import dc.android.base.activity.BridgeActivity;
import dc.android.base.wrapper.ImmersiveBarWrapper;
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
        barWrapper = new ImmersiveBarWrapper(this);
        barWrapper.setShowStatus(true);
        barWrapper.onCreate();
        barWrapper.setImmersiveStatusBar(true, Color.WHITE);
        Logger.w(this, getClass().getName());
    }


}
