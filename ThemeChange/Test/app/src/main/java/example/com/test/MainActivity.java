package example.com.test;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private TextView mOriginalText;
    private TextView mThemeText;
    private ImageView mThemeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOriginalText = (TextView) findViewById(R.id.original_text);
        mThemeText = (TextView) findViewById(R.id.theme_text);
        mThemeIcon = (ImageView) findViewById(R.id.theme_icon);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindTextColorByTheme();
    }

    private void bindTextColorByTheme() {
        mOriginalText.setTextColor(getResources().getColor(R.color.themeTestColor));

        if (TestApplication.getInstance() != null && TestApplication.getInstance().getThemeResource() != null) {
            Resources resources = TestApplication.getInstance().getThemeResource();
            int themeColor = resources.getColor(resources.getIdentifier("themeTestColor", "color", "example.com.themeresource"));
            mThemeText.setTextColor(themeColor);
            String text = resources.getString(resources.getIdentifier("themeTestString", "string", "example.com.themeresource"));
            mThemeText.setText(text);
            Drawable drawable = resources.getDrawable(resources.getIdentifier("icon", "drawable", "example.com.themeresource"));
            mThemeIcon.setImageDrawable(drawable);
        }
    }
}
