package dc.test.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import dc.android.base.activity.BridgeActivity;
import dc.android.base.wrapper.ImmersiveBarWrapper;

/**
 * @author senrsl
 * @ClassName: DisplayActivity
 * @Package: dc.test.sample
 * @CreateTime: 2019/4/24 6:27 PM
 */
public class DisplayActivity extends BridgeActivity {


    public static void start(Context context) {
        Intent starter = new Intent(context, DisplayActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ImmersiveBarWrapper barWrapper = new ImmersiveBarWrapper(this);

        barWrapper.setShowStatus(true);
//        barWrapper.setShowNav(true);
//        barWrapper.setShowFull(true);
        super.onCreate(savedInstanceState);
        barWrapper.onCreate();
        barWrapper.setImmersiveStatusBar(true, Color.WHITE);
    }
}
