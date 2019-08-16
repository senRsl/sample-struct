package dc.test.sample.dog.view.adapter;

import java.util.List;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dc.android.base.listener.OnItemClickListener;
import dc.android.base.listener.OnItemLongClickLIstener;
import dc.test.sample.R;
import dc.test.sample.domain.DogBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import dc.common.Logger;

/**
 * 独立方式
 *
 * @author senrsl
 * @ClassName: DogListAdapter
 * @Package: dc.test.sample.dog.view.adapter
 * @CreateTime: 2018/11/19 4:12 PM
 */
@Deprecated
public class DogListAdapter extends RecyclerView.Adapter<DogListAdapter.ViewHolder> {

    //1，内部变量
    private List<DogBean> listData;
    private OnItemClickListener listenerClick;
    private OnItemLongClickLIstener listenerClickLong;


    //2,前端通信
    //只可接收，不可发送
    public DogListAdapter(List<DogBean> listData) {
        this.listData = listData;
    }

    public void updateData(List<DogBean> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    public void setListenerClick(OnItemClickListener listenerClick) {
        this.listenerClick = listenerClick;
    }

    public void setListenerClickLong(OnItemLongClickLIstener listenerClickLong) {
        this.listenerClickLong = listenerClickLong;
    }


    //3,生命周期

    @NonNull
    @Override
    public DogListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);
        //AutoMarginUtils.auto(v);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DogListAdapter.ViewHolder holder, int position) {
        DogBean bean = listData.get(position);
        Logger.w(bean, holder, holder.tvName);

        holder.tvName.setText(bean.getNameChinese());
        holder.tvFrom.setText(bean.getFrom());

        holder.itemView.setOnClickListener(v -> {
            if (null != listenerClick) listenerClick.onClick(position, v);
        });

        holder.tvName.setOnLongClickListener(v -> {
            if (null != listenerClickLong) listenerClickLong.onClickLong(position, v);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return null == listData ? 0 : listData.size();
    }


    //4,本地监听


    //5.内部类放在最后
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_from)
        TextView tvFrom;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


}
