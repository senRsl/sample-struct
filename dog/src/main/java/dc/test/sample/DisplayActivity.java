package dc.test.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import dc.android.base.activity.BridgeActivity;
import dc.android.common.BridgeContext;
import dc.android.common.utils.AutoMarginUtils;

/**
 * @author senrsl
 * @ClassName: DisplayActivity
 * @Package: dc.test.sample
 * @CreateTime: 2019/4/24 6:27 PM
 */
public class DisplayActivity extends BridgeActivity {

    @BindView(R.id.tv_name_cn)
    TextView tvNameCn;
    @BindView(R.id.et_from)
    EditText etFrom;

    public static void start(Context context) {
        Intent starter = new Intent(context, DisplayActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initLayout() {
        super.initLayout();
        AutoMarginUtils.setSize(getApplication(), true, BridgeContext.DESIGN_WIDTH, BridgeContext.DESIGN_HEIGHT);
//        setLayout(true, BridgeOpcode.DEFAULT, true, Color.WHITE);
        setLayout(true, R.layout.activity_detail_dog, true, Color.WHITE);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        tvNameCn.setText(getClass().getSimpleName());
    }
}
