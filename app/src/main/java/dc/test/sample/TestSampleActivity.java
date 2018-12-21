package dc.test.sample;

import android.os.Bundle;
import dc.android.base.activity.BarActivity;
import dc.android.common.BridgeOpcode;
import dc.android.common.utils.KeepInstance;
import dc.common.Logger;

/**
 * @author senrsl
 * @ClassName: TestSampleActivity
 * @Package: dc.test.sample
 * @CreateTime: 2018/12/18 12:07 PM
 */
public class TestSampleActivity extends BarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        KeepInstance.getInstance().setStatus(BridgeOpcode.YES);
        super.onCreate(savedInstanceState);
        Logger.w(this, getClass().getName());
    }
}