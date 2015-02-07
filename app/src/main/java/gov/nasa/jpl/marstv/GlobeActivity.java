/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package gov.nasa.jpl.marstv;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import org.androidannotations.annotations.EActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


/*
 * MainActivity class
 */
@EActivity
public class GlobeActivity extends Activity {

    /** Hold a reference to our GLSurfaceView */
    private GLSurfaceView mGLSurfaceView;

    private Dpad mDpad;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLSurfaceView = new GLSurfaceView(this);
        mDpad = new Dpad();

        // Check if the system supports OpenGL ES 2.0.
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        System.out.println(configurationInfo.reqGlEsVersion);
        if (supportsEs2)
        {
            Log.e("GlobeActivity", "we have OpenGL?");

            // Request an OpenGL ES 2.0 compatible context.
            mGLSurfaceView.setEGLContextClientVersion(2);

            //read json
            String json = null;
            try {

                InputStream is = getApplicationContext().getAssets().open("MarsPlanet.json");

                int size = is.available();

                byte[] buffer = new byte[size];

                is.read(buffer);

                is.close();

                json = new String(buffer, "UTF-8");

                JSONObject actualjson = new JSONObject(json);

                System.out.println(actualjson);


            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Set the renderer to our demo renderer, defined below.
            mGLSurfaceView.setRenderer(new GlobeRenderer());
        }
        else
        {
            Log.e("GlobeActivity", "Incompatible OpenGL");

            System.out.println("OO NOOO");
            // This is where you could create an OpenGL ES 1.x compatible
            // renderer if you wanted to support both ES 1 and ES 2.
            return;
        }
        setContentView(mGLSurfaceView);

    }

    @Override
    protected void onResume()
    {
        // The activity must call the GL surface view's onResume() on activity onResume().
        super.onResume();
        mGLSurfaceView.onResume();

    }

    @Override
    protected void onPause()
    {
        // The activity must call the GL surface view's onPause() on activity onPause().
        super.onPause();
        mGLSurfaceView.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_A) {
            Log.e("GlobeActivity", "A PRESSED DOWN");
            Intent intent = new Intent(this, SurfaceActivity_.class);
            startActivity(intent);
            return true;
        }
        if(event.getKeyCode()==KeyEvent.KEYCODE_BUTTON_B) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
