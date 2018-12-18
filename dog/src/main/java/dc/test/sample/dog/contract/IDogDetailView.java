package dc.test.sample.dog.contract;

import android.content.Intent;

import dc.test.sample.domain.DogBean;

import dc.android.arch.present.IBaseView;

/**
 * @author senrsl
 * @ClassName: IDogDetailView
 * @Package: dc.test.sample.dog.contract
 * @CreateTime: 2018/11/18 9:57 PM
 */
public interface IDogDetailView extends IBaseView {

    /**
     * 更新界面显示数据
     *
     * @param bean 数据源
     */
    void showData(DogBean bean);

    /**
     * toast 提示
     *
     * @param msg
     */
    void showToast(String msg);

    /**
     * 桥接系统设置返回数据
     *
     * @param resultCode 同
     * @param data       同
     */
    void setResult(int resultCode, Intent data);

}
