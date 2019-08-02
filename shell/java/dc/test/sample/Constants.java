package dc.test.sample;

import java.util.ArrayList;
import java.util.List;

import dc.test.sample.domain.DogBean;

/**
 * @author senrsl
 * @ClassName: Constants
 * @Package: dc.test.sample
 * @CreateTime: 2018/11/20 1:57 PM
 */
public class Constants {
    public static List<DogBean> listDogs = new ArrayList<>();//模拟 dog 数据源

    public static final String KEY_ID = "id";//对应 CandyContext.KEY_EB_ID
    public static final String KEY_VAR_1 = "var1";//对应 CandyContext.KEY_VAR_1
    public static final String KEY_VAR_2 = "var2";//对应 CandyContext.KEY_VAR_2

    public static final int REQ_DETAIL = 1;//对应 CandyOpcode.REQ_DETAIL

    public static final int PAGE_NUM = 30;//对应 CandyContext.PAGE_NUM

    public static final int LIST_TYPE_SWIPE = 0;
    public static final int LIST_TYPE_SWIPE_WRAPPER = 1;

}
