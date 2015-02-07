package gov.nasa.jpl.marstv;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_surface)
public class SurfaceActivity extends Activity {

    @AfterViews
    void init() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);
        if(event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_A) {
            Intent intent = new Intent(this, RegionActivity_.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
