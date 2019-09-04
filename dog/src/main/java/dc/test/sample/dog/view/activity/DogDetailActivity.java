package dc.test.sample.dog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnTextChanged;
import dc.android.common.BridgeOpcode;
import dc.android.views.LoadingDialog;
import dc.common.Logger;
import dc.test.sample.Constants;
import dc.test.sample.R;
import dc.test.sample.bridge.BaseSampleActivity;
import dc.test.sample.dog.contract.IDogDetailPresenter;
import dc.test.sample.dog.contract.IDogDetailView;
import dc.test.sample.dog.presenter.DogDetailPresenterImpl;
import dc.test.sample.domain.DogBean;

/**
 * @author senrsl
 * @ClassName: DogDetailActivity
 * @Package: dc.test.sample.dog.view.activity
 * @CreateTime: 2018/11/18 10:08 PM
 */
public class DogDetailActivity extends BaseSampleActivity implements IDogDetailView {

    //1,static final

    //2,layout res

    @BindView(R.id.tv_name_eng)
    TextView tvNameEng;
    @BindView(R.id.tv_name_cn)
    TextView tvNameCn;
    @BindView(R.id.et_from)
    TextView etFrom;

    //3, 业务 相关变量

    private IDogDetailPresenter presenter;

    private LoadingDialog loadingDialog;

    //4, 上层通信

    public static void start(Context context, int id) {
        Intent starter = new Intent(context, DogDetailActivity.class);
        starter.putExtra(Constants.KEY_ID, id);
        context.startActivity(starter);
    }


    //5,生命周期
    //父类对生命周期的引申包含在此序列
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setShowStatus(true);
        setShowNav(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dog);
        setImmersiveStatusBar(true, Color.WHITE);
        //AutoMarginUtils.auto(mFrameLayoutContent);
        initBindView();

    }

    @Override
    protected void initLayout() {
        super.initLayout();
        tvTitle.setText(R.string.dog_title_detail);
    }

    @Override
    protected void initData() {
        super.initData();
        loadingDialog = new LoadingDialog(this);
        loadingDialog.setCancelable(false);
        loadingDialog.setHiddenNav(false);
        loadingDialog.showDialog();
        new Handler().postDelayed(() -> loadingDialog.dismissDialog(), 10000);

        int id = getIntent().getIntExtra(Constants.KEY_ID, BridgeOpcode.DEFAULT);//分别对应 CandyContext.KEY_EB_ID 与CandyOpcode.DEFAULT
        int position = getIntent().getIntExtra(Constants.KEY_VAR_1, BridgeOpcode.DEFAULT);

        presenter = new DogDetailPresenterImpl();
        presenter.attachView(this, this);
        presenter.initData(id, position);

    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.refreshValid();
    }

    @Override
    protected void refreshData() {
        super.refreshData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void finish() {
        presenter.back();
        super.finish();
    }

    //6,activity result 操作

    //7,onClick 事件

    @OnTextChanged({R.id.et_from})
    public void onTextChanged() {
        presenter.updateInfo(String.valueOf(etFrom.getText()));
    }

    //8,本地方法


    //9,view implements

    //9.1 本地view implements

    //9.2 控件view implements

    //9.3 p层 view implements

    @Override
    public void showData(DogBean bean) {
        tvNameEng.setText(bean.getNameEng());
        tvNameCn.setText(bean.getNameChinese());
        etFrom.setText(bean.getFrom());
    }

    @Override
    public void showToast(String msg) {
        Logger.w(this, msg);
    }

    //10,view 事件监听


    //11,业务层Callback，统一为cbXxx，其中vp通信Xxx为Local(default)或LocalXxx


}
