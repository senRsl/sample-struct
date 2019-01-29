package dc.test.sample.dog.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import dc.android.bridge.adapter.PurposeRecyclerAdapter;
import dc.android.bridge.annotation.RecyclerItemViewId;
import dc.common.Logger;
import dc.test.sample.R;
import dc.test.sample.domain.DogBean;

/**
 * 此类仅与adapter通信
 * 并持有adapter引用
 * <p>
 * 处理item相关界面变化
 *
 * @author senrsl
 * @ClassName: DogListViewHolder
 * @Package: dc.test.sample.dog.view.adapter
 * @CreateTime: 2018/11/20 11:36 AM
 */
@RecyclerItemViewId(R.layout.item_dog)
public class DogListViewHolder extends PurposeRecyclerAdapter.PurposeViewHolder<DogBean> {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_from)
    TextView tvFrom;

    private Context ctx;
    private DogBean bean;
    private int position;
    private PurposeRecyclerAdapter adapter;

    public DogListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);//将viewhodler于我们的view绑定起来
    }

    @Override
    public void convert(DogBean bean, PurposeRecyclerAdapter adapter, Context context, int position) {
        this.ctx = context;
        this.bean = bean;
        this.adapter = adapter;
        this.position = position;
        //Logger.w(bean, adapter, tvName);

        tvName.setText(bean.getNameChinese());
        tvFrom.setText(bean.getFrom());
    }

    @OnClick({R.id.layout_item_dog, R.id.tv_name})
    void onViewClickedItem() {
        Logger.w(ctx, bean.toString());
        if (null != adapter.getListenerClick()) adapter.getListenerClick().onClick(position, itemView);
    }

    @OnLongClick({R.id.tv_from})
    boolean onViewLongClickedItem() {
        Logger.w(ctx, bean.toString());
        adapter.getListenerClickLong().onClickLong(position, tvFrom);
        return false;
    }
}
