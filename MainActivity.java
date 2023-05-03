import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    List<imageItem> items ;

    ImageAdapter imageAdapter;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPager2);
        items = new ArrayList<>();
        items.add(new imageItem(R.drawable.cartoon_1));
        items.add(new imageItem(R.drawable.ragini_mms_two_xlg));
        items.add(new imageItem(R.drawable.transformer));

        items.add(new imageItem(R.drawable.jsleague));


        imageAdapter = new ImageAdapter(items,viewPager2);
        viewPager2.setAdapter(imageAdapter);
        //----------------------------------------------------------------------------------------------

        // Transforming design;
        viewPager2.setOffscreenPageLimit(40);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);

        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1- Math.abs(position);
                page.setScaleY(0.85f + r * 0.14f);
            }
        });
        viewPager2.setPageTransformer(transformer);
        //------------------------------------------------------------------------------------------------
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed( runnable,2000);
            }
        });
    }

    // looping view head to end;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+ 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,2000);
    }
    //-------------------------------------------------------------------------------------------------------------
}
