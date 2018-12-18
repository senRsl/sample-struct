package dc.test.sample.dog.view.activity;

import dc.test.sample.R;
import dc.test.sample.bridge.BaseSampleActivity;
import dc.test.sample.dog.view.DogViewGenerator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;

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
    public static void start(Context context) {
        Intent starter = new Intent(context, DogListActivity.class);
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
        tvTitle.setText(R.string.dog_title_list);

        commitFragment(R.id.layout_container, DogViewGenerator.generateFragment());
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
