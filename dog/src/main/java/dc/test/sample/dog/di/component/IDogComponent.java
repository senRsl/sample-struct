package dc.test.sample.dog.di.component;

import dagger.Component;
import dc.test.sample.dog.di.module.DogModule;
import dc.test.sample.dog.di.scope.DogScope;
import dc.test.sample.dog.view.fragment.DogListWrapperFooterFragment;

/**
 * @author senrsl
 * @ClassName: IDogComponent
 * @Package: dc.test.sample.dog.ioc.component
 * @CreateTime: 2019/2/14 4:37 PM
 */
@DogScope
@Component(modules = DogModule.class)
public interface IDogComponent {

    void inject(DogListWrapperFooterFragment fragment);//为什么不能用父类

}
