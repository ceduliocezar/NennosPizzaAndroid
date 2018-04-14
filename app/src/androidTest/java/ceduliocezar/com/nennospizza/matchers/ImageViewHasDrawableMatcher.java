package ceduliocezar.com.nennospizza.matchers;

import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ImageViewHasDrawableMatcher extends TypeSafeMatcher<View> {


    public ImageViewHasDrawableMatcher() {
        super(ImageView.class);
    }

    public static ImageViewHasDrawableMatcher hasDrawable() {
        return new ImageViewHasDrawableMatcher();
    }

    @Override
    protected boolean matchesSafely(View item) {

        if (!(item instanceof ImageView)) {
            return false;
        }

        ImageView imageView = (ImageView) item;
        return imageView.getDrawable() != null;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("should have a drawable ");
    }
}
