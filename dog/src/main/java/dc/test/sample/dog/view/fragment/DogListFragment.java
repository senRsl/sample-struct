package dc.test.sample.dog.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dc.android.base.listener.OnItemClickListener;
import dc.android.base.listener.OnItemLongClickLIstener;
import dc.android.bridge.adapter.PurposeRecyclerAdapter;
import dc.android.bridge.fragment.BaseListFragment;
import dc.common.Logger;
import dc.test.sample.Constants;
import dc.test.sample.R;
import dc.test.sample.dog.contract.IDogListPresenter;
import dc.test.sample.dog.contract.IDogListView;
import dc.test.sample.dog.presenter.DogListPresenterImpl;
import dc.test.sample.dog.view.adapter.DogListViewHolder;
import dc.test.sample.domain.DogBean;

/**
 * @author senrsl
 * @ClassName: DogListFragment
 * @Package: dc.test.sample.dog.view.fragment
 * @CreateTime: 2018/11/18 10:09 PM
 */
public class DogListFragment extends BaseListFragment implements IDogListView {

    //1,static final

    //2,layout res

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.rv_list)
    RecyclerView rvList;

    //    private DogListAdapter adapter;//独立模式
    private PurposeRecyclerAdapter adapter;//合并模式
    private LinearLayoutManager linearLayoutManager;

    @BindArray(R.array.dogs)
    String[] arrDog;


    //3, 业务 相关变量
    private Unbinder unbinder;

    private IDogListPresenter presenter;


    //4, 上层通信
    //fragment不可通过引用直接调用上层事件，仅适用上层调用fragment方式

    @Deprecated
    public DogListFragment() {
    }

    public static DogListFragment newInstance() {
        DogListFragment fragment = new DogListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    //5,生命周期
    //父类对生命周期的引申包含在此序列
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DogListPresenterImpl();
        presenter.attachView(getActivity(), this);
        Logger.w(getClass().getSimpleName(), presenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_dog, container, false);
        //AutoMarginUtils.auto(rootView);
        unbinder = ButterKnife.bind(this, rootView);
        initSwipeLayout(refreshLayout);

        return rootView;
    }

    @Override
    protected void inflatView() {
        super.inflatView();
        //1,本地工具初始化

        //2，布局相关初始化
//        adapter = new DogListAdapter(presenter.getListData());
//        adapter.setListenerClick(listenerClickItem);
//        adapter.setListenerClickLong(listenerClickLong);
        adapter = new PurposeRecyclerAdapter(DogListViewHolder.class, presenter.getListData());
        adapter.setListenerClick(listenerClickItem);
        adapter.setListenerClickLong(listenerClickLong);

        rvList.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(activity);
        rvList.setLayoutManager(linearLayoutManager);

        //3，业务初始化
        presenter.initData();

        //4,其他第三方操作
    }

    @Override
    public void refreshDataOnCreated() {
        super.refreshDataOnCreated();
        presenter.refreshDataList();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //6,activity result 操作
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.w(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.REQ_DETAIL:
                presenter.detailResult(resultCode, data);
                break;
        }
    }


    //7,onClick 事件


    //8,本地方法


    //9,view implements

    //9.1 本地view implements

    //9.2 控件view implements

    @Override
    public void onRefresh() {
        super.onRefresh();
        presenter.refreshDataList();
    }


    //9.3 p层 view implements

    @Override
    public void showData(int size) {
        adapter.update(presenter.getListData());
    }

    @Override
    public void updateAdapter(int position, DogBean bean) {
        adapter.update(position, bean);
    }


    //10,view 事件监听，统一为 listenerXxx
    private OnItemClickListener listenerClickItem = new OnItemClickListener() {
        @Override
        public void onClick(int position, View v) {
            Logger.w(getClass().getName(), presenter.getListData().get(position));
            presenter.toDetail(position);
        }
    };

    private OnItemLongClickLIstener listenerClickLong = new OnItemLongClickLIstener() {
        @Override
        public void onClickLong(int position, View v) {
            Logger.w(getClass().getName(), presenter.getListData().get(position));
            presenter.toDetailForResult(position, position);//position代替id演示功能
        }
    };


    //11,业务层Callback，统一为cbXxx，其中vp通信Xxx为Local(default)或LocalXxx


}
