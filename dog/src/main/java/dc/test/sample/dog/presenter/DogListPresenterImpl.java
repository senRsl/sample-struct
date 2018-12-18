package dc.test.sample.dog.presenter;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;

import dc.test.sample.Constants;
import dc.test.sample.dog.contract.IDogListPresenter;
import dc.test.sample.dog.contract.IDogListView;
import dc.test.sample.dog.view.activity.DogDetailActivity;
import dc.test.sample.domain.DogBean;

import dc.android.arch.present.BasePresenter;
import dc.android.common.SelfOpcode;

/**
 * @author senrsl
 * @ClassName: DogListPresenterImpl
 * @Package: dc.test.sample.dog.presenter
 * @CreateTime: 2018/11/18 10:05 PM
 */
public class DogListPresenterImpl<V extends IDogListView> extends BasePresenter<V> implements IDogListPresenter<V> {

    private List<DogBean> listData;

    @Override
    public List<DogBean> getListData() {
        return listData;
    }

    @Override
    public void initData() {
        listData = new ArrayList<>();
    }


    @Override
    public void refreshDataList() {
        //从这里调用 interactor,返回show
        listData = Constants.listDogs;
        showData(listData);
    }

    @Override
    public void showData(List<DogBean> list) {
        if (!isViewAttached()) return;
        getView().showData(list.size());
        getView().cancelRefresh();
    }

    @Override
    public void toDetail(int id) {
        DogDetailActivity.start(ctx, id);
    }

    @Override
    public void toDetailForResult(int position, int id) {
        Intent intent = new Intent(ctx, DogDetailActivity.class);
        intent.putExtra(Constants.KEY_ID, id);
        intent.putExtra(Constants.KEY_VAR_1, position);
        getView().startActivityForResult(intent, Constants.REQ_DETAIL);
    }

    @Override
    public void detailResult(int resultCode, Intent data) {
        if (!isViewAttached()) return;
        switch (resultCode) {
            case SelfOpcode.DEFAULT:
                DogBean dogBean = data.getParcelableExtra(Constants.KEY_VAR_1);
                int position = data.getIntExtra(Constants.KEY_VAR_2, SelfOpcode.DEFAULT);
                if (SelfOpcode.DEFAULT == position) break;

                getView().updateAdapter(position, dogBean);
                break;
        }
    }
}
