package dc.test.sample.dog.contract;

import java.util.List;

import android.content.Intent;

import dc.test.sample.domain.DogBean;

import dc.android.arch.present.IBasePresenter;
import dc.android.arch.present.IBaseView;

/**
 * @author senrsl
 * @ClassName: IDogListPresenter
 * @Package: dc.test.sample.dog.contract
 * @CreateTime: 2018/11/18 9:58 PM
 */
public interface IDogListPresenter<V extends IBaseView> extends IBasePresenter<V> {

    /**
     * 获取 数据存储集合
     *
     * @return model list
     */
    List<DogBean> getListData();

    /**
     * 数据初始化
     */
    void initData();

    /**
     * 请求刷新数据
     * 应为 refreshData()
     */
    void refreshDataList();

    /**
     * 刷新数据完成，回调返回
     *
     * 应为private,不对外暴露
     *
     * @param list 新数据集合
     */
    void showData(List<DogBean> list);


    /**
     * 跳转到详情页
     *
     * @param id 详情id
     */
    void toDetail(int id);

    /**
     * 跳转详情页并获取返回
     *
     * @param position
     * @param id
     */
    void toDetailForResult(int position, int id);

    /**
     * 详情页返回
     *
     * @param resultCode
     * @param data
     */
    void detailResult(int resultCode, Intent data);
}
