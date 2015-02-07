package gov.nasa.jpl.marstv;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import gov.nasa.jpl.marstv.Dpad;

@EActivity(R.layout.activity_region)
public class RegionActivity extends Activity {

    Dpad mDpad;

    @AfterViews
    void init() {
        mDpad = new Dpad();
    }






    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);
        if(event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_A) {
            Intent intent = new Intent(this, SurfaceActivity_.class);
            startActivity(intent);
            return true;
        } else if(event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_B) {
            Intent intent = new Intent(this, GlobeActivity_.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
