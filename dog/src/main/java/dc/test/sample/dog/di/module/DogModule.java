package dc.test.sample.dog.di.module;

import dagger.Module;
import dagger.Provides;
import dc.test.sample.dog.contract.IDogListPresenter;
import dc.test.sample.dog.presenter.DogListPresenterImpl;

/**
 * @author senrsl
 * @ClassName: DogModule
 * @Package: dc.test.sample.dog.ioc.module
 * @CreateTime: 2019/2/14 4:40 PM
 */
@Module
public class DogModule {

    @Provides
    public IDogListPresenter provideDogListPresenter() {
        return new DogListPresenterImpl();
    }

}
