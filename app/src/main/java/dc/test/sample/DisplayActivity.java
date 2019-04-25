package dc.test.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import dc.android.base.activity.BarActivity;
import dc.android.common.BridgeOpcode;
import dc.android.common.utils.KeepInstance;

/**
 * @author senrsl
 * @ClassName: DisplayActivity
 * @Package: dc.test.sample
 * @CreateTime: 2019/4/24 5:36 PM
 */
public class DisplayActivity extends BarActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, DisplayActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        KeepInstance.getInstance().setStatus(BridgeOpcode.YES);
        setShowStatus(true);
        super.onCreate(savedInstanceState);
        initLayout();

    }

    //    @Override
    protected void initLayout() {
        //super.initLayout();
        TextView tvDisplay = new TextView(this);
        tvDisplay.setText(R.string.app_name);
        setContentView(tvDisplay);
    }
}
