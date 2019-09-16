package dc.test.sample.dog.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author senrsl
 * @ClassName: DogScope
 * @Package: dc.test.sample.dog.ioc.scope
 * @CreateTime: 2019/2/14 4:31 PM
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface DogScope {
}
