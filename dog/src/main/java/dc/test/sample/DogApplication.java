package dc.test.sample;

import dc.android.shell.ShellApplication;
import dc.android.shell.listener.ShellActivityLifecycleCallbacks;

/**
 * @author senrsl
 * @ClassName: DogApplication
 * @Package: dc.test.sample
 * @CreateTime: 2019/8/2 8:26 PM
 */
public class DogApplication extends ShellApplication {

    public DogApplication() {
        lifecycleCallbacks = new ShellActivityLifecycleCallbacks();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }
}
