package com.tevinjeffrey.rutgersct.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.tevinjeffrey.rutgersct.R;
import com.tevinjeffrey.rutgersct.animator.EaseOutQuint;
import com.tevinjeffrey.rutgersct.services.Alarm;

public class MainActivity extends ActionBarActivity {

    public static void setGreenWindow(Activity context) {
        setWindowColor(context.getResources().getColor(R.color.green), context.getResources().getColor(R.color.green_dark), context);
    }

    public static void setAccentWindow(Activity context) {
        setWindowColor(context.getResources().getColor(R.color.accent), context.getResources().getColor(R.color.accent_dark), context);
    }

    public static void setPrimaryWindow(Activity context) {
        setWindowColor(context.getResources().getColor(R.color.primary), context.getResources().getColor(R.color.primary_dark), context);
    }

    private static void setWindowColor(int color, int colorDark, Activity context) {
        Window window = context.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(colorDark);
            window.setNavigationBarColor(colorDark);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            TrackedSectionsFragment tsf = new TrackedSectionsFragment();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tsf.setEnterTransition(new AutoTransition().excludeTarget(ImageView.class, true));
                tsf.setExitTransition(new Fade(Fade.OUT).excludeTarget(ImageView.class, true));
                tsf.setReenterTransition(new AutoTransition().excludeTarget(ImageView.class, true));
                tsf.setReturnTransition(new Fade(Fade.IN).excludeTarget(ImageView.class, true));
                tsf.setAllowReturnTransitionOverlap(false);
                tsf.setAllowEnterTransitionOverlap(false);
                tsf.setSharedElementEnterTransition(new ChangeBounds().setInterpolator(new EaseOutQuint()));
                tsf.setSharedElementReturnTransition(new ChangeBounds().setInterpolator(new EaseOutQuint()));
            }
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, tsf)
                    .commit();
        }

        setPrimaryWindow(this);

        new Alarm(getApplicationContext()).setAlarm();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Allows the up button to call the back button. This pops the fragment off the back stack.
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
