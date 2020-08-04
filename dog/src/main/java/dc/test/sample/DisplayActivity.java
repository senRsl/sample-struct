package dc.test.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;
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

    @Nullable
    @BindView(R.id.tv_name_cn)
    TextView tvNameCn;
    @Nullable
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

        barWrapper.setIdRes(R.layout.activity_bar, R.style.FullTheme, R.id.view_status_bar_place, R.id.layout_frame_content_place);

        //空布局
//        setLayout(true, BridgeOpcode.DEFAULT, true, Color.WHITE);
        //状态栏布局
        setLayout(true, R.layout.activity_detail_dog, true, Color.WHITE);
        //全屏
//        barWrapper.setShowFull(true);
//        setLayout(false, R.layout.activity_detail_dog, true, Color.WHITE);

        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        if (null != tvNameCn) tvNameCn.setText(getClass().getSimpleName());
    }
}
