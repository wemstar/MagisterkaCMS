package pl.edu.agh.fis.activity.document;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.ViewById;
import pl.edu.agh.fis.R;

/**
 * TODO: document your custom view class.
 */
@EView
public class DocumetListCell extends View {

    @ViewById
    EditText documentTitleView;

    @ViewById
    EditText documentDetailView;

    public DocumetListCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
