package dc.test.sample.bridge;

import dc.android.bridge.activity.BaseFrameActivity;
import dc.test.sample.R;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dc.common.Logger;

/**
 * @author senrsl
 * @ClassName: BaseSampleActivity
 * @Package: dc.test.sample.bridge
 * @CreateTime: 2018/11/19 6:48 PM
 */
public class BaseSampleActivity extends BaseFrameActivity {

    @BindView(R.id.tv_back)
    protected TextView tvBack;
    @BindView(R.id.tv_title)
    protected TextView tvTitle;
    @BindView(R.id.tv_more)
    protected TextView tvMore;


    protected void initBindView() {
        ButterKnife.bind(this);
        initOnCreate();
    }

    @OnClick({R.id.tv_back, R.id.tv_title, R.id.tv_more})
    public void onViewClickedTitle(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_title:
                Logger.w(this, tvTitle.getText());
                break;
            case R.id.tv_more:
                Logger.w(this, tvMore.getText());
                break;
            default:
                break;
        }
    }

}
