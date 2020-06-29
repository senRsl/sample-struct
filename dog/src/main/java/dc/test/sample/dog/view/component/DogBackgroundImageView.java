package dc.test.sample.dog.view.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author senrsl
 * @ClassName: DogBackgroundImageView
 * @Package: dc.test.sample.dog.view.component
 * @CreateTime: 2018/11/18 10:19 PM
 */
public class DogBackgroundImageView extends AppCompatImageView {

    public DogBackgroundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);

    }
}
