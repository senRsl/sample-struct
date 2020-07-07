package dc.test.sample.dog.contract;

import android.content.Intent;

import dc.test.sample.domain.DogBean;

import dc.android.arch.present.IBaseView;

/**
 * @author senrsl
 * @ClassName: IDogListView
 * @Package: dc.test.sample.dog.contract
 * @CreateTime: 2018/11/18 9:58 PM
 */
public interface IDogListView extends IBaseView {

    /**
     * 取消刷新效果
     */
    void cancelRefresh();

    /**
     * 显示数据
     *
     * @param size
     */
    void showData(int size);


    /**
     * @param intent
     * @param requestCode
     */
    void startActivityForResult(Intent intent, int requestCode);


    /**
     * 更新单条数据
     *
     * @param position
     * @param bean
     */
    void updateAdapter(int position, DogBean bean);

}
