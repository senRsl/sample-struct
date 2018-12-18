package dc.test.sample.dog.view;

import android.os.Bundle;

import dc.test.sample.dog.view.fragment.DogListFragment;

/**
 * @author senrsl
 * @ClassName: DogViewGenerator
 * @Package: dc.test.sample.dog.view
 * @CreateTime: 2018/11/18 10:01 PM
 */
public class DogViewGenerator {

    public static DogListFragment generateFragment() {
        DogListFragment dynamicFragment = DogListFragment.newInstance();

        Bundle bundleAll = new Bundle();
        dynamicFragment.setArguments(bundleAll);
        return dynamicFragment;
    }
}
