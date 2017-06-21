package in.cyberprism.libin.milma.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.utils.Fonts;

/**
 * Created by libin on 21/06/17.
 */

public class MLTextView extends android.support.v7.widget.AppCompatTextView {
    public MLTextView(Context context) {
        super(context);

        init(context, null);
    }

    public MLTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public MLTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MLTextView, 0, 0);

            try {
                int font = attributes.getInteger(R.styleable.MLTextView_ml_textview_font, 9);
                int fontStyle = attributes.getInteger(R.styleable.MLTextView_ml_textview_fontstyle, 0);

                switch (font) {
                    case 0:
                        setFont(fontStyle, context, Fonts.GEOSANS_FONT);

                    case 1:
                        setFont(fontStyle, context, Fonts.METANOIA_FONT);
                        break;

                    case 2:
                        setFont(fontStyle, context, Fonts.SANSATION_REGULAR);
                        break;

                    case 3:
                        setFont(fontStyle, context, Fonts.SANSATION_BOLD);
                        break;

                    case 9:
                        setFont(fontStyle, context, Fonts.DEFAULT_FONT);
                        break;

                    default:
                        setFont(fontStyle, context, Fonts.DEFAULT_FONT);
                }


            } finally {
                attributes.recycle();
            }

        } else {
            Typeface type = Typeface.createFromAsset(context.getAssets(), Fonts.DEFAULT_FONT);
            this.setTypeface(type);
        }
    }

    private void setFont(int fontStyle, Context context, String font) {

        switch (fontStyle) {
            case 0:
                Typeface type = Typeface.createFromAsset(context.getAssets(), font);
                this.setTypeface(type);
                break;

            case 1:
                type = Typeface.createFromAsset(context.getAssets(), font);
                this.setTypeface(type);
                break;

            case 2:
                type = Typeface.createFromAsset(context.getAssets(), font);
                this.setTypeface(type);
                break;

            case 3:
                type = Typeface.createFromAsset(context.getAssets(), font);
                this.setTypeface(type);
                break;

            default:
                type = Typeface.createFromAsset(context.getAssets(), font);
                this.setTypeface(type);
                break;
        }
    }

}
