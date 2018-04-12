package ceduliocezar.com.nennospizza.matchers;

import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;


public class ImageViewNullDrawableMatcher extends TypeSafeMatcher<View> {


    public ImageViewNullDrawableMatcher() {
        super(ImageView.class);
    }

    public static ImageViewNullDrawableMatcher nullDrawable() {
        return new ImageViewNullDrawableMatcher();
    }

    @Override
    protected boolean matchesSafely(View item) {

        if (!(item instanceof ImageView)) {
            return false;
        }

        ImageView imageView = (ImageView) item;
        return imageView.getDrawable() == null;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("should not have a drawable");
    }
}

