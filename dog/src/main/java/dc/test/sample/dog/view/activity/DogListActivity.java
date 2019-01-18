package dc.test.sample.dog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import dc.test.sample.Constants;
import dc.test.sample.R;
import dc.test.sample.bridge.BaseSampleActivity;
import dc.test.sample.dog.view.DogViewGenerator;

/**
 * @author senrsl
 * @ClassName: DogListActivity
 * @Package: dc.test.sample.dog.view.activity
 * @CreateTime: 2018/11/18 10:07 PM
 */
public class DogListActivity extends BaseSampleActivity {

    //1,static final

    //2,layout res

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_more)
    TextView tvMore;

    //3, 业务 相关变量

    //4, 上层通信
    public static void start(Context context, int listType) {
        Intent starter = new Intent(context, DogListActivity.class);
        starter.putExtra(Constants.KEY_VAR_1, listType);
        context.startActivity(starter);
    }

    //5,生命周期
    //父类对生命周期的引申包含在此序列
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setShowStatus(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dog);
        setImmersiveStatusBar(true, Color.WHITE);
        //AutoMarginUtils.auto(mFrameLayoutContent);
        initBindView();
    }

    @Override
    protected void initLayout() {
        super.initLayout();


    }

    @Override
    protected void initData() {
        super.initData();

        int listType = getIntent().getIntExtra(Constants.KEY_VAR_1, Constants.LIST_TYPE_SWIPE);
        switch (listType) {
            case Constants.LIST_TYPE_SWIPE:
                commitFragment(R.id.layout_container, DogViewGenerator.generateFragment());
                tvTitle.setText(R.string.dog_title_list);
                break;
            case Constants.LIST_TYPE_SWIPE_WRAPPER:
            default:
                commitFragment(R.id.layout_container, DogViewGenerator.generateDogListWrapperFooterFragment());
                tvTitle.setText(R.string.dog_title_list_wrapper);
                break;
        }
    }

    //6,activity result 操作

    //7,onClick 事件


    //8,本地方法


    //9,view implements

    //9.1 本地view implements

    //9.2 控件view implements

    //9.3 p层 view implements


    //10,view 事件监听


    //11,业务层Callback，统一为cbXxx，其中vp通信Xxx为Local(default)或LocalXxx
}
