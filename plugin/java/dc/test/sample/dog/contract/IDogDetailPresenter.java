package dc.test.sample.dog.contract;

import dc.android.arch.present.IBasePresenter;
import dc.android.arch.present.IBaseView;

/**
 * @author senrsl
 * @ClassName: IDogDetailPresenter
 * @Package: dc.test.sample.dog.contract
 * @CreateTime: 2018/11/18 9:58 PM
 */
public interface IDogDetailPresenter<V extends IBaseView> extends IBasePresenter<V> {

    /**
     * 初始化数据
     *
     * @param id       详情id
     * @param position 来源列表时的位置
     */
    void initData(int id, int position);

    /**
     * 验证
     * 刷新数据
     */
    void refreshValid();

    /**
     * 刷新数据
     * 直接调用 interactor请求
     */
    void refreshData();

    /**
     * 模拟更新界面信息
     *
     * @param from 更新dog的产地
     */
    void updateInfo(String from);

    /**
     * 返回时填充回调内容
     */
    void back();


}
