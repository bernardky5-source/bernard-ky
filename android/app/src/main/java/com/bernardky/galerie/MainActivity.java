package com.bernardky.galerie;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;

public class MainActivity extends Activity {

private final String[] photos = {
    "1746055406887.jpg",
    "20250303_060327.jpg",
    "BeautyPlus_20191002114735_save.jpg",
    "BeautyPlus_20240504224809_save.jpg",
    "BeautyPlus_20240506225343_save.jpg"
};

private int dp(float value) {
    return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
}

private TextView text(String value, int size, int color) {
    TextView t = new TextView(this);
    t.setText(value);
    t.setTextSize(size);
    t.setTextColor(color);
    t.setGravity(Gravity.CENTER);
    return t;
}

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getWindow().setStatusBarColor(Color.rgb(16, 16, 16));
    getWindow().setNavigationBarColor(Color.rgb(16, 16, 16));

    LinearLayout page = new LinearLayout(this);
    page.setOrientation(LinearLayout.VERTICAL);
    page.setBackgroundColor(Color.rgb(16, 16, 16));

    ScrollView scroll = new ScrollView(this);
    LinearLayout content = new LinearLayout(this);
    content.setOrientation(LinearLayout.VERTICAL);
    content.setPadding(dp(16), dp(20), dp(16), dp(24));

    TextView title = text("BERNARD KY", 28, Color.rgb(201, 162, 39));
    title.setTypeface(null, android.graphics.Typeface.BOLD);
    content.addView(title, new LinearLayout.LayoutParams(
        -1, dp(55)));

    TextView subtitle = text("GALERIE PERSONNELLE", 13, Color.LTGRAY);
    content.addView(subtitle, new LinearLayout.LayoutParams(
        -1, dp(35)));

    TextView intro = text("Mes photos • Collection personnelle", 15, Color.WHITE);
    content.addView(intro, new LinearLayout.LayoutParams(
        -1, dp(50)));

    for (String file : photos) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setBackgroundColor(Color.rgb(30, 30, 30));
        card.setPadding(dp(8), dp(8), dp(8), dp(8));

        ImageView image = new ImageView(this);
        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        image.setBackgroundColor(Color.rgb(24, 24, 24));

        try (InputStream input = getAssets().open("photos/" + file)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, options);
            image.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(this, "Impossible de charger " + file,
                Toast.LENGTH_SHORT).show();
        }

        card.addView(image, new LinearLayout.LayoutParams(
            -1, dp(300)));

        TextView caption = text("BERNARD KY", 14, Color.rgb(201, 162, 39));
        caption.setPadding(0, dp(8), 0, dp(8));
        card.addView(caption);

        LinearLayout.LayoutParams cardParams =
            new LinearLayout.LayoutParams(-1, -2);
        cardParams.setMargins(0, dp(8), 0, dp(12));
        content.addView(card, cardParams);
    }

    TextView footer = text("© Bernard ky", 12, Color.LTGRAY);
    content.addView(footer, new LinearLayout.LayoutParams(-1, dp(45)));

    scroll.addView(content);
    page.addView(scroll, new LinearLayout.LayoutParams(-1, -1));
    setContentView(page);
}

}
