package dc.test.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import butterknife.BindArray;
import butterknife.OnClick;
import dc.android.common.BridgeContext;
import dc.android.common.BridgeOpcode;
import dc.android.common.handler.CrashHandler;
import dc.android.common.utils.KeepInstance;
import dc.common.Logger;
import dc.test.sample.bridge.BaseSampleActivity;
import dc.test.sample.dog.view.activity.DogListActivity;
import dc.test.sample.domain.DogBean;

/**
 * @author senrsl
 * @ClassName: SampleActivity
 * @Package: dc.test.sample
 * @CreateTime: 2018/11/18 9:52 PM
 */
public class SampleActivity extends BaseSampleActivity {

    @BindArray(R.array.dogs)
    String[] dogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        KeepInstance.getInstance().setStatus(BridgeOpcode.YES);
        setShowStatus(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        setImmersiveStatusBar(true, Color.WHITE);
        //AutoMarginUtils.auto(mFrameLayoutContent);
        initBindView();
    }

    @Override
    protected void initLayout() {
        super.initLayout();
        tvTitle.setText(R.string.app_name);
    }

    @Override
    protected void initData() {
        generateFromXml();
    }


    @OnClick({R.id.btn_list_dog, R.id.btn_list_dog_wrapper, R.id.btn_add_dog, R.id.btn_display, R.id.btn_crash})
    public void onViewClickedContent(View v) {
        switch (v.getId()) {
            case R.id.btn_list_dog:
                DogListActivity.start(this, Constants.LIST_TYPE_SWIPE);
                break;
            case R.id.btn_list_dog_wrapper:
                DogListActivity.start(this, Constants.LIST_TYPE_SWIPE_WRAPPER);
                break;
            case R.id.btn_add_dog:
                break;
            case R.id.btn_crash:
                //VsfApplication中，先!isDebug，再初始化
                CrashHandler.getInstance().init(getApplicationContext());
                BridgeContext.CLS_WELCOME = SampleActivity.class.getCanonicalName();
                int i = 1 / 0;
                break;
            default:
                DisplayActivity.start(this);
                break;
        }
    }


    private void generateFromXml() {
        Logger.w(dogs);
        int i = 0;
        for (String dog : dogs) {
            Logger.w(dog);
            String[] dogValue = dog.split(",");
            Logger.w(dogValue);
            DogBean bean = new DogBean(dogValue[0], dogValue[1] + i++, dogValue[2]);
            Constants.listDogs.add(bean);
        }
    }

}
