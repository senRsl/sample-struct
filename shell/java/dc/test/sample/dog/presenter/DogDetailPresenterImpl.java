package dc.test.sample.dog.presenter;

import android.content.Intent;
import dc.android.arch.present.BasePresenter;
import dc.android.common.BridgeOpcode;
import dc.test.sample.Constants;
import dc.test.sample.R;
import dc.test.sample.dog.contract.IDogDetailPresenter;
import dc.test.sample.dog.contract.IDogDetailView;
import dc.test.sample.domain.DogBean;

/**
 * @author senrsl
 * @ClassName: DogDetailPresenterImpl
 * @Package: dc.test.sample.dog.presenter
 * @CreateTime: 2018/11/18 10:03 PM
 */
public class DogDetailPresenterImpl<V extends IDogDetailView> extends BasePresenter<V> implements IDogDetailPresenter<V> {

    private int id;
    private int position;

    private DogBean bean = new DogBean();

    @Override
    public void initData(int id, int position) {
        this.id = id;
        this.position = position;
    }

    @Override
    public void refreshValid() {
        if (!isViewAttached()) return;
        if (BridgeOpcode.DEFAULT == id) getView().showToast(ctx.getString(R.string.invalid_data));
        else refreshData();
    }

    @Override
    public void refreshData() {
        if (!isViewAttached()) return;
        bean = Constants.listDogs.get(id);
        getView().showData(bean);
    }

    @Override
    public void updateInfo(String from) {
        if (!isViewAttached()) return;
        bean.setFrom(from);
        Constants.listDogs.get(id).setFrom(from);
    }

    @Override
    public void back() {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_VAR_1, bean);
        intent.putExtra(Constants.KEY_VAR_2, position);
        getView().setResult(BridgeOpcode.DEFAULT, intent);
    }


}
